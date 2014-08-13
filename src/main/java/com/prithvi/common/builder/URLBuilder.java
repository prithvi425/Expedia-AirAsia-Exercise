package com.prithvi.common.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class URLBuilder {
    private @Value("${weather.key}") String key;
    private @Value("${weather.baseUrl}") String baseUrl;
    private @Value("${weather.pathPrefix}") String pathPrefix;
    private @Value("${weather.extension}") String extension;

   public @Bean(name = "uRLBuilder") URLBuilder getURLBuilder() {
       return this;
   }

    public String getUrlForZipcode(int zipCode) {
        return baseUrl + key+ "/"+pathPrefix+zipCode+"."+extension;
    }
}
