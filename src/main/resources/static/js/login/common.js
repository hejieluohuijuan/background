var USER_COOKIE = "userCookie";
$(function () {
    //隐藏登录错误信息
    errmsgHtml(false);
    $("input[type='button']").on("click", function () {
        var userName = $("input[name='userName']").val();
        var password = $("input[name='password']").val();
        var errmsg = "";
        var check = true;
        if (userName.trim() == "") {
            check = false;
            errmsg += "用户名不能为空"
        }
        if (password.trim() == "") {
            check = false;
            errmsg += "密码不能为空";
        }
        if (check) {
            var storePwd="false";
            if($("input[name='storePwd']").length>0){
                //该标签存在
                storePwd=$("input[name='storePwd']").next().hasClass("layui-form-onswitch") ? "true" : "false"
            }else{
                storePwd=$(".img_head").find("div").hasClass("savePwd")? "true" : "false";
            }
            //发送ajax
            $.ajax({
                url: rootPath + "/user/login",
                data: {
                    userName: userName,
                    password: password,
                    storePwd: storePwd
                },
                success: function (datas) {
                    if (datas == "success") {
                        //用户名和密码正确
                    } else {
                        // 说明用户名或密码错误
                        errmsg = "账号或密码错误";
                        //显示登录错误信息
                        errmsgHtml(true, errmsg)
                    }
                },
                error: function (datas) {
                },
                type: "post"
            })
        } else {
            errmsgHtml(true, errmsg);
        }
    })
    function errmsgHtml(show, errmsg) {
        if (!show) {
            $(".l_err").hide();
        } else {
            $(".err_msg").html(errmsg);
            $(".l_err").show();
        }
    }
    window.onload = function () {
        //切换列表跳转到对应的页面
        $("select[name='loginModel']").next().find("dd").on("click", function () {
            var modelName = $("select[name='loginModel']").val();
            if(modelName!=null && modelName!=''){
                $.ajax({
                    url: rootPath + "/switchLogin",
                    data: {modelName: modelName},
                    success: function (datas) {
                        window.location.href = rootPath + "/login"
                    },
                    type:"post"
                })
            }
        })
        //判读是否记住我
        var cookie = document.cookie.split(";");
        function getCK(mkey) {
            for (var i = 0; i < cookie.length; i++) {
                var kv = cookie[i].split("=");
                if (kv[0].trim() == mkey) {
                    return kv[1].trim();
                }
            }
            return '';
        }
        var userNamePwd = getCK(USER_COOKIE);
        if (userNamePwd != "" && userNamePwd != null) {
            //模式1
            $("input[name='storePwd']").next().addClass("layui-form-onswitch");
            $("input[name='storePwd']").attr("checked", true);
            //模式2
            $(".img_head").find("div").addClass("savePwd");
            $(".savePwd").css({"background-image": "url(static/images/icon/ok.png)", "background-size": "100% 100%"});
        }
    }
})