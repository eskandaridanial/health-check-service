package com.heal.ms.utility;

import com.heal.ms.domain.entities.HttpResource;

import com.heal.ms.domain.enums.HttpMethods;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-09 17:19:44
 */
@Component
public class HttpUtils {

    private final RestTemplate restTemplate;

    public HttpUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Boolean is2xxSuccessful(HttpResource httpResource) {
        return call(httpResource).getStatusCode().is2xxSuccessful();
    }

    private ResponseEntity<Object> call(HttpResource httpResource) {
        return httpResource.getHttpMethod().equals(HttpMethods.POST) ? post(httpResource) : get(httpResource);
    }

    private ResponseEntity<Object> post(HttpResource httpResource) {
        HttpHeaders headers = new HttpHeaders();
        httpResource.getHeaders().forEach(headers::add);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(httpResource.getBody(), headers);
        return restTemplate.postForEntity(httpResource.getUrl(), httpEntity, Object.class);
    }

    private ResponseEntity<Object> get(HttpResource httpResource) {
        return restTemplate.getForEntity(httpResource.getUrl(), Object.class);
    }
}
