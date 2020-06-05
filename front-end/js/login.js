function Login() {
    var xmlhttp=new XMLHttpRequest();
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;

    xmlhttp.open("post","http://localhost:8087/user/login/",true);

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log(xmlhttp.response);
            var res=JSON.parse(this.response);
            console.log(res.success)
            if(res.success==false)
            {
                alert("用户名或密码错误");
            }else{
                alert("登录成功")
                username=res.data.username
                //cookie保存用户id
                document.cookie="username="+escape(username);
                var userid=getCookie("username");
                console.log(userid);
                window.location.href='home.html';
            }
        }
    }

    xmlhttp.send("username="+username+"&password="+password);
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
