package com.freelancing.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Alaa Jawhar
 */
@UtilityClass
@Slf4j
public class JsonUtil<T> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String convertToString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("unable to parse it as json", e);
            return "{\"Error\": \"" + getStackTraceAsString(e) + "\"}";
        }
    }

    public static <T> T parseJsonToObject(String data, Class<?> target) {
        try {
            return (T) OBJECT_MAPPER.readValue(data, target);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("unable to parse JSON: [" + data + "] to object [" + target + "]");
        }
    }

    public static String getStackTraceAsString(final Throwable throwable) {
        if (throwable == null) {
            return "";
        }
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
