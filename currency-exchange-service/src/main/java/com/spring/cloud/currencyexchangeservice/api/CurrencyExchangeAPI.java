package com.spring.cloud.currencyexchangeservice.api;

import com.spring.cloud.currencyexchangeservice.entity.ExchangeValue;
import com.spring.cloud.currencyexchangeservice.repo.ExchangeValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeAPI {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepo exchangeValueRepo;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retriveExchangeValue(@PathVariable String from , @PathVariable String to) {

        // find in repo using from and to
        ExchangeValue exchangeValue = exchangeValueRepo.findByFromAndTo(from, to);

        // find port from property file
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("server.port")));

        // return exchange value
        return exchangeValue;
    }
}
