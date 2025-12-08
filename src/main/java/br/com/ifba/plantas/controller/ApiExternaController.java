package br.com.ifba.plantas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.infrastructure.client.ApiExternaClient;

@RestController
@RequestMapping("/api-externa")
public class ApiExternaController {

    private final ApiExternaClient client;

    public ApiExternaController(ApiExternaClient client) {
        this.client = client;
    }

    @GetMapping
    public String consumirApi() {
        return client.consumirApiExterna();
    }
}
