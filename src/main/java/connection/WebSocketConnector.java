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
		
		// update position of player
		
		// update items on map
		
//		StringBuilder json = new StringBuilder();
////		
//		json.append(Json.as);
////		json.append(Json.ob);
////		/**
////		 * prepare json for sent
////		 */
//		for (Iterator<Player> iterator = getPlayers().iterator(); iterator.hasNext();)
//		{
//			Player player = iterator.next();
//			
//			
//			json.append(Json.ob);
//			json.append(Json.qt + "playerId" + Json.qt + Json.cn + Json.qt + player.getId() + Json.qt);
//			json.append(Json.oe);
//			
//			if(iterator.hasNext())
//			{
//				json.append(Json.cm);
//			}
//		}
//		json.append(Json.ad);
//		json.append(Json.oe);
//		json.append(")");
		
		/**
		 * sent json for all connected players
		 */
		for (Iterator<Player> iterator = getPlayers().iterator(); iterator.hasNext();)
		{
			Player player = iterator.next();
			
			if(this.player == player)
				continue;
			
			try
			{
				player.getSession().getBasicRemote().sendText(message);
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