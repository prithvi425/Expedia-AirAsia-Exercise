package com.prithvi.common.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prithvi.common.definition.Weather;
import com.prithvi.common.impl.WeatherServiceImpl;

@Controller
public class WeatherController {
    @Autowired
    private WeatherServiceImpl weatherServiceImpl;

    private static final Logger logger = Logger.getLogger(WeatherController.class);

    @Autowired
    private Validator validator;


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelMap get() {
        return new ModelMap("zipcode", new ZipCodeInformation());
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String post(@ModelAttribute("zipcode") @Valid ZipCodeInformation userMsg,
                       BindingResult result, Model model) {

        validator.validate(userMsg, result);
        if (result.hasErrors()) { return "form"; }

        try {
            Weather weather = weatherServiceImpl.retrieveWeather(Integer.parseInt(userMsg.getZipCode()));
            if(weather.getCurrentObservation() == null) {
                result.addError(new FieldError("zipcode","zipCode","zipcode not found"));
                return "form";
            }
            model.addAttribute("weather",weather);
            return "show-weather";
        } catch (IOException e) {
            logger.error("Unable to retrieve weather",e);
            return "weather-down";
        }

    }

    public static class ZipCodeInformation {

        @Pattern(regexp = ("\\d\\d\\d\\d\\d"),message = "Please enter a valid 5 digit zipcode")
        private String zipCode;


        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

}
