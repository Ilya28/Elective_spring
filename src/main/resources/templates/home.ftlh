<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
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
<br style="clear:both">
<br style="clear:both">
<br style="clear:both">

</body>
</html>