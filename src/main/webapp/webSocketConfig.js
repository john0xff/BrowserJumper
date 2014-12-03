/**
 * 
 */
	var webSocket = null;

	function connect() 
	{
           
        if ('WebSocket' in window) 
        {
        	webSocket = new WebSocket('ws://' + window.location.host + '/BrowserJumper/websocket/connector');
        } 
        
        webSocket.onopen = function () 
        {
        	console.log('Info: WebSocket connection opened.');
        };
        
        webSocket.onmessage = function (serverData) 
        {
        	console.log('Received: ' + serverData.data);
        };
        
        webSocket.onclose = function (event) 
        {
        	console.log('Info: WebSocket connection closed, Code: ' + event.code);
        };
    }
	
	function sendPing() 
	{
        if (webSocket != null) 
        {
        	message = "ping to server";
            console.log('Sent: ' + message);
            
            webSocket.send(message);
        } 
    }
	
	function closeWebSocket()
	{
		if (webSocket != null) 
		{
			webSocket.close();
			webSocket = null;
        }
	}

	