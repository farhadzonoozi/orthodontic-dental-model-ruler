package h4nsolo.gmail.com.dental.modelanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;

import javax.swing.table.AbstractTableModel;

public class MyItemListTableModel extends AbstractTableModel
{
	LinkedHashMap<String, MyItemListTableElement> table;
	MyItemListTableElement scale;

	public MyItemListTableModel()
	{
		table = new LinkedHashMap<String, MyItemListTableElement>();
		scale = new MyItemListTableElement(MyItemListTableElement.TYPE_SCALE, "scale");
		put(scale);
	}

	public void put(MyItemListTableElement e)
	{
		table.put(e.getName(), e);
	}

	public void remove(MyItemListTableElement e)
	{
		table.remove(e.getName());
	}

	public int getColumnCount()
	{
		return 2;
	}

	public int getRowCount()
	{
		return table.size();
	}

	public MyItemListTableElement getElementAt(int row)
	{
		if (row < 0)
			row = 0;
		MyItemListTableElement e = (MyItemListTableElement) table.values().toArray()[row];
		return e;
	}
	
	public Object getValueAt(int row, int col)
	{
		if (row < 0)
			row = 0;
		MyItemListTableElement e = (MyItemListTableElement) table.values().toArray()[row];
		
		if(col == 0) 
		{
			return e.getName();
		}
		else if(col == 1) 
		{
			if( e.getType() == MyItemListTableElement.TYPE_SCALE )
			{
				return (double)Math.round(e.getDistance()*100)/100;
			}
			else
			{
				return (double)Math.round((e.getDistance()/scale.getDistance()*100)*100)/100;
			}
		}
		
		return null;
	}
	
	public double getScale()
	{
		return scale.getDistance();
	}

	public void initFromFile(File f)
	{
		try
		{
			clear();
			BufferedReader r = new BufferedReader(new FileReader(f));
			String line = null;

			while ((line = r.readLine()) != null)
			{
				String[] lines = line.split(",");
				String name = lines[0];
				int type = -1;
				if (lines[1].equals("POINT"))
					type = MyItemListTableElement.TYPE_POINT;
				if (lines[1].equals("LINE"))
					type = MyItemListTableElement.TYPE_LINE;
				if (lines[1].equals("MULTILINE"))
					type = MyItemListTableElement.TYPE_MULTILINE;

				MyItemListTableElement e = new MyItemListTableElement(type, name);
				put(e);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void clear()
	{
		table.clear();
		put(scale);
	}
}
