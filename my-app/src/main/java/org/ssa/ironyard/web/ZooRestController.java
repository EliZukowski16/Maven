package org.ssa.ironyard.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.ssa.ironyard.model.Animal;
import org.ssa.ironyard.model.AnimalLink;

@RestController
@RequestMapping("/zoo")
public class ZooRestController
{
    static final Logger LOGGER = LogManager.getLogger(ZooRestController.class);

    @Autowired
    @Qualifier("SSAZoo")
    Map<String, Animal> ssaZoo;

    @Autowired
    @Qualifier("SSAZooLinks")
    List<AnimalLink> ssaZooLinks;

    @RequestMapping("/animals")
    @ResponseBody
    public ResponseEntity<List<AnimalLink>> zoo()
    {
        HttpHeaders header = new HttpHeaders();

        header.put("X-Powered-By", Arrays.asList("SSA Zoo"));

        ResponseEntity<List<AnimalLink>> returnEntity = new ResponseEntity<List<AnimalLink>>(ssaZooLinks, header,
                HttpStatus.OK);

        return returnEntity;
    }

    @RequestMapping("/animals/{animal}")
    @ResponseBody
    public ResponseEntity<Animal> zooAnimal(@PathVariable String animal)
    {
        HttpHeaders header = new HttpHeaders();

        header.put("X-Powered-By", Arrays.asList("SSA Zoo"));

        ResponseEntity<Animal> returnEntity = new ResponseEntity<Animal>(ssaZoo.get(animal), header,
                HttpStatus.OK);

        return returnEntity;

    }

    
    @RequestMapping("/animals/search/continents/{continent}")
    @ResponseBody
    public ResponseEntity<List<Animal>> continentSearch(@PathVariable String continent)
    {
        HttpHeaders header = new HttpHeaders();

        header.put("X-Powered-By", Arrays.asList("SSA Zoo"));
        
        List<Animal> filteredAnimals = ssaZoo.entrySet().stream()
                .filter(e -> e.getValue().getContinents().equals(continent))
                .map(e -> e.getValue())
                .collect(Collectors.toList());
        
        ResponseEntity<List<Animal>> returnEntity = new ResponseEntity<List<Animal>>(filteredAnimals, header,
                HttpStatus.OK);

        return returnEntity;
        
    }

    
}
