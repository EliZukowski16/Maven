package org.ssa.ironyard.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.ssa.ironyard.model.Property;

@RestController
public class PropertyRestController
{
    static final Logger LOGGER = LogManager.getLogger(PropertyRestController.class);
    
    @Autowired
    @Qualifier("default-props")
    Map<String, List<Property>> allProperties;
    
    @RequestMapping("/realestate/{city}/{street}")
    @ResponseBody
    public List<Property> search(@PathVariable String city, @PathVariable String street)
    {
        LOGGER.debug("Got City:{} and Street:{}", city, street);
        
        return allProperties.get(city).stream().filter(p -> p.getAddress().contains(street)).collect(Collectors.toList());
        
    }
    
    @RequestMapping("/realestate/{city}")
    @ResponseBody
    public List<Property> search(@PathVariable String city)
    {
        LOGGER.debug("Got City:{}", city);
        
        return allProperties.get(city);
    }

}
