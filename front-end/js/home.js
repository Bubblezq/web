//加载商品数据
window.onload = function(){
    var xmlhttp=new XMLHttpRequest();
    
    xmlhttp.open("post","http://localhost:8087/product/",true);
    

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttp.onreadystatechange=function(e){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log(this.response);
            var data=JSON.parse(this.response);
            console.log(data);
            var container = document.getElementById("productItemContainer").innerHTML;
            for(var i=0;i<data.length;i++){
            container+=[
                '<li>\
                <div class="product-item col-grid-4 top-space">\
                    <div class="product-item-wrapper zoom-effect-hover-container box-shadow-block">\
                        <div class="product-thumb zoom-effect">\
                            <a class="thumbnail" href="#"><img src="'+data[i].picture+'"></a>\
                            <div class="pruduct-buttons">\
                                <a class="product-button tooltip">\
                                    <i class="iconfont icon-gouwuchekong" onclick=addCart('+data[i].productid+')></i>\
                                    <span class="tooltiptext tooltip-right">Add To Cart</span>\
                                </a>\
                            </div>\
                            <h3 class="product-title" id="name" title="title">'+data[i].productname+'</h3>\
                            <div class="product-price-container">\
                                <span class="fix-price">'+data[i].unitprice+'元</span>\
                            </div>\
                        </div>\
                    </div>\
                </div>\
                </li>'
            ];
            }
            document.getElementById("productItemContainer").innerHTML+=container;
        }
    }

   xmlhttp.send();
}

function addCart(productid){
    var xmlhttp=new XMLHttpRequest();
    
    xmlhttp.open("post","http://localhost:8087/addCart/",true);

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    //var username=getCookie("username");
    var username='zhang';
    console.log(username);
    xmlhttp.onreadystatechange=function(e){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log(this.response);
            var data=JSON.parse(this.response);
            console.log(data);
            alert(data.msg);
        }
    }
    xmlhttp.send("productid="+productid+"&username="+username);
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