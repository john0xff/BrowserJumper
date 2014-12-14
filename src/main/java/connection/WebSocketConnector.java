package connection;

import java.awt.Point;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import utils.Json;

@ServerEndpoint(value = "/websocket/connector")
public class WebSocketConnector
{
	private Session session;
	private String[] msg = new String[4];

	private static final AtomicInteger playerIds = new AtomicInteger(0);

	private int id;
	private Player player;

	private static final ConcurrentHashMap<Integer, Player> players = new ConcurrentHashMap<>();

	public void addPlayer(Player player)
	{
		WebSocketConnector.players.put(player.getId(), player);
	}

	public WebSocketConnector()
	{
		// each time increment
		this.id = playerIds.getAndIncrement();
	}

	protected static Collection<Player> getPlayers()
	{
		return Collections.unmodifiableCollection(players.values());
	}

	@OnOpen
	public void onOpen(Session session)
	{
		System.out.println("onOpen");
		this.player = new Player(id, session);

		this.player.setPoint(new Point(0, 0));
		addPlayer(this.player);

	}

	/**
	 * When something goes wrong in this method webSocket is close from tomcat core. Not via @OnClose method.
	 * 
	 * @param message
	 */
	@OnMessage
	public void onTextMessage(String message)
	{
		// System.out.println("onTextMessage -> " + message);

//		ClientMessageTypes.readMessage(message);
//		
//		msg = message.split(" ");
//
//		System.out.println(msg[0] + " = " + msg[1]);
//		System.out.println(msg[2] + " = " + msg[3]);
//
//		int x = Integer.valueOf(msg[1]);
//		int y = Integer.valueOf(msg[3]);
//		this.player.setPoint(new Point(x, y));

		// System.out.println(message);

		// System.out.println("x = ");
		// System.out.println("y = ");

		// broadcasting in new thrad

	//	StringBuilder sb = new StringBuilder();

//		for (Iterator<Player> iterator = getPlayers().iterator(); iterator.hasNext();)
//		{
//			Player player = iterator.next();
//			// sb.append(String.format("{\"id\": %d}", player.getId()));
//			// sb.append(String.format("{\"x\": %d}", player.getPoint().x));
//			// sb.append(String.format("{\"y\": %d}", player.getPoint().y));
//
//			// {"type": "update", "data" : [{"id":0,"body":[{"x": 10, "y": 140}]}]}
//			//sb.append("{\"type\": \"update\", \"data\" : [{\"id,\":" + player.getId() + "\"body\":[{\"x\": " + x + ",\"y\": " + y + "}]}]}");
//
//			sb.append("{\"type\": \"update\", \"data\" : [{\"id\":0,\"body\":[{\"x\": 10, \"y\": 140}]}]}");
//			
//			// Snake snake = iterator.next();
//			// sb.append(String.format("{\"id\": %d, \"color\": \"%s\"}", Integer.valueOf(snake.getId()),
//			// snake.getHexColor()));
//			// if (iterator.hasNext())
//			// {
//			// sb.append(',');
//			// }
//		}
		
	//	sb.append("{\"type\": \"update\", \"data\" : [{\"id\":0,\"body\":[{\"x\": 10, \"y\": 140}]}]}");
		
		StringBuilder json = new StringBuilder();
//		
		json.append(Json.as);
//		json.append(Json.ob);
//		/**
//		 * prepare json for sent
//		 */
		for (Iterator<Player> iterator = getPlayers().iterator(); iterator.hasNext();)
		{
			Player player = iterator.next();
			
			
			json.append(Json.ob);
			json.append(Json.qt + "playerId" + Json.qt + Json.cn + Json.qt + player.getId() + Json.qt);
			json.append(Json.oe);
			
			if(iterator.hasNext())
			{
				json.append(Json.cm);
			}
		}
		json.append(Json.ad);
//		json.append(Json.oe);
//		json.append(")");
		
		/**
		 * sent json for all connected players
		 */
		for (Iterator<Player> iterator = getPlayers().iterator(); iterator.hasNext();)
		{
			Player player = iterator.next();
			
			try
			{
				player.getSession().getBasicRemote().sendText(json.toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	// @OnMessage
	// public void onMapMessage(Map message)
	// {
	// System.out.println("onTextMessage -> " + message);
	//
	// try
	// {
	// session.getBasicRemote().sendText("pong to client");
	// }
	// catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	// }

	@OnClose
	public void onClose()
	{
		WebSocketConnector.players.remove(this.player.getId());

		System.out.println("onClose");
	}

	@OnError
	public void onError(Throwable t) throws Throwable
	{

	}

}