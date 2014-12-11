/**
 * 
 */
function Player() {
	this.posX = 0;
	this.posY = 0;
	this.size = 10;
	this.speed = 20;
};

Player.prototype.setPosX = function(x) {
	this.posX = x;
}

Player.prototype.setPosY = function(y) {
	this.posY = y;
}

Player.prototype.getPosX = function() {
	return this.posX;
}

Player.prototype.getPosY = function() {
	return this.posY;
}

Player.prototype.getSize = function() {
	return this.size;
}

Player.prototype.getSpeed = function() {
	return this.speed;
}