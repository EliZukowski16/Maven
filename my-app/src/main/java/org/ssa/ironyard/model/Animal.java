package org.ssa.ironyard.model;

import java.util.ArrayList;
import java.util.List;

public class Animal
{
    private String name;
    private List<String> continents;
    private String interestingFact;
    
    public Animal()
    {
        name = "";
        continents = new ArrayList<>();
        interestingFact = "";
    }
    
    public Animal(String name, List<String> continents, String interestingFact)
    {
        this.name = name;
        this.continents = continents;
        this.interestingFact = interestingFact;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getContinents()
    {
        return continents;
    }

    public void setContinents(List<String> continents)
    {
        this.continents = continents;
    }

    public String getInterestingFact()
    {
        return interestingFact;
    }

    public void setInterestingFact(String interestingFact)
    {
        this.interestingFact = interestingFact;
    }    
}
