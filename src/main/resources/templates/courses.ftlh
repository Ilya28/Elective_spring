<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <@header.all/>
    <link href="css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar role = "guest"/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">
<div class="col-md-8 col-md-offset-4">
    <h4>Courses</h4>
</div>
<div class="container">
    <div class="row">
        <ul class="thumbnails list-unstyled">

            <#list subjects as subject>
            <li class="col-md-3">
                <div class="thumbnail" style="padding: 0">
                    <div style="padding:4px">
                        <img alt="300x200" style="width: 100%" src="http://placehold.it/200x150">
                    </div>
                    <div class="caption">
                        <h2>Project C</h2>
                        <p>My project description</p>
                        <p><i class="icon icon-map-marker"></i> Place, Country</p>
                    </div>
                </div>
            </li>
            </#list>

        </ul>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col col-xs-4">Page 1 of 5
        </div>
        <div class="col col-xs-8">
            <ul class="pagination hidden-xs pull-right">
                <#list 1..5 as i>
                    <#if i == 2>
                        <li class="active"><a href="#">${i}<span class="sr-only">(current)</span></a></li>
                    <#else>
                        <li><a href="#">${i}</a></li>
                    </#if>
                </#list>
            </ul>
            <ul class="pagination visible-xs pull-right">
                <li><a href="#">«</a></li>
                <li><a href="#">»</a></li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>