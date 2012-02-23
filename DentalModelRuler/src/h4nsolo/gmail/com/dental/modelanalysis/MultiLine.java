package h4nsolo.gmail.com.dental.modelanalysis;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.ArrayList;


public class MultiLine extends Path2D.Double
{
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public void addPoint(Point p)
	{
		points.add(p);
	}
	
	public ArrayList<Point> getPoints()
	{
		return points;
	}
}