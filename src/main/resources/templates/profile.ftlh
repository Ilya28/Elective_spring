<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${msg("profile.title")}</title>
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

            <#if message??>
                <div class="container" style="margin-top: 8px">
                    <div class="col-md-6 col-md-offset-3">
                        <h4>${message}</h4>
                    </div>
                </div>
            </#if>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>${msg("profile.title")}</h3>
                    <h4> ${msg("user.title")}: ${user.name}</h4>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <div class="list-group-item">${msg("user.name")}:    ${user.name}</div>
                        <div class="list-group-item">${msg("user.email")}:   ${user.email}</div>
                        <div class="list-group-item">${msg("user.role")}:    ${user.role?keep_after("_")}</div>
                    </div>

                    <hr class="col-12"/>

                    <a class="btn btn-sm btn-primary" href="${uri}/modify" role="button">${msg("btn.modify")}</a>
                </div>

            </div>

        </div>
    </div>
</div>

</body>
</html>