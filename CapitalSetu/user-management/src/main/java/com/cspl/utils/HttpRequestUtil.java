package com.cspl.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HttpRequestUtil {

    @Autowired
    private RestTemplate restTemplate;


    public String getRequestMethod(String url, Map<String, String> header) {
        try {
            HttpHeaders headers = new HttpHeaders();
            if (null != header) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    headers.set(entry.getKey(), entry.getValue());
                }
            }
            return restTemplate.getForObject(url, String.class, headers);
        } catch (Exception e) {
            log.info("Get Request method call failed for Url {}", url);
        }
        return null;
    }

    public String postRequestMethod(String url, Map<String, String> header, Object request, MediaType contentType) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(contentType);
            if (null != header) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    headers.set(entry.getKey(), entry.getValue());
                }
            }
            return restTemplate.postForObject(url, request, String.class, headers);
        } catch (Exception e) {
            log.error("Post Request method call failed for Url. ", e);
        }
        return null;
    }

    public <T> T extractObject(String object, TypeReference<T> typeRef) {
        try {
            return new ObjectMapper().readValue(object, typeRef);
        } catch (Exception e) {
            log.error("Unable to convert:", e);
        }
        return null;
    }
}
