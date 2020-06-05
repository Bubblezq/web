function Regist() {
    var xmlhttp=new XMLHttpRequest();
    var username=document.getElementById("username").value;
    var password=document.getElementById("password").value;

    xmlhttp.open("post","http://localhost:8087/user/regist/",true);

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.onreadystatechange=function(){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log(xmlhttp.response);
            var res=JSON.parse(this.response);
            console.log(res.success)
            if(res.success==false)
            {
                alert(res.msg);
            }else{
                alert("注册成功")
                window.location.href='login.html';
            }
        }
    }

    xmlhttp.send("username="+username+"&password="+password);
}

