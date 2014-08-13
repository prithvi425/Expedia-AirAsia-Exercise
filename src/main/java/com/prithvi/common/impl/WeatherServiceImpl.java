package com.prithvi.common.impl;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.prithvi.common.builder.URLBuilder;
import com.prithvi.common.definition.Weather;

@Service
public class WeatherServiceImpl {

    @Autowired
    @Qualifier(value = "uRLBuilder")
    URLBuilder builder;

    public Weather retrieveWeather(int zipCode) throws IOException {

        String url = builder.getUrlForZipcode(zipCode);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new URL(url), Weather.class);
    }
}
