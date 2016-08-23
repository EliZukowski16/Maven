package org.ssa.ironyard.web;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssa.ironyard.web.Fruit.Color;

@Controller
public class GreetingController
{
    Logger LOGGER = LogManager.getLogger(GreetingController.class);
    
    @Autowired
    WeatherService weather;
    
    @RequestMapping("/")
    @ResponseBody
    public String greet()
    {
        LOGGER.info("Greeting Given");
        return "Our first web app";
        
    }
    
    @RequestMapping("/now")
    @ResponseBody
    public Map<String, LocalDate> now()
    {
        Map<String, LocalDate> model = new HashMap<>();
        model.put("now", LocalDate.now());
        LOGGER.info("Date/Time Now Given");
        
        return model;
    }
    
    
    @RequestMapping("/tomato")
    @ResponseBody
    public Fruit tomato()
    {
        LOGGER.info("Tomato returned");
        
        return new Fruit(Color.RED, "tomato");
    }
    
    @RequestMapping("/temp")
    @ResponseBody
    public float currentTemp()
    {
        return this.weather.temperature();
    }
    

}
