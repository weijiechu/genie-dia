package pku.deviceInformationAccess.locationProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import pku.deviceInformationAccess.location.Coordinates;
import pku.deviceInformationAccess.location.Location;
import pku.deviceInformationAccess.location.StayInZone;
import pku.deviceInformationAccess.location.Zone;
import pku.deviceInformationAccess.terminal.Bluetooth;
import pku.deviceInformationAccess.terminal.Terminal;
import pku.deviceInformationAccess.terminal.WiFiEquipment;

public class BlipLocationProvider implements LocationProvider
{
	String url;
	String userName;
	String password;
	Connection conn;
	Map<Zone,TerminalsInZone> terminalsOfAllZones = new ConcurrentHashMap<Zone,TerminalsInZone>();
	public BlipLocationProvider(String url, String userName, String password) 
	{
		super();
		this.url = url;
		this.userName = userName;
		this.password = password;
		try
		{
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			this.conn=DriverManager.getConnection(url, userName, password);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BlipLocationProvider(String arg)
	{
		super();
		DataSource ds;
		Context ctx;
		try
		{
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(arg);
			
			this.conn = ds.getConnection();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		
		
	}
	public Set<Terminal> getAllTerminals(Zone zoneInfo)
	{
		PreparedStatement select;
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		//首先，动态生成表名
		String tableName =  "BEFound"+date;
		//查询最新的BatchNum
		int BatchNum = -2;
		String sql0 = "SELECT MAX(BatchPerNode) as max FROM "+tableName;
		try
		{
			select = conn.prepareStatement(sql0);
			ResultSet rs= select.executeQuery();
			if(rs.next())
			{
				BatchNum= rs.getInt("max");
				//*****************************
				//System.out.println("Now BatchNum = "+BatchNum);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		if(!terminalsOfAllZones.containsKey(zoneInfo))//如果这是该Zone的第一次查询
		{
			//System.out.println("first");
			TerminalsInZone tiz = new TerminalsInZone();
			tiz.batchNum = BatchNum;
			terminalsOfAllZones.put(zoneInfo,tiz);//则向记录中插入新的记录
		}
		else//不是第一次查询
		{
			TerminalsInZone tiz = terminalsOfAllZones.get(zoneInfo);
			//System.out.println("tiz.batchNum = "+tiz.batchNum);
			if(BatchNum>tiz.batchNum)//新插入了信息
			{
				tiz.batchNum = BatchNum;
				String sql = "Select BluetoothEquipmentInfo.ID,FriendlyName,MAC FROM "+tableName+",BluetoothEquipmentInfo"
				+" WHERE "+tableName+".ZoneID = "+zoneInfo.getZoneID()
				+" AND BEFound"+date+".BluetoothEquipmentID = BluetoothEquipmentInfo.ID"
				+ " AND BatchPerNode = "+ BatchNum;//如果server没有搜索到蓝牙设备，则不会返回信息
				//System.out.println(sql);
			try
			{
				select = conn.prepareStatement(sql);
				ResultSet rs= select.executeQuery();
				
				tiz.terminals = new HashSet<Terminal>();//使用新的Set
				while(rs.next())
				{
					int id = rs.getInt("ID");
					String friendlyName = rs.getString("FriendlyName");
					//System.out.println(friendlyName);
					String mac = rs.getString("MAC");
					Terminal bt = new Bluetooth(friendlyName,id,mac);
					//System.out.println(friendlyName);
					tiz.terminals.add(bt);//一次添加本次的记录
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			}
			else//没有新的查询信息
			{
				;
			}
			
		}
		/*try
		{
			conn.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		TerminalsInZone tiz = terminalsOfAllZones.get(zoneInfo);
		return tiz.terminals;
	}

	public Set<Zone> getAllZone()
	{
		String sql = "SELECT * FROM ZoneInfo";
		Set<Zone> set = new HashSet<Zone>();
		try
		{
			PreparedStatement select = conn.prepareStatement(sql);
			ResultSet rs= select.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("ID");
				String zoneDescription = rs.getString("ZoneDescription");
				Zone z = new Zone(id,zoneDescription);
				set.add(z);
				
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try
		{
			conn.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return set;
	}

	public Location getLocation(Terminal terminal)
	{
		PreparedStatement select;
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		//首先，动态生成表名
		String tableName =  "BEFound"+date;
		//查询最新的BatchNum
		int BatchNum = -1;
		String sql = "SELECT MAX(BatchPerNode) as max FROM "+tableName;
		//System.out.println(sql);
		try
		{
			select = conn.prepareStatement(sql);
			ResultSet rs= select.executeQuery();
			if(rs.next())
			{
				BatchNum= rs.getInt("max");
				//System.out.println("Now BatchNum = "+BatchNum);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		sql = "SELECT Time,ZoneID,X,Y FROM "+tableName+
		" WHERE BatchPerNode ="+BatchNum+" AND BluetoothEquipmentID = "+terminal.getId();
		System.out.println(sql);
		try
		{
			select = conn.prepareStatement(sql);
			ResultSet rs= select.executeQuery();
			while(rs.next())
			{
				Date time = rs.getDate("Time");
				int zoneID = rs.getInt("ZoneID");
				double x = rs.getFloat("X");
				double y = rs.getFloat("Y");
				Coordinates c = new Coordinates(x,y);
				Zone z = this.getZone(zoneID);
				Location l = new Location(z,c,time);
				return l;
				
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Set<Terminal> getTerminalByName(String name)
	{
		Set<Terminal> result = new HashSet<Terminal>();
		PreparedStatement select;
		String sql ="Select ID,FriendlyName,MAC from BluetoothEquipmentInfo where FriendlyName = '"+name+"'";
		try
		{
			select = conn.prepareStatement(sql);
			ResultSet rs= select.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("ID");
				String friendlyName = rs.getString("FriendlyName");
				String mac = rs.getString("MAC");
				Terminal t = new Bluetooth(friendlyName,id,mac);
				result.add(t);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public List<StayInZone> getTerminalPath(Terminal terminal)
	{
		List<StayInZone> path = new LinkedList();
		Bluetooth bt =(Bluetooth)terminal;
		PreparedStatement select;
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		//首先，动态生成表名
		String tableName =  "BEFound"+date;
		String sql = "Select ZoneID,Time,X,Y from "+tableName+",ZoneInfo where BluetoothEquipmentID ="
					+bt.getId()+" and ZoneInfo.ID = "+tableName+".ZoneID";
		try
		{
			select = conn.prepareStatement(sql);
			ResultSet rs= select.executeQuery();
			while(rs.next())
			{
				int id = rs.getInt("ZoneID");
				Date time = rs.getDate("Time");
				double x = rs.getFloat("X");
				double y = rs.getFloat("Y");
				Zone z = this.getZone(id);
				Coordinates c = new Coordinates(x,y);
				Location l = new Location(z,c,time);
				if(path.isEmpty())//如果是空
				{
					StayInZone siz = new StayInZone();
					siz.setEnterLocation(l);
					siz.setLeaveLoaction(l);
					path.add(siz);
					continue;
				}
				else//如果不空
				{
					int last = path.size()-1;//最后一个
					StayInZone lastStayInZone = path.get(last);
					Location lastZone = (Location) lastStayInZone.getEnterLocation();
					if(lastZone.getZone().getZoneID() == id)
					{
						lastStayInZone.setLeaveLoaction(l);
						continue;
					}
					else
					{
						StayInZone siz = new StayInZone();
						siz.setEnterLocation(l);
						siz.setLeaveLoaction(l);
						path.add(siz);
						continue;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.toString();
		}
		/*try
		{
			conn.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return path;
	}

	public Zone getZone(int id)
	{
		String sql = "SELECT ID,ZoneDescription FROM ZoneInfo WHERE ID = "+id;
		int superZoneID=0;
		Zone z=null;
		try
		{
			PreparedStatement select = conn.prepareStatement(sql);
			ResultSet rs= select.executeQuery();
			while(rs.next())
			{
				String zoneDescription = rs.getString("ZoneDescription");
				z = new Zone(id,zoneDescription);
				
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return z;
	}

}
