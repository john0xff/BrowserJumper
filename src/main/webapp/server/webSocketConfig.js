/**
 * 
 */
	function Connector() {
		//this.webSocket = null;
	}

	Connector.prototype.connectWebSocket = function()  
	{
           
        if ('WebSocket' in window) 
        {
        	this.webSocket = new WebSocket('ws://' + window.location.host + '/BrowserJumper/websocket/connector');
        } 
        
        this.webSocket.onopen = function () 
        {
        	console.log('Info: WebSocket connection opened.');
        	Console.println('Info: WebSocket connection opened.');
        };
        
        // 2
        // need to receive all positions from server before redraw
        this.webSocket.onmessage = function (serverData) 
        {
        	console.log('Received: ' + serverData.data);
        	Console.println('Received: ' + serverData.data);
        	// 3
        	// call update positions of players 
        };
        
        this.webSocket.onclose = function (event) 
        {
        	console.log('Info: WebSocket connection closed, Code: ' + event.code);
        	Console.println('Info: WebSocket connection closed, Code: ' + event.code);
        };
    }
	
	// 1
	// sending new position to server before redraw
	Connector.prototype.sendMessage = function(message) 
	{
        if (this.webSocket != null) 
        {
        	this.webSocket.send(message);
            console.log('Sent: ' + message);
            Console.println('Sent: ' + message);
        } 
    }
	
	Connector.prototype.closeWebSocket = function()
	{
		if (this.webSocket != null) 
		{
			this.webSocket.close();
			this.webSocket = null;
        }
	}

	