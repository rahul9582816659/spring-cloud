package com.spring.cloud.currencyconversionservice.feign;

import com.spring.cloud.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// When we have only 1 instance of currency-exchange-service
// @FeignClient(name = "currency-exchange-service", url = "localhost:8000")

// When we have multiple instance of currency-exchange-service
@FeignClient(name = "currency-exchange-service")

// ribbon is used for load balance
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retriveExchangeValue(@PathVariable String from , @PathVariable String to);
}
