package com.cspl.commons.service;

import com.cspl.commons.exception.RequestFailedException;
import com.cspl.commons.util.Constants;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Utility class to make http calls
 * @author Ashutosh
 */
@Slf4j
public class HttpClientUtil {

    private HttpClientUtil() {

    }

    public static String get(String url, Map<String, String> header) throws HttpResponseException, RequestFailedException {
        log.info("Entering into [HttpClientUtil -> get] for URL {}", url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ResponseHandler<String> handler = new BasicResponseHandler();
            HttpGet getRequest = new HttpGet(url);
            if (null != header) {
                for (Map.Entry<String, String> element : header.entrySet()) {
                    getRequest.addHeader(element.getKey(), element.getValue());
                }
            }
            return httpClient.execute(getRequest, handler);
        } catch (HttpResponseException e) {
            log.error("GET method call failed for {}", url, e);
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while fetching the details {}", url, e);
            throw new RequestFailedException();
        }
    }

    public static String post(String url, Object object, Map<String, String> header, String contentType) throws RequestFailedException, HttpResponseException {
        log.info("Entering into [HttpClientUtil -> post] for URL {}", url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ResponseHandler<String> handler = new BasicResponseHandler();
            HttpPost postRequest = new HttpPost(url);
            StringEntity input = null;
            if (null != contentType && !contentType.isEmpty()) {
                if (contentType.equals(Constants.CONTENT_TYPE_JSON)) {
                    String json = new Gson().toJson(object);
                    input = new StringEntity(json);
                } else {
                    input = new StringEntity((String) object);
                }
                input.setContentType(contentType);
            }
            if (null != header) {
                for (Map.Entry<String, String> element : header.entrySet()) {
                    postRequest.addHeader(element.getKey(), element.getValue());
                }
            }
            postRequest.setEntity(input);
            return httpClient.execute(postRequest, handler);
        } catch (HttpResponseException e) {
            log.error("POST method call failed for {}", url, e);
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while fetching the details {}", url, e);
            throw new RequestFailedException();
        }

    }

}
