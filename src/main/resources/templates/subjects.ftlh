<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <@header.all/>
    <link href="/css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">
<br style="clear:both">
<div class="container">
    <div class="row">
        <ul class="thumbnails list-unstyled">

            <div class="col-md-10 col-md-offset-1">

                <#if message??>
                    <div class="container" style="margin-top: 8px">
                        <div class="col-md-6 col-md-offset-3">
                            <h4>${message}</h4>
                        </div>
                    </div>
                </#if>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>${msg("courses.table.subject")}</h3>
                    </div>

                    <div class="panel-body">
                        <#list subjects as subject>
                            <li class="col-md-3">
                                <div class="thumbnail" style="padding: 0">
                                    <a href="${subject.mapping}">
                                        <div style="padding:4px">
                                            <img alt="300x200" style="width: 100%" src="http://placehold.it/200x150">
                                        </div>
                                        <div class="caption">
                                            <h3>${subject.name}</h3>
                                            <!-- <p>My project description</p>
                                            <p><i class="icon icon-map-marker"></i> Place, Country</p> -->
                                        </div>
                                    </a>
                                </div>
                            </li>
                        </#list>
                    </div>

                </div>
            </div>

        </ul>
    </div>
</div>

</body>
</html>