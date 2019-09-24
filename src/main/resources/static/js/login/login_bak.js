$(function(){
    //动态获取图片
    $.ajax({
        url:rootPath+"/resourceData/headImg",
        success:function(imgPath){
            console.log(imgPath)
            $(".img_head").css({"background-image":"url(static/"+imgPath+")","background-size":"100% 100%"});
        },
        type:"get"
    })

})