<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <@header.all/>
</head>
<body>
<@navbar.navbar/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">

<div class="col-md-8 col-md-offset-4">
    <h1>Some error</h1>
    <h3>${error_message}</h3>
</div>
</body>
</html>