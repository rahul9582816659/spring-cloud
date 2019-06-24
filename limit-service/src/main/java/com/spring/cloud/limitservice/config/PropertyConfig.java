package com.spring.cloud.limitservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limit-service")
public class PropertyConfig {


    // Create Multiple Config Property Related to functionality this will break into smaller modules
    // instead of using 1 big AppConfig File.

    // Make sure these name should match with what in property file after limit-service
    private int maximum;
    private int minimum;

    public PropertyConfig() {
    }

    public PropertyConfig(int maximum, int minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    @Override
    public String toString() {
        return "PropertyConfig{" +
                "maximum=" + maximum +
                ", minimum=" + minimum +
                '}';
    }
}
