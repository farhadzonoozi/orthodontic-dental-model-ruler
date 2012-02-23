package h4nsolo.gmail.com.dental.ODMR;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class DetachedMagnifyingGlass extends JComponent implements MouseMotionListener
{

	double zoom;
	JComponent comp;
	Point point;
	Dimension mySize;
	Robot robot;
	Line2D.Double crossH;
	Line2D.Double crossV;

	public DetachedMagnifyingGlass(JComponent comp, Dimension size, double zoom)
	{
		this.comp = comp;
		// flag to say don't draw until we get a MouseMotionEvent
		point = new Point(-1, -1);
		comp.addMouseMotionListener(this);
		this.mySize = size;
		this.zoom = zoom;
		// if we can't get a robot, then we just never
		// paint anything
		try
		{
			robot = new Robot();
		} catch (AWTException awte)
		{
			System.err.println("Can't get a Robot");
			awte.printStackTrace();
		}
		
		crossH = new Line2D.Double(mySize.width/2-5, mySize.height/2, mySize.width/2+5, mySize.height/2);
		crossV = new Line2D.Double(mySize.width/2, mySize.height/2-5, mySize.width/2, mySize.height/2+5);
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if ((robot == null) || (point.x == -1))
		{
			return;
		}
		Rectangle grabRect = computeGrabRect();
		BufferedImage grabImg = robot.createScreenCapture(grabRect);
//		int boundX = comp.getSize().width-mySize.width ;
//		int boundY = comp.getSize().height-mySize.height;
//		if( grabRect.x > boundX ) grabRect.x = boundX;
//		if( grabRect.y > boundY ) grabRect.y = boundY;
//		BufferedImage grabImg = Pictures.toBufferedImage(((MyImagePane)comp).getCurrentImage()).getSubimage(grabRect.x, grabRect.y, grabRect.width, grabRect.height);
		Image scaleImg = grabImg.getScaledInstance(mySize.width, mySize.height, Image.SCALE_FAST);
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
		g2.drawImage(scaleImg, 0, 0, null);
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f));
		g2.setPaint(Color.BLUE);
		g2.draw(crossH);
		g2.draw(crossV);
	}

	private Rectangle computeGrabRect()
	{
		// width, height are size of this comp / zoom
		int grabWidth = (int) ((double) mySize.width / zoom);
		int grabHeight = (int) ((double) mySize.height / zoom);
		// upper left corner is current point
		return new Rectangle(point.x-grabWidth/2, point.y-grabHeight/2, grabWidth, grabHeight);
	}

	public Dimension getPreferredSize()
	{
		return mySize;
	}

	public Dimension getMinimumSize()
	{
		return mySize;
	}

	public Dimension getMaximumSize()
	{
		return mySize;
	}

	// MouseMotionListener implementations
	public void mouseMoved(MouseEvent e)
	{
		Point offsetPoint = comp.getLocationOnScreen();
		e.translatePoint (offsetPoint.x, offsetPoint.y);
		point = e.getPoint();
		repaint();
		
//		point = e.getPoint();
//		setLocation(point.x-mySize.width/2, point.y-mySize.height/2+22);
//		repaint();
	}

	public void mouseDragged(MouseEvent e)
	{
		mouseMoved(e);
	}

}

/*
 * Swing Hacks Tips and Tools for Killer GUIs By Joshua Marinacci, Chris Adamson
 * First Edition June 2005 Series: Hacks ISBN: 0-596-00907-0
 * http://www.oreilly.com/catalog/swinghks/
 */