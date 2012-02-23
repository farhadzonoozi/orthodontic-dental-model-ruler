package h4nsolo.gmail.com.dental.modelanalysis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.nio.BufferUnderflowException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JPanel;

public class MyImagePane extends JPanel{
	
	private BufferedImage img = null;
	private Hashtable<String,Point> pointTable;
	private Hashtable<String,Line2D> lineTable;
	private Hashtable<String,MultiLine> multiLineTable;
	private Hashtable<String,Polygon> polygonTable;
	
	public MyImagePane()
	{
		pointTable = new Hashtable<String, Point>();
		lineTable = new Hashtable<String, Line2D>();
		multiLineTable = new Hashtable<String, MultiLine>();
		polygonTable = new Hashtable<String, Polygon>();
		
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
	}
	
	public void setImage(BufferedImage img)
	{
		this.img = img;
	}
	
	public BufferedImage getImage()
	{
		return img;
	}
	
	public Image getCurrentImage()
	{
		Dimension size = getSize();
		BufferedImage currentImg = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = currentImg.getGraphics();
		g = paintMyData(g);
		return currentImg;
	}
	
	public void addPoint(String name, int x, int y)
	{
		pointTable.put(name, new Point(x,y));
	}
	
	public void removePoint(String name)
	{
		pointTable.remove(name);
	}
	
	public void addLine(String name, int startX, int startY, int endX, int endY)
	{
		lineTable.put(name, new Line2D.Double(startX, startY, endX, endY));
	}
	
	public void removeLine(String name)
	{
		lineTable.remove(name);
	}
	
	public void addMultiLine(String name, MultiLine p)
	{
		multiLineTable.put(name, p);
	}
	
	public void removeMultiLine(String name)
	{
		multiLineTable.remove(name);
	}
	
	public void addPolygon(String name, Polygon polygon)
	{
		polygonTable.put(name, polygon);
	}
	
	public void removePolygon(String name)
	{
		polygonTable.remove(name);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		paintMyData(g);
	}
	
	public void clear()
	{
		img = null;
		pointTable.clear();
		lineTable.clear();
		multiLineTable.clear();
		polygonTable.clear();
	}
	
	private Graphics paintMyData(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		
		// draw background image
		g2.drawImage(img, 0,0,1000,768,null);
		
		// draw points
		Enumeration<Point> points = pointTable.elements();
		g2.setPaint(Color.BLUE);
		while( points.hasMoreElements() )
		{
			Point p = points.nextElement();
			
			g2.drawOval(p.x-1, p.y-1, 2,2);
			g2.fillOval(p.x-1, p.y-1, 2,2);
		}
		
		// draw line
		Enumeration<Line2D> lines = lineTable.elements();
		g2.setPaint(Color.GREEN);
		while( lines.hasMoreElements())
		{
			Line2D l = lines.nextElement();
			g2.draw(l);
		}
		
		// draw multiLine
		Enumeration<MultiLine> multiLines = multiLineTable.elements();
		g2.setPaint(Color.RED);
		while( multiLines.hasMoreElements() )
		{
			MultiLine p = multiLines.nextElement();
			g2.draw(p);
		}
		
		// draw polygon
		Enumeration<Polygon> polygons = polygonTable.elements();
		g2.setPaint(Color.RED);
		while( polygons.hasMoreElements())
		{
			Polygon p = polygons.nextElement();
			g2.draw(p);
			g2.fill(p);
		}
		
		return g2;
	}
}
