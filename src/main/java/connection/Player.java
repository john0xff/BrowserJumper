package connection;

import java.awt.Point;

import javax.websocket.Session;


public class Player
{
	private final int id;
	private final Session session;
	private Point point;
	
	public Player(int id, Session session)
	{
		this.id = id;
		this.session = session;
	}

	public int getId()
	{
		return id;
	}

	public Session getSession()
	{
		return session;
	}

	public Point getPoint()
	{
		return point;
	}

	public void setPoint(Point point)
	{
		this.point = point;
	}
	
	public synchronized String getLocationsJson()
	{
		StringBuilder sb = new StringBuilder();
//		sb.append(String.format("{\"x\": %d, \"y\": %d}", Integer.valueOf(head.x), Integer.valueOf(head.y)));
//		for (Location location : tail)
//		{
//			sb.append(',');
//			sb.append(String.format("{\"x\": %d, \"y\": %d}", Integer.valueOf(location.x), Integer.valueOf(location.y)));
//		}
		return String.format("{\"id\":%d,\"body\":[%s]}", Integer.valueOf(id), sb.toString());
	}
}
