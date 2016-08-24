package org.ssa.ironyard.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ssa.ironyard.model.Property;

@Configuration
public class PropertyControllerConfig
{
    static final Logger LOGGER = LogManager.getLogger(PropertyControllerConfig.class);
    
    @Bean(name = "default-props")
    public Map<String, List<Property>> defaults() throws URISyntaxException, IOException
    {
        Map<String, List<Property>> allProperties = new HashMap<>();

        File propertyFile = new WebFileFactory("properties.csv").getInstance();
        
        LOGGER.debug("Got property file {}", propertyFile.toURI());

        BufferedReader reader = null;

        try
        {
            reader = Files.newBufferedReader(propertyFile.toPath(), Charset.defaultCharset());

            String line;

            while (null != (line = reader.readLine()))
            {
                String[] property = line.split(",");
                String city = property[0];
                String address = property[1];
                int cost = Integer.parseInt(property[2]);
                int floorArea = Integer.parseInt(property[3]);
                
                LOGGER.debug("Reading City:{}, Street:{}, Cost:{}, Area:{}", city, address, cost, floorArea);

                List<Property> listProperties = allProperties.get(city);

                if (listProperties == null)
                {
                    LOGGER.debug("Creating new property list for City:{}", city);
                    listProperties = new ArrayList<>();
                }

                listProperties.add(new Property(address, cost, floorArea));
                allProperties.put(city, listProperties);
            }
        }
        catch (IOException iex)
        {
            System.err.println(iex);
            throw iex;
        }
        finally
        {
            if (null != reader)
                reader.close();
        }
        
        return allProperties;
    }

}
