$(function(){
/*$("input[type='submit']").on("click",function(){
})*/
// 在键盘按下并释放及提交后验证提交表单
    $("#formGrid").validate({
        rules: {
            userName: "required",
            password: "required",
            password: {
                required: true,
                minlength: 4
            },
            password: {
                required: true,
                minlength: 6
            }
        },
        messages: {
            userName: "",
            password: "",
            username: {
                required: "",
                minlength: ""
            },
            password: {
                required: "",
                minlength: ""
            }
        }
    });
});

$.validator.setDefaults({
    submitHandler: function() {
        $.ajax({
            url:"/user/login",
            data:{
                userName:$("input[name='userName']"),
                password:$("input[name='password']")
            },
            dataType:"json",
            success:function (data) {
              alert(data)
            },
            async:"true"
        })
    }
});