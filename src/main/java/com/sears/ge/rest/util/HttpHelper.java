package com.sears.ge.rest.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Q
 */
public class HttpHelper {

    private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
    private static final int HTTP_TIMEOUT = 120 * 1000;
    private static String baseUrl;

    private HttpHelper() {
    }

    public static void setBaseUrl(String url) {
        baseUrl = url;
    }

    private static CloseableHttpClient getCloseableHttpClient() {
        int timeoutValue = HTTP_TIMEOUT;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeoutValue).setConnectTimeout(timeoutValue).build();
        HttpClients.custom().setDefaultRequestConfig(requestConfig);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        return closeableHttpClient;
    }

    /**
     * Perform HTTP Post method with the json payload
     *
     * @param path path of RESTful API
     * @param jsonInput payload
     * @return response of the HTTP Post method execution
     */
    public static String invokePost(String path, String jsonInput) {
        return invokePost(path, null, jsonInput);
    }

    /**
     * Perform HTTP Post method with the json payload and http headers
     *
     * @param path path of RESTful API
     * @param headers http headers
     * @param jsonInput payload
     * @return response of the HTTP Post method execution
     */
    public static String invokePost(String path, Map<String, String> headers, String jsonInput) {
        String output = null;
        try (CloseableHttpClient closeableHttpClient = getCloseableHttpClient()) {
            HttpPost postRequest = new HttpPost(baseUrl + path);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    postRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }
            logger.info("JSON Request sent to web service is: " + jsonInput);
            HttpEntity entity = new StringEntity(jsonInput);
            postRequest.setEntity(entity);
            try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(postRequest)) {
                output = convertHTTPResponseToString(closeableHttpResponse);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getCause());
        } finally {
            return output;
        }
    }

    /**
     * Perform HTTP Get method
     *
     * @param path path of RESTful API
     * @return response of the HTTP Get method execution
     */
    public static String invokeGet(String path) {
        return invokeGet(path, null);
    }

    /**
     * Perform HTTP Get method with the http headers
     *
     * @param path path of RESTful API
     * @param headers http headers
     * @return response of the HTTP Get method execution
     */
    public static String invokeGet(String path, Map<String, String> headers) {
        return invokeGet(path, headers, null);
    }

    /**
     * Perform HTTP Get method with the http headers and query string
     *
     * @param path path of RESTful API
     * @param headers http headers
     * @param queryParameters query string
     * @return response of the HTTP Get method execution
     */
    public static String invokeGet(String path, Map<String, String> headers, Map<String, String> queryParameters) {
        String output = null;
        try (CloseableHttpClient closeableHttpClient = getCloseableHttpClient()) {
            String url = baseUrl + path;
            if (queryParameters != null) {
                url = appendQueryParamsToURL(url, queryParameters);
            }
            HttpGet getRequest = new HttpGet(url);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    getRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }

            try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(getRequest)) {
                output = convertHTTPResponseToString(closeableHttpResponse);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getCause());
        } finally {
            return output;
        }
    }

    /**
     * Perform HTTP Delete method
     *
     * @param path path of RESTful API
     * @return response of the HTTP Delete method execution
     */
    public static String invokeDelete(String path) {
        return invokeDelete(path, null);
    }

    /**
     * Perform HTTP Delete method with the http headers
     *
     * @param path path of RESTful API
     * @param headers http headers
     * @return response of the HTTP Delete method execution
     */
    public static String invokeDelete(String path, Map<String, String> headers) {
        String output = null;
        try (CloseableHttpClient closeableHttpClient = getCloseableHttpClient()) {
            HttpDelete deleteRequest = new HttpDelete(baseUrl + path);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    deleteRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }

            try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(deleteRequest)) {
                output = convertHTTPResponseToString(closeableHttpResponse);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getCause());
        } finally {
            return output;
        }
    }

    /**
     * Perform HTTP Put method with the text
     *
     * @param path path of RESTful API
     * @param text string content to upload
     * @param file file to upload
     * @return response of the HTTP Put method execution
     */
    public static String invokePut(String path, String text, File file) {
        return invokePut(path, null, text, file);
    }

    /**
     * Perform HTTP Put method with the text
     *
     * @param path path of RESTful API
     * @param headers http headers
     * @param text string content to upload
     * @param file file to upload
     * @return response of the HTTP Put method execution
     */
    public static String invokePut(String path, Map<String, String> headers, String text, File file) {
        String output = null;
        try (CloseableHttpClient closeableHttpClient = getCloseableHttpClient()) {
            HttpPut putRequest = new HttpPut(baseUrl + path);
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    putRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (text != null) {
                logger.info("Text sent to web service is: " + text);
                HttpEntity entity = new StringEntity(text);
                putRequest.setEntity(entity);
            }
            if (file != null) {
                logger.info("File sent to web service is: " + text);
                HttpEntity entity = new FileEntity(file);
                putRequest.setEntity(entity);
            }

            try (CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(putRequest)) {
                output = convertHTTPResponseToString(closeableHttpResponse);
            }
        } catch (Exception e) {
            logger.error("Exception: " + e.getCause());
        } finally {
            return output;
        }
    }

    private static String convertHTTPResponseToString(HttpResponse httpResponse) throws IOException {
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity);
    }

    private static String appendQueryParamsToURL(String url, Map<String, String> queryParams) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            uriBuilder.setParameter(entry.getKey(), entry.getValue());
        }
        URI uri = uriBuilder.build();
        return uri.toString();
    }
}
