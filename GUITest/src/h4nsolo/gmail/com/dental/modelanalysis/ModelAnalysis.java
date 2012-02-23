package h4nsolo.gmail.com.dental.modelanalysis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ModelAnalysis extends javax.swing.JFrame
{

	private static final long serialVersionUID = -3406808560679984455L;

	{
		// Set Look & Feel
		try
		{
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private MyItemListTable myItemListTable;
	private MyImagePane jImagePane;
	private JScrollPane jScrollPane1;
	private JPanel jItemListPane;
	private JSplitPane jMainSplitPane;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;

	private int dragStartX = -1;
	private int dragStartY = -1;
	private MultiLine currentMultiLine = null;
	private String itemsFileName = "items.cfg";

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new ModelAnalysis();
			}
		});
	}

	public ModelAnalysis()
	{
		super("Model Analysis");
		initGUI();
		openFile();
		setLocationRelativeTo(null);
		setVisible(true);
		
//		JFrame magFrame = new JFrame("Zoom");
		DetachedMagnifyingGlass mag = new DetachedMagnifyingGlass(jImagePane, new Dimension(300, 300), 2.0);
		mag.setLocation(1000,1000);
		setGlassPane(mag);
		mag.setVisible(true);
		Point p = this.getLocation();
		

//		magFrame.getContentPane().add(mag);
//		magFrame.setAlwaysOnTop(true);
//		magFrame.setResizable(false);
//		magFrame.pack();
//		magFrame.setLocation(
//				new Point(
//						this.getLocation().x+this.getWidth()-magFrame.getWidth(), 
//						this.getLocation().y+this.getHeight()-magFrame.getHeight()));
//		magFrame.setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	private void initGUI()
	{
		try
		{
			{
				jMainSplitPane = new JSplitPane();
				jMainSplitPane.setResizeWeight(1);
				jMainSplitPane.setDividerSize(2);
				getContentPane().add(jMainSplitPane, BorderLayout.CENTER);
				{
					jScrollPane1 = new JScrollPane();
					jMainSplitPane.add(jScrollPane1, JSplitPane.RIGHT);
					jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					{
						jItemListPane = new JPanel();
						BorderLayout jItemListPaneLayout = new BorderLayout();
						jItemListPane.setLayout(jItemListPaneLayout);
						jScrollPane1.setViewportView(jItemListPane);
						{
							MyItemListTableModel myItemListTableModel = new MyItemListTableModel();
							myItemListTableModel.initFromFile(new File(itemsFileName));
							myItemListTable = new MyItemListTable();
							BorderLayout myItemListTableLayout = new BorderLayout();
							myItemListTable.setLayout(myItemListTableLayout);
							myItemListTable.setModel(myItemListTableModel);
							myItemListTable.setRowSelectionAllowed(true);
							myItemListTable.setColumnSelectionAllowed(false);
							myItemListTable.setForeground(new java.awt.Color(0, 0, 0));
							myItemListTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							myItemListTable.changeSelection(0, 0, false, false);
							jItemListPane.add(myItemListTable, BorderLayout.CENTER);
						}
					}
				}
				{
					jImagePane = new MyImagePane();
					jMainSplitPane.add(jImagePane, JSplitPane.LEFT);
					jImagePane.setPreferredSize(new java.awt.Dimension(514, 423));
					jImagePane.addMouseMotionListener(new MouseMotionAdapter()
					{
						public void mouseMoved(MouseEvent evt)
						{
							jImagePaneMouseMoved(evt);
						}

						public void mouseDragged(MouseEvent evt)
						{
							jImagePaneMouseDragged(evt);
						}
					});
					jImagePane.addMouseListener(new MouseAdapter()
					{
						public void mouseReleased(MouseEvent evt)
						{
							jImagePaneMouseReleased(evt);
						}

						public void mousePressed(MouseEvent evt)
						{
							jImagePaneMousePressed(evt);
						}
					});
				}
			}
			setSize(1024, 768);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("New");
						newFileMenuItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								newFileMenuItemActionPerformed(evt);
							}
						});
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
						openFileMenuItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								openFileMenuItemActionPerformed(evt);
							}
						});
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Save");
						saveMenuItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								saveMenuItemActionPerformed(evt);
							}
						});
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenu3.add(saveAsMenuItem);
						saveAsMenuItem.setText("Save As ...");
						saveAsMenuItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								saveAsMenuItemActionPerformed(evt);
							}
						});
					}
					{
						closeFileMenuItem = new JMenuItem();
						jMenu3.add(closeFileMenuItem);
						closeFileMenuItem.setText("Close");
						closeFileMenuItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								closeFileMenuItemActionPerformed(evt);
							}
						});
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
						exitMenuItem.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent evt)
							{
								exitMenuItemActionPerformed(evt);
							}
						});
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("Edit");
					{
						cutMenuItem = new JMenuItem();
						jMenu4.add(cutMenuItem);
						cutMenuItem.setText("Cut");
					}
					{
						copyMenuItem = new JMenuItem();
						jMenu4.add(copyMenuItem);
						copyMenuItem.setText("Copy");
					}
					{
						pasteMenuItem = new JMenuItem();
						jMenu4.add(pasteMenuItem);
						pasteMenuItem.setText("Paste");
					}
					{
						jSeparator1 = new JSeparator();
						jMenu4.add(jSeparator1);
					}
					{
						deleteMenuItem = new JMenuItem();
						jMenu4.add(deleteMenuItem);
						deleteMenuItem.setText("Delete");
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public JSplitPane getJMainSplitPane()
	{
		return jMainSplitPane;
	}

	private void newFileMenuItemActionPerformed(ActionEvent evt)
	{
		System.out.println("newFileMenuItem.actionPerformed, event=" + evt);
		// TODO add your code for newFileMenuItem.actionPerformed
	}

	private void openFileMenuItemActionPerformed(ActionEvent evt)
	{
		System.out.println("openFileMenuItem.actionPerformed, event=" + evt);
		openFile();
	}

	private void saveMenuItemActionPerformed(ActionEvent evt)
	{
		System.out.println("saveMenuItem.actionPerformed, event=" + evt);
		// TODO add your code for saveMenuItem.actionPerformed
	}

	private void saveAsMenuItemActionPerformed(ActionEvent evt)
	{
		System.out.println("saveAsMenuItem.actionPerformed, event=" + evt);
		// TODO add your code for saveAsMenuItem.actionPerformed
	}

	private void closeFileMenuItemActionPerformed(ActionEvent evt)
	{
		System.out.println("closeFileMenuItem.actionPerformed, event=" + evt);
		// TODO add your code for closeFileMenuItem.actionPerformed
	}

	private void exitMenuItemActionPerformed(ActionEvent evt)
	{
		System.out.println("exitMenuItem.actionPerformed, event=" + evt);
		// TODO add your code for exitMenuItem.actionPerformed
	}

	private void jImagePaneMousePressed(MouseEvent evt)
	{
		// System.out.println("jImagePane.mousePressed, event="+evt);

		// check this is point or line or segline
		int row = myItemListTable.getSelectedRow();
		MyItemListTableElement e = ((MyItemListTableModel) myItemListTable.getModel()).getElementAt(row);

		if (e.getType() == MyItemListTableElement.TYPE_POINT)
		{
			// if point
			int x = evt.getX();
			int y = evt.getY();
			String name = x + "," + y;
			jImagePane.addPoint(name, x, y);
			jImagePane.repaint();

			e.setObject(new Point(x, y));
			((MyItemListTableModel) myItemListTable.getModel()).put(e);

			if (row < myItemListTable.getRowCount() - 1)
				myItemListTable.changeSelection(row + 1, 0, false, false);
			else
				myItemListTable.changeSelection(1, 0, false, false);
		} else if (e.getType() == MyItemListTableElement.TYPE_LINE || e.getType() == MyItemListTableElement.TYPE_SCALE)
		{
			// if line
			dragStartX = evt.getX();
			dragStartY = evt.getY();
		} else if (e.getType() == MyItemListTableElement.TYPE_MULTILINE)
		{
			// if multiLine
			int x = evt.getX();
			int y = evt.getY();
			// left button clicked
			if (evt.getButton() == evt.BUTTON1)
			{
				// start multi line
				if (currentMultiLine == null)
				{
					currentMultiLine = new MultiLine();
					currentMultiLine.moveTo(x, y);
					currentMultiLine.addPoint(new Point(x, y));
				}
				// in-multiline
				else
				{
					currentMultiLine.lineTo(x, y);
					currentMultiLine.moveTo(x, y);
					currentMultiLine.addPoint(new Point(x, y));
					jImagePane.removeLine("temp");
					jImagePane.addMultiLine(e.getName(), currentMultiLine);
					jImagePane.repaint();
				}
			}// if right-click = end multiline
			else if (evt.getButton() == evt.BUTTON3)
			{
				if (currentMultiLine != null)
				{
					currentMultiLine.lineTo(x, y);
					currentMultiLine.moveTo(x, y);
					currentMultiLine.addPoint(new Point(x, y));
					jImagePane.addMultiLine(e.getName(), currentMultiLine);
					jImagePane.removeLine("temp");
					jImagePane.repaint();

					e.setObject(currentMultiLine);
					((MyItemListTableModel) myItemListTable.getModel()).put(e);

					if (row < myItemListTable.getRowCount() - 1)
						myItemListTable.changeSelection(row + 1, 0, false, false);
					else
						myItemListTable.changeSelection(1, 0, false, false);

					currentMultiLine = null;
				}
			}
		}
	}

	private void jImagePaneMouseReleased(MouseEvent evt)
	{
		// System.out.println("jImagePane.mouseReleased, event="+evt);
		int row = myItemListTable.getSelectedRow();
		MyItemListTableElement e = ((MyItemListTableModel) myItemListTable.getModel()).getElementAt(row);

		// if line
		if (e.getType() == MyItemListTableElement.TYPE_LINE || e.getType() == MyItemListTableElement.TYPE_SCALE)
		{
			if (dragStartX != -1 && dragStartY != -1)
			{
				int endX = evt.getX();
				int endY = evt.getY();
				jImagePane.addLine(e.getName(), dragStartX, dragStartY, endX, endY);
				jImagePane.repaint();

				e.setObject(new Line2D.Double(dragStartX, dragStartY, endX, endY));
				((MyItemListTableModel) myItemListTable.getModel()).put(e);
				myItemListTable.repaint();

				if (row < myItemListTable.getRowCount() - 1)
					myItemListTable.changeSelection(row + 1, 0, false, false);
				else
					myItemListTable.changeSelection(1, 0, false, false);

				dragStartX = -1;
				dragStartY = -1;
			}
		}
	}

	private void jImagePaneMouseDragged(MouseEvent evt)
	{
		// System.out.println("jImagePane.mouseDragged, event="+evt);
		int row = myItemListTable.getSelectedRow();
		MyItemListTableElement e = ((MyItemListTableModel) myItemListTable.getModel()).getElementAt(row);

		// if line
		if (e.getType() == MyItemListTableElement.TYPE_LINE || e.getType() == MyItemListTableElement.TYPE_SCALE)
		{
			int endX = evt.getX();
			int endY = evt.getY();
			jImagePane.addLine(e.getName(), dragStartX, dragStartY, endX, endY);
			jImagePane.repaint();
		}
	}

	private void jImagePaneMouseMoved(MouseEvent evt)
	{
		// System.out.println("jImagePane.mouseMoved, event="+evt);
		// if multiLine
		if (currentMultiLine != null)
		{
			Point2D p = currentMultiLine.getCurrentPoint();
			int startX = (int) p.getX();
			int startY = (int) p.getY();
			int endX = evt.getX();
			int endY = evt.getY();
			jImagePane.addLine("temp", startX, startY, endX, endY);
			jImagePane.repaint();
		}
	}
	
	private void openFile()
	{
		// clear old data and lines
		jImagePane.clear();
		((MyItemListTableModel)myItemListTable.getModel()).initFromFile(new File(itemsFileName));
		myItemListTable.changeSelection(0, 0, false, false);
		// get previously choosed file path
		Preferences userPrefs = Preferences.userRoot().node("h4nsolo.gmail.com.dental.modelanalysis");
		File suggestedFile = new File( userPrefs.get("SAVEDIR","C:"));
		
		// make fc
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new JpgFileFilter());
		fileChooser.setSelectedFile(suggestedFile);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			// save choosed file path
			userPrefs.put("SAVEDIR", fileChooser.getSelectedFile().getAbsolutePath());
			
			// open file
			try
			{
				BufferedImage img = ImageIO.read(fileChooser.getSelectedFile());
				jImagePane.setImage(img);
				this.update(getGraphics());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
