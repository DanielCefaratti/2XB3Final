package wt;

import java.util.ArrayList;

public class Patent
{
	private ArrayList<Patent> above = new ArrayList<Patent>();
	private ArrayList<Patent> below = new ArrayList<Patent>();
	private int id;
	
	public Patent(int id)
	{
		this.id = id;
	}
	
	//getters
	public int getId()
	{
		return id;
	}
	public ArrayList<Patent> getAbove()
	{
		return above;
	}
	public ArrayList<Patent> getBelow()
	{
		return below;
	}
	
	//setter
	public void setId(int id)
	{
		id = id;
	}
	public void addAbove(Patent p)
	{
		above.add(p);
	}
	public void addBelow(Patent p)
	{
		below.add(p);
	}
	
	public boolean isEmpty()
	{
		if(id == -1)
		{
			return true;
		}
		else return false;
	}
}