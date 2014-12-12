/**
 * 
 */
	var hGameArea;
	var hCanvas;
	var context;
		
	var connector = new Connector();
	var player = new Player();
	var map = new Map();
	//var console = new Console();
	
	var keys = [];
	
	window.onload = function init() 
	{
		hCanvas = document.getElementById("canvas");

		context = canvas.getContext("2d");
		gameLoop();
	}

	window.addEventListener("keydown", function(e) {
		keys[e.keyCode] = true;
	});
	
	window.addEventListener("keyup", function(e) {
		keys[e.keyCode] = false;
	});
	
	
	
	///////////////////////////////
	///// Server - start
	//////////////////////////////
	
	// open web socket connection
	function openConnection()
	{
		connector.connectWebSocket();
	}
	
	function sendMessage()
	{

    	message = document.getElementById("message").value;
    	
		connector.sendMessage(message);
	}
	
	// close web socket connection
	function closeConnection()
	{
		connector.closeWebSocket();
	}
	
	///////////////////////////////
	///// Server - end
	//////////////////////////////

	function gameLoop() 
	{
		movement();

		//this.context.clearRect(0, 0, map.getHeight(), map.getHeight());
		hCanvas.width = map.getWidth();
		hCanvas.height = map.getHeight();
		
		
		this.context.fillRect(player.getPosX(), player.getPosY(), player.getSize(), player.getSize());
		
		window.requestAnimationFrame(gameLoop); // fps 60x - The number of callbacks is usually 60 times per second
		
		// window.webkitRequestAnimationFrame(gameLoop);
		// window.mozRequestAnimationFrame(gameLoop);
		
    };

	function movement() 
	{
		keyEvents();
		checkWalls();
	}
	
	/*function onClickEvent(moveDirection)
	{
		var posX = player.getPosX();
		var posY = player.getPosY();
		
		if(moveDirection == "moveLeft")
		{
			posX -= player.getSpeed();
		}
		
		if(moveDirection == "moveUp")
		{
			posY -= player.getSpeed();
		}
		
		if(moveDirection == "moveRight")
		{
			posX += player.getSpeed();
		}
		
		if(moveDirection == "moveDown")
		{
			posY += player.getSpeed();
		}
		
		player.setPosX(posX);
		player.setPosY(posY);
	}*/
	
	
	function keyEvents()
	{
		var posX = player.getPosX();
		var posY = player.getPosY();
		
		if (keys[37]) // left arrow
		{
			//console.log(posX);
			posX -= player.getSpeed();
			positionToServer(posX, posY);
		}
		
		if (keys[38]) // up arrow
		{
			posY -= player.getSpeed();
			positionToServer(posX, posY);
		}

		if (keys[39]) // right arrow
		{
			posX += player.getSpeed();
			positionToServer(posX, posY);
		}
		
		if (keys[40]) // down arrow
		{
			posY += player.getSpeed();
			positionToServer(posX, posY);
		}
		
		player.setPosX(posX);
		player.setPosY(posY);
		
		
	}
	
	function positionToServer(posX, posY)
	{
		message = "x " + posX + " " + "y " + posY;
		connector.sendMessage(message);
	}
	
	function checkWalls()
	{
		var posX = player.getPosX();
		var posY = player.getPosY();
		
		if(posX <= 0)
		{
			posX = 0;
			//console.log(posX);
		}
		
		if(posX >= (map.getWidth() - player.getSize())) // player redraw from top-left pixel that's why need to map - size
		{
			posX = (map.getWidth() - player.getSize());
			//console.log("s");
		}
		
		if(posY <= 0)
		{
			posY = 0;
			//console.log("s");
		}
		
		if(posY >= (map.getHeight() - player.getSize())) // player redraw from top-left pixel that's why need to map - size
		{
			posY = (map.getHeight() - player.getSize());
			//console.log("s");
		}
		
		
		//connector.sendMessage(message);
		
		player.setPosX(posX);
		player.setPosY(posY);
	}
	
	///////////////////////////////
	///// Console
	//////////////////////////////
	
	
	
	
	
	