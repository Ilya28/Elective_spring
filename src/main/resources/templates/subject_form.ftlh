<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add subject</title>
    <@header.all/>
    <link href="/css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">

<div class="col-md-8 col-md-offset-4">
    <h4>Add subject</h4>
</div>


<div class="container">
    <form class="form-control" method="post" action="#">
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <input type="text" name="name" id="name" class="form-control" placeholder="Course name" value="">
        </div>
        <div class="form-group col-md-6 ">
            <input type="text" name="mapping" id="mapping" class="form-control" placeholder="Mapping" value="">
        </div>
        <div class="form-group col-md-2">
            <button class="form-control input-sm btn btn-success disabled" id="btnSubmit" name="btnSubmit" type="button" style="height:35px">Add</button>
        </div>
    </form>
</div>


</body>
</html>