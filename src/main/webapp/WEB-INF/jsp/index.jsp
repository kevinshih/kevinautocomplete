<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8"/>

    <title>Auto-Complete大亂鬥！ V.0.1.0 (Beta)</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>


</head>
<body>

<h3>Auto-Complete 大亂鬥！ V.0.1.0 (Beta)</h3>
<form>
    <div class="form-group row">
        <label for="searchinput" class="col-sm-2 col-form-label">搜尋</label>
        <div class="col-sm-10">
            <div class="input-group mb-3">
                <input type="text" class="form-control" id="searchinput">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="search()">
                        search
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="row" id="result">

</div>
<script>

  function search() {
    var value = $("#searchinput").val();
     for(var i=0;i<entities.length;i++) {
      entities[i].query(value, entities[i].getName());
   }

  }

  $("#searchinput").on('keypress',function(e) {
    if(e.which == 13) {
        e.preventDefault();
        search();
    }
  });



//速度太快，會導致pchome, momo 認為是爬蟲
//var timerId;
//$('#searchinput').keyup(function(){
//    clearTimeout(timerId);
//    if (this.value.length > 0) {
//          timerId = setTimeout(search(), 2000);
//    }
//});

// define ECEntity

function ECEnity(myName) {
    this.name=myName;
};

ECEnity.prototype.setName = function(context) {
    this.name=context;
};

ECEnity.prototype.getName = function() {
    return this.name;
};

ECEnity.prototype.query = function(context, theName) {
    var url=window.location.protocol + "//" + window.location.hostname;
    if(window.location.port != "" && window.location.port != "80"  && window.location.port != "443") {
       url += ":"+window.location.port;
    }

    if(window.location.pathname.length >1) {
        url+= window.location.pathname.substring(0, window.location.pathname.indexOf("/",1))+"/";
    }

    url += "/autocomplete?entity=AEntityA&query=AkeywordA";

    var querystr = url.replace("AkeywordA",context);
    var querystr = querystr.replace("AEntityA", theName);
    console.log("querystr:"+querystr);
    $("#"+theName+"_hd").text(theName+" loading...");
    $.ajax(
        {
            url: querystr,
            cache: false,
            timeout:10*1000,
            success: function(html){
              $("#"+theName).html( html );
              $("#"+theName+"_hd").text(theName);
            },
            error: function( req, status, err ) {
              $("#"+theName+"_hd").text(theName+" failed... "+status+", "+err);
              console.log( 'something went wrong', status, err );
            }
        }
    );

};



// initial
    entities = new Array();

    //entities.push(new ECEnity("umall"));
    //entities.push(new ECEnity("umallori"));
    entities.push(new ECEnity("etmall"));
    entities.push(new ECEnity("momo"));
    entities.push(new ECEnity("pchome"));
    //entities.push(new ECEnity("yahoo"));
    entities.push(new ECEnity("shopee"));
    //entities.push(new ECEnity("udn"));

  for(var x=0;x<entities.length;x++) {
    $("#result").append("<div class='card' style='width: 18rem;'><div class='card-header' id='"+entities[x].getName()+"_hd'>"+entities[x].getName()+"</div><div id="+entities[x].getName()+">"+entities[x].getName()+"</div></div>");
  }



</script>

</body>
</html>