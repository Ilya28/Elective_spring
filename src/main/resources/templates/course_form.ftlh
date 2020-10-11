<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add course</title>
    <@header.all/>
    <link href="css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar role = "guest"/>

<p></p>
<br style="clear:both">

<div class="col-md-8 col-md-offset-4">
    <h4>Add course</h4>
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
        <div class="form-group col-md-6 ">
            <input type="number" name="seats" id="seats" class="form-control" min="1" max="1000" value="16" value="">
        </div>
        <div class="form-group col-md-6 ">
            <label id="date_startLabel" for="date_start">Start date</label>
            <input type="date" name="date_start" id="date_start">
        </div>
        <div class="form-group col-md-6 ">
            <label id="date_endLabel" for="date_end">End date</label>
            <input type="date" name="date_end" id="date_end">
        </div>

        <div class="form-group col-md-6 ">
            <label id="descriptionLabelEN" for="descriptionEN">Description (EN)</label>
            <textarea class="form-control input-sm " type="textarea" id="descriptionEN" placeholder="Your text" maxlength="1000" rows="8"></textarea>
            <span class="help-block"><p id="characterLeft" class="help-block ">You have reached the limit</p></span>
        </div>

        <div class="form-group col-md-6 ">
            <label id="descriptionLabelUA" for="descriptionUA">Опис (УКР)</label>
            <textarea class="form-control input-sm " type="textarea" id="descriptionUA" placeholder="Ваш текст" maxlength="1000" rows="8"></textarea>
            <span class="help-block"><p id="characterLeft" class="help-block ">You have reached the limit</p></span>
        </div>

        <div class="form-group col-md-2">
            <button class="form-control input-sm btn btn-success disabled" id="btnSubmit" name="btnSubmit" type="button" style="height:35px">Add</button>
        </div>
    </form>
</div>



</body>
</html>