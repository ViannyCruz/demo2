package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class InstanceController {

    private static final Logger logger = LoggerFactory.getLogger(InstanceController.class);

    @Value("${instance.id}")
    private String instanceId;

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String index(HttpSession session) {
        logger.info("Instance ID: {}, Port: {}", instanceId, port); // Log para verificar las propiedades
        if (session.getAttribute("visits") == null) {
            session.setAttribute("visits", 0);
        }
        int visits = (int) session.getAttribute("visits");
        session.setAttribute("visits", visits + 1);
        return "Hello from instance " + instanceId + " on port " + port +
                "<br>Session ID: " + session.getId() +
                "<br>You have visited this app " + session.getAttribute("visits") + " times";
    }

    @GetMapping("/health")
    public String health() {
        return "UP";
    }
}