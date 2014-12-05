package connection;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.swing.JButton;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.tomcat.websocket.server.WsRemoteEndpointImplServer;

// To check impl
// WsRemoteEndpointImplServer s;

@ServerEndpoint(value = "/websocket/connector")
public class WebSocketConnector
{
	private Session session;
	JButton a;
	
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

//		try
//		{
//			session.getBasicRemote().sendText("pong to client");
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
	}

	@OnClose
	public void onClose()
	{
		try
		{
			this.session.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("onClose");
	}

	@OnError
	public void onError(Throwable t) throws Throwable
	{

	}
}
