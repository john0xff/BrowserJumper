package connection;

import java.io.IOException;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/connector")
public class WebSocketConnector
{
	private Session session;

	@OnOpen
	public void onOpen(Session session)
	{
		this.session = session;
		System.out.println("onOpen");
	}

	@OnMessage
	public void onTextMessage(String message)
	{
		System.out.println("onTextMessage -> " + message);

		try
		{
			session.getBasicRemote().sendText("pong to client");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
//	@OnMessage
//	public void onMapMessage(Map message)
//	{
//		System.out.println("onTextMessage -> " + message);
//
//		try
//		{
//			session.getBasicRemote().sendText("pong to client");
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}

	@OnClose
	public void onClose()
	{
		System.out.println("onClose");
	}

	@OnError
	public void onError(Throwable t) throws Throwable
	{

	}
}