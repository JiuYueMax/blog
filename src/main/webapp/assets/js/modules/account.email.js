define(function(require, exports, module) {
	require('validate');
	require('bootstrap');
	require('layer');

	$("#f_email").validate({
		//debug:false,//只验证,不提交
		rules : {
			email : {
				required : true,/*这个事必须的下面那个是最小长度*/
				email:true
			}
		},
		messages : {
			email : {
				required : "请输入电子邮件",
				email:'邮箱格式错误'
			}
		},
		errorPlacement: function(error,element){
			element.popover('destroy');
			element.popover({
				content:error[0].innerHTML
			});
			element.click();
			element.closest('div').removeClass('has-success').addClass('has-error');
		},
		success:function(a,b) {
			$(b).parent().removeClass('has-error').addClass('has-success');
			$(b).popover('destroy');
		},
		submitHandler:function(form){//验证通过,执行这里
			var layer1 = layer.msg('正在验证邮箱,请稍后...',{
				icon: 16,
				shade: 0.5,
				time:0
			});
			
			
			$.ajax({
				   type: "POST",
				   url: "/account/email/change",
				   data: $("#f_email").serialize(),
				   success: function(data){
					  	 layer.close(layer1);
					  		 if(data.code==300){
							layer.msg('新邮箱不能与旧邮箱相同',function(){});
						}
						if(data.code==100){
							layer.msg('邮箱修改成功',{icon: 6});
							$('#f_email').reset();
							
						} 
				   },
				   error:function (XMLHttpRequest, textStatus, errorThrown) {
					   layer.close(layer1);
					   layer.msg('服务器通讯错误',{icon:5});
					}
				});
			
    	}  
	});
	
	
	exports.init=function(){
		console.log('account.email初始化...');
		//f_email.init();
	}
});