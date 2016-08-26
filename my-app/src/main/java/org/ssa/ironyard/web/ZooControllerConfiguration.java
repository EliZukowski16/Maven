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
import org.ssa.ironyard.model.Animal;
import org.ssa.ironyard.model.AnimalLink;

@Configuration
public class ZooControllerConfiguration
{
    static final Logger LOGGER = LogManager.getLogger(ZooControllerConfiguration.class);

    @Bean(name = "SSAZoo")
    public Map<String, Animal> ssaZoo() throws URISyntaxException, IOException
    {
        Map<String, Animal> ssaZooAnimals = new HashMap<>();

        File animalFile = new WebFileFactory("animals.txt").getInstance();

        LOGGER.debug("Got file {}", animalFile.toURI());

        BufferedReader reader = null;

        try
        {
            reader = Files.newBufferedReader(animalFile.toPath(), Charset.defaultCharset());

            String line;

            while (null != (line = reader.readLine()))
            {
                String[] animal = line.split(";");
                String name = animal[0];
                List<String> continents = new ArrayList<>();
                for (String s : animal[1].split(","))
                {
                    continents.add(s);
                }
                String interestingFact = animal[2];

                LOGGER.debug("Reading Name:{}, Continents:{}, Interesting Fact:{}", name, continents, interestingFact);

                ssaZooAnimals.put(name, new Animal(name, continents, interestingFact));

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

        return ssaZooAnimals;

    }

    @Bean(name = "SSAZooLinks")
    public List<AnimalLink> ssaZooLinks(Map<String, Animal> ssaZooAnimal)
    {
        List<AnimalLink> ssaZooLinks = new ArrayList<>();

        ssaZooAnimal.keySet().stream().sorted().forEach(s -> ssaZooLinks.add(new AnimalLink(s, "/zoo/animals/" + s)));

        return ssaZooLinks;
    }

}
