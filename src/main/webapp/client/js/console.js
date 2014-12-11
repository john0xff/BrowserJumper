
//function Console(){
//	
//}
//
//Console.prototype.println = function(message) {
//	
//	hConsole = document.getElementById("console");
//	
//	hConsole.innerHtml += message;
//}

function consolePrintln() {
	
//	var para = document.createElement("P");
//    var t = document.createTextNode("This is a paragraph.");
//    para.appendChild(t);
//    hConsole = document.getElementById("console");
//    hConsole.appendChild(para);
//    
//    //hConsole.scrollTo(500, 0);
//    
//    hConsole.scrollTop = hConsole.scrollHeight;
	
	Console.println("test");
}

var Console = {};

Console.println = (function(message) {
	
	var console = document.getElementById('console');
	var p = document.createElement('p');
	p.style.wordWrap = 'break-word';
	p.innerHTML = message;
	
	console.appendChild(p);
	
//	while (console.childNodes.length > 25) {
//		console.removeChild(console.firstChild);
//	}
	
	console.scrollTop = console.scrollHeight;
});