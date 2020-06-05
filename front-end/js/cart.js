window.onload = function(){
    var xmlhttp=new XMLHttpRequest();
    xmlhttp.open("post","http://localhost:8087/cart/",true);
    

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    var username=getCookie("username");
    //var username='zhang';
    console.log(username);
    xmlhttp.onreadystatechange=function(e){
        if(xmlhttp.readyState==4 && xmlhttp.status==200){
            console.log(this.response);
            var data=JSON.parse(this.response);
            console.log(data);
            var html = document.getElementById("carItem").innerHTML;
            for(var i=0;i<data.length;i++){
                html+=[
                    '<tr class="cart-item"><td class="product-name" data-title="Product">\
                            <input type="checkbox" name="CouponID" onclick="IsCheched('+data[i].productid,data[i].unitprice+')">\
                            <a href="#" class="cart-product-thumb">\
                                <img src="'+data[i].picture+'" alt="Product Thumbnail">\
                            </a>\
                            <div class="product-info">\
                                <h4>'+data[i].productname+'</h4>\
                            </div>\
                        </td>\
                        <td class="product-price" data-title="Price">\
                               <span class="product-Price-amount amount">'+data[i].unitprice+'</span>\
                        </td>\
                        <td class="product-quantity" data-title="Quantity">\
                            <div class="quantity">\
                                <input type="number" id="amount" class="input-text" step="1" min="1" value="1">\
                            </div>\
                        </td>\
                        <td class="product-subtotal" data-title="Total">\
                            <span class="product-Price-amount amount">'+data[i].unitprice+'</span>\
                        </td>\
                        <td class="product-remove" data-title="Remove">\
                            <a href="#" class="remove" title="Remove this item">\
                                <img class="icon" aria-hidden="true" src="images/remove.png">\
                            </a>\
                        </td>\
                    </tr>'
                ]
            }
            document.getElementById("carItem").innerHTML+=html;
        }
    }
    xmlhttp.send("username="+username);
}

var total=0;
var productid=0;

function IsCheched(product,price){
    total+=price;
    productid=product;
    document.getElementById("total").innerHTML="Total: "+total+" 元";
}

function pay()
{
    var xmlhttp=new XMLHttpRequest();
    
    xmlhttp.open("post","http://localhost:8087/pay/",true);

    xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    var username=getCookie("username");
    //var username='zhang';
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