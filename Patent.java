package wt;

import java.util.ArrayList;

public class Patent
{
	private ArrayList<Patent> above;
	private ArrayList<Patent> below;
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
}