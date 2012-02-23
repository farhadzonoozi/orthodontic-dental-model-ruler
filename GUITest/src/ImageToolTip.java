import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.Graphics;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ImageToolTip
{
 public static void main(String[]args)
 {
  PanelImage imageA=new PanelImage("matrix.jpg");
  
  PanelImage imageB=new PanelImage("Hancock-1611.jpg");
  
  PanelImage imageC=new PanelImage("Leafs_1_by_NerghaL.jpg");

  PanelImage imageD=new PanelImage("Love_Music_by_jovincent.jpg");

  JFrame myMainWindow=new JFrame("Image Tool Tip");
  myMainWindow.setResizable(false);
  myMainWindow.getContentPane().setLayout(new GridLayout(2,2));
  myMainWindow.getContentPane().add(imageA);
  myMainWindow.getContentPane().add(imageB);
  myMainWindow.getContentPane().add(imageC);
  myMainWindow.getContentPane().add(imageD);
  
  myMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  myMainWindow.setSize(100,100);
  myMainWindow.setLocationRelativeTo(null);
  myMainWindow.setVisible(true);
 }
}

class PanelImage extends JPanel implements MouseListener
{
 ImageIcon temp;
 ImageMagnifier im;
 
 public PanelImage(String a)
 {
  addMouseListener(this);
  temp=new ImageIcon(a);
 }
 
 public void paint(Graphics g)
 {
  super.paint(g);
  g.drawImage(temp.getImage(),0,0,getSize().width,getSize().height,this);
 }
 
 public void mouseClicked(MouseEvent event)
 {
 }
 
 public void mouseEntered(MouseEvent event)
 {
  im=new ImageMagnifier(temp,getSize().width,getSize().height,event.getXOnScreen(),event.getYOnScreen());
 }
 
 public void mouseExited(MouseEvent event)
 {
  im.dispose();
 }
 
 public void mousePressed(MouseEvent event)
 { 
 }
 
 public void mouseReleased(MouseEvent event)
 { 
 }
}

class ImageMagnifier extends JFrame
{
 ImageIcon temp;
 
 public ImageMagnifier(ImageIcon imageFile,int width,int height,int x,int y)
 {
  setUndecorated(true);
  temp=imageFile;
  setLocation(x,y);
  setSize(width*9,height*9);
  setVisible(true);
 }
 
 public void paint(Graphics g)
 {
  super.paint(g);
  g.drawImage(temp.getImage(),0,0,getSize().width,getSize().height,this);
 }
}
