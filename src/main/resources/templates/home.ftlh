<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<#import "parts/home_options.ftlh" as home>
<#import "parts/pager.ftlh" as p>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${msg("home.title")}</title>
    <@header.all/>
    <link href="/css/registration_form.css" rel="stylesheet">
    <script type="text/javascript" src="/js/registration_action.js"></script>
    <script type="text/javascript" src="/js/registration_form.js"></script>
</head>
<body>
<@navbar.navbar/>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3> ${msg("home.title")}</h3>
                </div>
                <div class="panel-body">

                    <#if message??>
                        <div class="container" style="margin-top: 8px">
                            <div class="col-md-6 col-md-offset-3">
                                <h4>${message}</h4>
                            </div>
                        </div>
                    </#if>

                    <#if role=="ROLE_USER">
                        <@home.home_registrations/>
                    <#elseif role=="ROLE_TEACHER">
                        <@home.home_courses/>
                    <#else>
                        Admin
                    </#if>

                </div>

        </div>
    </div>
</div>

</body>
</html>