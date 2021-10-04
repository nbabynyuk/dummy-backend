package com.nb.refbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WebController {
    
    @Value("${SAMPLE-APP-CONFIG-VALUE}")
    private String sampleConfigValue;
    @Value("${SAMPLE-APP-SECRET-VALUE}")
    private String sampleSecretValue;
    
    @GetMapping
    public Map<String, String> index() {
        return Map.of("version", "0.0.3",
            "sampleConfigValue", sampleConfigValue,
            "sampleSecretValue", sampleSecretValue,
            "hostname", getHostname());
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "undefined";
        }
    }
}
