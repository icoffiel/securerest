package com.nerdery.icoffiel.securerest.web.rest.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test Util for testing REST endpoints.
 *
 * Created by icoffiel on 4/11/2016.
 */
public class TestUtil {

    /**
     * Convert an object to JSON.
     *
     * @param object The object to convert to JSON.
     * @return The Object as a JSON byte array.
     * @throws JsonProcessingException If there is an exception converting the object.
     */
    public static byte[] objectToJsonBytes(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
