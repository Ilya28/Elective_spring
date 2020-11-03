<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${msg("login.title")}</title>
    <@header.all/>
    <link href="/css/registration_form.css" rel="stylesheet">
    <script type="text/javascript" src="/js/registration_action.js"></script>
    <script type="text/javascript" src="/js/registration_form.js"></script>
    <script>
        $(document).ready ( function() {
            $('#login-form-link').addClass('active');
            $("#login-form").fadeIn(100);
            $("#register-form").fadeOut(100);
            e.preventDefault();
        });
    </script>
</head>
<body>
<@navbar.navbar/>

<div class="container" style="margin-top: 0px">
    <div class="col-md-6 col-md-offset-3">
        <h3>Please login or register</h3>
    </div>
</div>

<!--<#if login_error?? || registration_success?? || logout??>
<div class="container" style="margin-top: 8px">
    <div class="col-md-6 col-md-offset-3">
        <#if login_error??>
            <h4 class="text-danger"> null (todo)</h4>
        <#elseif registration_success??>
            <h4 class="text-success">null (todo)</h4>
        <#elseif logout??>
            <h4 class="text-warning">null (todo)</h4>
        </#if>
    </div>
</div>
</#if>-->

<#if message??>
    <div class="container" style="margin-top: 8px">
        <div class="col-md-6 col-md-offset-3">
            <h4>${message}</h4>
        </div>
    </div>
</#if>

<@form.login_form/>

</body>
</html>