

<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>我的主页 - Mtons</title>
    <meta name="keywords" content="mtons,mtons,博客,社区,摄影,旅游,艺术,娱乐"/>
    <meta name="description" content="Mtons, 轻松分享你的兴趣. 便捷的文字、图片发布,扁平化的响应式设计,美观、大气,是您记录生活的最佳选择"/>
<meta property="mtons:mblog" content="2.2.1">

<script src="/assets/vendors/pace/pace.min.js"></script>
<link href="/assets/vendors/pace/themes/pace-theme-minimal.css" rel="stylesheet" />

<link rel='stylesheet' media='all' href='/assets/vendors/font-awesome/css/font-awesome.min.css'/>
<link rel="stylesheet" media='all' href="/assets/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" media='all' href="/assets/vendors/animate/animate.min.css">
<link rel='stylesheet' media='all' href='/assets/css/style.css'/>
<link rel='stylesheet' media='all' href='/assets/css/layout.css'/>
<link rel='stylesheet' media='all' href='/assets/css/plugins.css'/>
<link rel='stylesheet' media='all' href='/assets/css/addons.css'/>

<link rel='stylesheet' media='all' href="/assets/vendors/baguette/baguetteBox.min.css"/>

<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/assets/js/utils.js"></script>

<script type="text/javascript" src="/assets/vendors/layer/layer.js"></script>

<script type="text/javascript" src="/assets/js/sea.js"></script>
<script type="text/javascript" src="/assets/js/sea.config.js"></script>

<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" href="http://mtons.com/dist/images/logo.png"/>
<link rel="shortcut icon" href="http://mtons.com/dist/images/logo.png"/>
<script type="text/javascript" src="/assets/vendors/bootstrap/js/bootstrap.min.js"></script> 
<script type="text/javascript">
    var _base_path = '$!{base}';

    window.app = {
        base: '',
        LOGIN_TOKEN: '$!{profile.id}'
    };
	
	window.UEDITOR_HOME_URL = '/assets/vendors/ueditor/';
</script>
</head>
<body>



<!-- Header BEGIN -->
<#include "/inc/header.ftl">
<!-- Header END -->
    <!--.site-main -->
    <div class="wrap" id="wrap">
        <div class="container">
            <div class="row">
                <div class="main clearfix">
                    <!-- left -->
                    <#include "/inc/home-left.ftl">

                    <div class="col-xs-12 col-md-9 side-right">

<div class="shadow-box">
	<div class="filter">
		<ul class="">
			<li><a class="active" href="/home/favors">我的喜欢</a></li>
		</ul>
	</div>
	<!-- tab panes -->
	<div class="stream-list">
	  <#list voList as item>
		<div class="stream-item" id="loop-17">
				<div class="p-rank clearfix">
					<div class="users">
						<a href="/ta/${item.id}">
							<div class="ava">
    <img class="img-circle" src="${item.avatar}"/>
							</div>
							<div class="info">
								<strong>${item.title}</strong>
								<time>${item.created?string('yyyy-MM-d HH:mm:ss')}</time>
							</div>
						</a>
					</div>
					<div class="counts">
						<span class="act"><i class="praise_icon"></i>${item.articleCount}</span>
						<span class="act"><i class="comment_icon"></i>${item.commentCount}</span>
					</div>
				</div>
				<div class="summary">
					<a href="/view/${item.id}">
						<div class="title"><h2>${item.title}</h2></div>
						<div class="excerpt wordbreak hidden-xs">${item.content} </div>
					</a>
					<!--前端图片显示样式-->

					<div class="foot-block clearfix">
						<ul class="tags">
						</ul>
                        <div class="pull-right hidden-xs">
                            <a class="act" href="javascript:void(0);" data-evt="unfavor" data-id="17">取消喜欢</a>
                        </div>
					</div>
				</div>
		</div>
		
		
		
</#list>
<div class="stream-item" id="loop-17">
				<div class="p-rank clearfix">
					<div class="users">
						<a href="/ta/4">
							<div class="ava">
    <img class="img-circle" src="/assets/images/ava/2.jpg"/>
							</div>
							<div class="info">
								<strong>东方不败</strong>
								<time>1小时前</time>
							</div>
						</a>
					</div>
					<div class="counts">
						<span class="act"><i class="praise_icon"></i>2</span>
						<span class="act"><i class="comment_icon"></i>0</span>
					</div>
				</div>
				<div class="summary">
					<a href="/view/17">
						<div class="title"><h2>Forword(请求转发)与 Redirect(重定向)</h2></div>
						<div class="excerpt wordbreak hidden-xs">1.从数据共享上 Forword 是一个请求的延续，可以共享 request 的数据 Redirect 开启一个新的请求，不可以共享 request 的数据&nbsp; 2.从地址栏 Forword 转发地址栏不发生变化 Redirect 转发地... </div>
					</a>
					<!--前端图片显示样式-->

					<div class="foot-block clearfix">
						<ul class="tags">
						</ul>
                        <div class="pull-right hidden-xs">
                            <a class="act" href="javascript:void(0);" data-evt="unfavor" data-id="17">取消喜欢</a>
                        </div>
					</div>
				</div>
		</div>

	</div>
</div>
<div class="text-center clr">

<ul class="pagination">

    <li class="active"><a href="javascript:void(0);"><span>1</span></a></li>

</ul>
</div>

<script type="text/javascript">
$(function() {
	$('a[data-evt=unfavor]').click(function () {
		var id = $(this).attr('data-id');

		layer.confirm('确定删除此项吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
			jQuery.getJSON('/account/unfavor', {'id': id}, function (ret) {
				layer.msg(ret.message, {icon: 1});
				if (ret.code >=0) {
					$('#loop-' + id).fadeOut();
					$('#loop-' + id).remove();
				}
			});

        }, function(){

        });
	});
})
</script>
                    </div>
                </div>
            </div>
        </div>
    </div>
<footer>
	<div class="footer-nav">
		<div class="container">
			<ul class="about-ul list-inline clearfix">
				<li><a href="/about">关于我们</a></li>
				<li><a href="/joinus">联系我们</a></li>
				<li><a href="/faqs">常见问题</a></li>
                
			</ul>
		</div>
	</div>
	<div class="container mode-link">
        <h3 class="t-h3">友情链接</h3>
        <ul class="list-inline">
            <li><a href="http://www.mtons.com" target="_blank" title="Mtons社区">Mtons社区</a></li>
        </ul>
    </div>
	<div class="container copy-right">
		<span> <a
			href="http://www.miitbeian.gov.cn/" target="_blank"></a>
		</span>
		<span class="pull-right">Powered By <a href="http://mtons.com/?copyright" target="_blank">Mtons</a></span>
	</div>

</footer>

<a href="#" class="site-scroll-top"></a>

<script type="text/javascript">
    seajs.use('main', function (main) {
        main.init();
    });
</script></body>
</html>
