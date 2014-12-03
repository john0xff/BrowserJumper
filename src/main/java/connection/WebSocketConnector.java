package connection;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/connector")
public class WebSocketConnector
{

	@OnOpen
	public void onOpen(Session session)
	{
		System.out.println("onOpen");
	}

	@OnMessage
	public void onTextMessage(String message)
	{

	}

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
