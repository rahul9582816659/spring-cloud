package com.spring.cloud.limitservice.api;

import com.spring.cloud.limitservice.domain.LimitConfig;
import com.spring.cloud.limitservice.config.PropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeAPI {

    @Autowired
    private PropertyConfig config;

    @GetMapping("/limit")
    public LimitConfig limit() {
        return new LimitConfig(config.getMaximum(),config.getMinimum());
    }
}
