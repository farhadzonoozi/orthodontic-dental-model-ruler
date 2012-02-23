package h4nsolo.gmail.com.dental.modelanalysis;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;

public class MyItemListTableElement
{
	public static int TYPE_SCALE = 100;
	
	public static int TYPE_POINT = 0;
	public static int TYPE_LINE = 1;
	public static int TYPE_MULTILINE = 2;
	
	private int type;
	private String name;
	private Object object;
	private double distance = 0;
	
	public MyItemListTableElement(int type, String name)
	{
		this.type = type;
		this.name = name;
		
		if( type == TYPE_SCALE ) distance = 1;
	}
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Object getObject()
	{
		return object;
	}

	public void setObject(Object object)
	{
		this.object = object;
		
		//calculate value
		if( object instanceof Line2D )
		{
			Line2D l = (Line2D)object;
			distance = l.getP1().distance(l.getP2());
		}
		else if( object instanceof MultiLine )
		{
			MultiLine l = (MultiLine)object;
			distance = 0;
			Iterator<Point> i = l.getPoints().iterator();
			Point p1 = i.next();
			Point p2 = null;
			while( i.hasNext() )
			{
				p2 = i.next();
				distance += p1.distance(p2);
				p1 = p2;
			}
		}
	}

	public double getDistance()
	{
		return distance;
	}
	
	public String toString()
	{
		return name;
	}
}
