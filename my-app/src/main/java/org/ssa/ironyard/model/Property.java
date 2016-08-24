package org.ssa.ironyard.model;

public class Property
{
    private String address;
    private int cost;
    private int floorArea;

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public int getFloorArea()
    {
        return floorArea;
    }

    public void setFloorArea(int floorArea)
    {
        this.floorArea = floorArea;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Property(String address, int cost, int floorArea)
    {
        this.cost = cost;
        this.floorArea = floorArea;
        this.address = address;
    }

}
