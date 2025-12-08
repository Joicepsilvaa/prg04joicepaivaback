package br.com.ifba.infrastructure.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiExternaClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String consumirApiExterna() {
        String url = "https://httpbin.org/get";
        return restTemplate.getForObject(url, String.class);
    }
}
