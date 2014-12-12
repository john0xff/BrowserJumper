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
        
//        // 2
//        // need to receive all positions from server before redraw
        this.webSocket.onmessage = function (serverData) 
        {
        	console.log('Received: ' + serverData.data);
        	Console.println('Received: ' + serverData.data);
        	// 3
        	// call update positions of players 
        };
        
        
     // each time when new msg from server
        this.webSocket.onmessage = function(message) 
        {
    		
    		var packet = JSON.parse(message.data);
    		console.log(packet.data);
    		
    		switch (packet.type) 
    		{
    			// {"type": "update", "data" : [{"id":0,"body":[{"x": 10, "y": 140}]}]}
	    		case 'update':
	    			for (var i = 0; i < packet.data.length; i++) 
	    			{
	    				//Game.updateSnake(packet.data[i].id, packet.data[i].body);
	    			}
	    			break;
	    		
	    		case 'join':
	    			for (var j = 0; j < packet.data.length; j++) 
	    			{
	    				Game.addSnake(packet.data[j].id, packet.data[j].color);
	    			}
	    			break;
	    		
	    		case 'leave':
	    			Game.removeSnake(packet.id);
	    			break;
	    		
	    		case 'dead':
	    			Console.log('Info: Your snake is dead, bad luck!');
	    			Game.direction = 'none';
	    			break;
	    		
	    		case 'kill':
	    			Console.log('Info: Head shot!');
	    			break;
    		}
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

	