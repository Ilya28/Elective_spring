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
<@navbar.navbar role = "guest"/>
<div class="col-md-8 col-md-offset-4">
    <h2>Some error</h2>
</div>
</body>
</html>