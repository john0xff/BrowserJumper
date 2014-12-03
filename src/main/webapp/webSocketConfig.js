/**
 * 
 */
window.onload = function init() 
{
	var Game = {};

	Game.webSocket = null;
	
	Game.webSocket = new WebSocket('ws://' + window.location.host + '/BrowserJumper/websocket/connector');
	
	Game.webSocket.onopen = function () 
	{
		console.log("onopen");
		// Game.webSocket.send('ping');
	}

	Game.webSocket.onclose = function () 
	{
		
	}

	Game.webSocket.onmessage = function () 
	{
		
	}
};