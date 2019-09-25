$(function () {
    //动态获取图片
    $.ajax({
        url: rootPath + "/resourceData/headImg",
        success: function (imgPath) {
            $(".img_head").css({"background-image": "url(static/" + imgPath + ")", "background-size": "100% 100%"});
        },
        type: "get"
    })
    $(".img_head").on("click", function () {
        $(this).find("div").toggleClass("savePwd");
        $(".savePwd").css({"background-image": "url(static/images/icon/ok.png)", "background-size": "100% 100%"});
    })
})