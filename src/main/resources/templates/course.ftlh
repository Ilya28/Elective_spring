<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <@header.all/>
    <link href="css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">
<br style="clear:both">

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

                    <h2>${course.name}</h2>
                    <#if status=="open">
                        <span class="badge badge-success">${msg("course.open")}</span>
                    <#elseif status=="in_progress">
                        <span class="badge badge-success">${msg("course.in_progress")}</span>
                    <#elseif status=="completed">
                        <span class="badge badge-success">${msg("course.completed")}</span>
                    </#if>
                        </div>
                    <#if role=="ROLE_ADMIN">
                        <div class="text-right">
                            <a class="btn btn-sm btn-primary" href="course_${course.id}/delete" role="button">${msg("btn.delete")}</a>
                        </div>
                    </#if>

                <div class="panel-body">
                    ${course.description}
                    <br style="clear:both">
                    <div class="list-group">
                        <div class="list-group-item">${msg("courses.table.start")}:    ${course.dateStart}</div>
                        <div class="list-group-item">${msg("courses.table.end")}:      ${course.dateEnd}</div>
                        <div class="list-group-item">${msg("courses.table.seats")}:    ${course.signedUp}/${course.seats}</div>
                    </div>

                    <hr class="col-12" />

                    <#if registered==true>
                        <#if status=="open">
                            <a class="btn btn-warning" href="${uri}/register/cancel" role="button">${msg("course.cancel_enrollment")}</a>
                        <#elseif status=="in_progress">
                            <a class="btn btn-warning" href="${uri}/register/cancel" role="button">${msg("course.leave")}</a>
                        </#if>
                    <#else>
                        <#if status=="open">
                            <a class="btn btn-primary" href="${uri}/register" role="button">${msg("course.sign_up")}</a>
                        <#else>
                            <button type="button" class="btn btn-secondary" disabled>${msg("course.registration_not_available")}</button>
                        </#if>
                    </#if>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>