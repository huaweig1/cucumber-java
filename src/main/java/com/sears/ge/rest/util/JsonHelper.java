package com.sears.ge.rest.util;

import java.io.IOException;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Q
 */
public class JsonHelper {

    private static Logger logger = LoggerFactory.getLogger(JsonHelper.class);

    public static <T> String serialize(T obj, boolean wrapRootValue) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, wrapRootValue);
        mapper.configure(SerializationConfig.Feature.USE_ANNOTATIONS, true);
        mapper.setSerializationConfig(mapper.getSerializationConfig().withAnnotationIntrospector(getAnnotationIntrospector()));
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("Exception: " + e.getCause());
        }
        return null;
    }

    public static <T> T deserialize(String jsonInput, Class<T> type, boolean unwrapRootValue) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, unwrapRootValue);
        mapper.configure(DeserializationConfig.Feature.USE_ANNOTATIONS, true);
        mapper.setSerializationConfig(mapper.getSerializationConfig().withAnnotationIntrospector(getAnnotationIntrospector()));
        try {
            return mapper.readValue(jsonInput, type);
        } catch (IOException e) {
            logger.error("Exception: " + e.getCause());
        }
        return null;
    }

    public static Pair getAnnotationIntrospector() {
        AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();
        AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
        return new AnnotationIntrospector.Pair(jacksonIntrospector, jaxbIntrospector);
    }
}
