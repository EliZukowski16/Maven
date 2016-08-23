package org.ssa.ironyard.web;

public class Fruit
{
    private final Color color;
    private final String name;
    
    
    public Fruit(Color color, String name)
    {
        this.color = color;
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }

    public Color getColor()
    {
        return this.color;
    }

    public enum Color
    {
        RED, GREEN, YELLOW;
    }

}
