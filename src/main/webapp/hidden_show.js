function hidden_table(){
	document.getElementById("add_parts_table").style.display = "none";
	document.getElementById("add").style.display="";
}
function show_table(){
	document.getElementById("add_parts_table").style.display="";
	document.getElementById("add").style.display="none";
}

function show_processes_table(){
	document.getElementById("add_processes_table").style.display="";
	document.getElementById("add_processes").style.display="none";
}

function hidden_processes_table(){
	document.getElementById("add_processes_table").style.display="none";
	document.getElementById("add_processes").style.display="";
}
function add_process(){
	document.getElementById("addpro_button").style.display="none";//
	document.getElementById("addprocess").style.display="";//
	//document.getElementById("addprocessplan").style.display="none";//
}
function cancleaddprocess(){
	document.getElementById("addpro_button").style.display="";//
	document.getElementById("addprocess").style.display="none";
	//document.getElementById("addprocessplan").style.display="none";
}








