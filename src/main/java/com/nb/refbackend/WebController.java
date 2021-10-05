package com.nb.refbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
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
        Map<String, String> result = new LinkedHashMap<>();
        result.put("sampleConfigValue", sampleConfigValue);
        result.put("sampleSecretValue", sampleSecretValue);
        result.put("version", "0.0.5");
        result.put("hostname", getHostname());
        return result;
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "undefined";
        }
    }
}
