$(function () {
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
            errmsg = "密码不能为空";
        }
        if (check) {
            //发送ajax
            $.ajax({
                url: rootPath + "/user/login",
                data: {
                    userName: userName,
                    password: password
                },
                success:function (datas) {
                    layer.msg(datas)
                },
                error:function(datas){
                    alert(datas)

                },
                dataType:"json",
                type:"post"
            })
        }
    })
})