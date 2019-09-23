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
            //发送ajax
            $.ajax({
                url: rootPath + "/user/login",
                data: {
                    userName: userName,
                    password: password,
                    storePwd:$("input[name='storePwd']").val()
                },
                success: function (datas) {
                    if (datas == "success") {
                        //用户名和密码正确
                    } else {
                        // 说明用户名或密码错误
                        errmsg = "账号或密码错误";
                        //显示登录错误信息
                        errmsgHtml(true,errmsg)
                    }
                },
                error: function (datas) {
                },
                type: "post"
            })
        }else{
            errmsgHtml(true,errmsg);
        }
    })
    $("input[name='storePwd']").on("click",function(){
        layer.msg($(this))
    })

    function errmsgHtml(show,errmsg) {
        if (!show) {
            $(".l_err").hide();
        } else {
            $(".err_msg").html(errmsg);
            $(".l_err").show();
        }
    }
})