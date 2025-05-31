package com.crispyecho.springkarate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {
    @LocalServerPort
    int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void testHello() {
        String response = restTemplate.getForObject("http://localhost:" + port + "/hello", String.class);
        assertThat(response).isEqualTo("Hello World!");
    }
}
