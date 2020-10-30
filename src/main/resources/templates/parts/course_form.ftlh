<#macro course_form>

<div class="container">
    <div class="row">

    <form role="form" style="display: block; background-color:rgba(0,0,0,0) !important;" method="post"
          action="${add_url}" autocomplete="off">
        <div class="form-group col-md-6 ">
            <label id="nameLabelEN" for="nameEN">${msg("courses.add.name_en")}</label>
            <input type="text" name="nameEN" id="nameEN" class="form-control" placeholder="Course name" value="">
        </div>
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <label id="nameLabelUA" for="nameUA">${msg("courses.add.name_ua")}</label>
            <input type="text" name="nameUA" id="nameUA" class="form-control" placeholder="Назва курсу" value="">
        </div>
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <label id="seatsLabel" for="seats">${msg("courses.add.seats")}</label>
            <input type="number" name="seats" id="seats" class="form-control" min="1" max="1000" value="16" value="">
        </div>
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <label id="date_startLabel" for="date_start">${msg("courses.add.start_date")}</label>
            <br style="clear:both">
            <input type="date" name="dateStart" id="date_start">
            <span class="glyphicon glyphicon-calendar"></span>
        </div>
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <label id="date_endLabel" for="date_end">${msg("courses.add.end_date")}</label>
            <br style="clear:both">
            <input type="date" name="dateEnd" id="date_end">
            <span class="glyphicon glyphicon-calendar"></span>
        </div>
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <label id="descriptionLabelEN" for="descriptionEN">${msg("courses.add.description_en")}</label>
            <textarea class="form-control input-sm " id="descriptionUA" name="descriptionEN" placeholder="Your text" maxlength="1000" rows="8"></textarea>
        </div>
        <br style="clear:both">
        <div class="form-group col-md-6 ">
            <label id="descriptionLabelUA" for="descriptionUA">${msg("courses.add.description_ua")}</label>
            <textarea class="form-control input-sm " id="descriptionUA" name="descriptionUA" placeholder="Ваш текст" maxlength="1000" rows="8"></textarea>
        </div>
        <br style="clear:both">
        <div class="form-group col-md-2">
            <input type="submit"
                   class="form-control input-sm btn btn-success"
                   style="height:35px"
                   value="${msg("btn.add")}">
        </div>
    </form>

    </div>
</div>

</#macro>