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
	
	
}
