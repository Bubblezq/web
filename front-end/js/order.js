window.onload = function(){
    var xmlhttp=new XMLHttpRequest();
    var html=document.getElementById("table").innerHTML;
    
    xmlhttp.open("post","http://localhost:8087/myOrder/",true);
    

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    var username=getCookie("username");
    //var username='zhang';
    console.log(username);
    xmlhttp.onreadystatechange=function(e){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log(this.response);
            var data=JSON.parse(this.response);
            console.log(data);
            for(var i=0;i<data.length;i++){
                html+=[
                '<tr>\
                    <td><a><img src='+data[i].picture+'></a><h4>'+data[i].productname+'</h4>\</td>\
                    <td>'+data[i].unitprice+'</td>\
                    <td>'+data[i].amount+'</td>\
                    <td>'+data[i].unitprice*data[i].amount+'元</td>\
                </tr>',
                ];
            }
            document.getElementById("table").innerHTML+=html;
        }
    }
    xmlhttp.send("username="+username);
}
function getCookie(cname){
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
	}
	return "";
}