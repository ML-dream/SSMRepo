

var ar2;
var ar;
  function toebom(product_id,issue_num,lot,sortie){
	  // top.window.location.href="bom_maitn.jsp?product_id="+product_id+"&issue_num="+issue_num+"&lot="+lot;
	  /*
	  if(lot=="1"){ document.getElementById("test").readOnly=true;
	  }else{
	  document.getElementById("test").readOnly=true;}
	   *///alert("!");
	  var b ="<%=a%>" ;
	  alert(b);
	  
	   }
  
 function testtable(){
	  var t = document.getElementById("table_test");
	  var newrow = t.insertRow();
	  var newcell =  newrow.insertCell();
	  var newcell2 = newrow.insertCell();
	   ar = new Array();
	  ar[0]="l";
	  ar[1]="u";
	  ar[2]="c";
	  ar2 ="d";
	  newcell.innerHTML = "<a onclick = 'deltable(ar);'>test</a>";
	  newcell2.innerHTML = "<a onclick = 'deltable(ar2);'>test2</a>";
  }
 
 function deltable(a){
	 alert("entered!"+ a[0]+a[1]+a[2]);
	/* var getform =  document.getElementById("do_type") ;
	 getform.Value = a;
 */
	 document.forsubmit.do_type = a ;
	 
 }