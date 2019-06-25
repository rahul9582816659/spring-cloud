package com.spring.cloud.currencyconversionservice.api;

import com.spring.cloud.currencyconversionservice.entity.CurrencyConversion;
import com.spring.cloud.currencyconversionservice.feign.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionAPI {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(@PathVariable String from , @PathVariable String to, @PathVariable BigDecimal quantity) {

        // Key Value pair for variables that we pass in http URL
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        // we can call get, post, put , delete operation on rest template
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

        // in body we will get the data which we cast to CurrencyConversion
        CurrencyConversion response = responseEntity.getBody();

        // return CurrencyConversion
        return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }


    // Duplicate of above using Feign and Above using RestClient
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable String from , @PathVariable String to, @PathVariable BigDecimal quantity) {

        // call feign
        CurrencyConversion response = currencyExchangeServiceProxy.retriveExchangeValue(from,to);

        // return CurrencyConversion
        return new CurrencyConversion(response.getId(),from,to,response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());

    }
}
