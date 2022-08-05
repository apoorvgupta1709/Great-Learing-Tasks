package com.cspl.commons.util;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.exception.ObjectMappingFailedException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Utility class to map object from api response
 * @author Ashutosh
 */
@Slf4j
@Service
public class ObjectMapperUtil {

    public static <T> T extractObject(String object, TypeReference<T> typeRef) throws BaseException {
        try {
            return new ObjectMapper().readValue(object, typeRef);
        } catch (Exception e) {
            log.error("Unable to convert:", e);
            throw new ObjectMappingFailedException();
        }

    }

}
