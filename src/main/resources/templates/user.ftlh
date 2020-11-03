<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<#import "parts/pager.ftlh" as p>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <@header.all/>
    <link href="css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar/>

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
                    <h3> ${msg("user.title")}: ${user.name}</h3>
                </div>
                <div class="panel-body">

                    <div class="list-group">
                        <div class="list-group-item">${msg("user.name")}:    ${user.name}</div>
                        <div class="list-group-item">${msg("user.email")}:   ${user.email}</div>
                        <div class="list-group-item">${msg("user.role")}:    ${user.role?keep_after("_")}</div>
                        <div class="list-group-item">${msg("user.blocked")}:
                            <#if user.blocked==true>
                                ${msg("user.blocked")}
                            <#else>
                                ${msg("user.active")}
                            </#if>
                        </div>
                    </div>
                    <#if role=="ROLE_ADMIN">
                        <div>
                            <a class="btn btn-sm btn-danger" href="user_${user.id}/delete" role="button">${msg("btn.delete")}</a>
                            <a class="btn btn-sm btn-warning" href="user_${user.id}/modify" role="button">${msg("btn.modify")}</a>
                        </div>
                    </#if>
                    <br style="clear:both">
                    <h4>${msg("user.registrations")}</h4>
                    <!-- REGISTRATIONS -->
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th>${msg("user.reg.reg_name")}</th>
                            <th>${msg("user.reg.reg_status")}</th>
                            <th>${msg("user.reg.progress")}</th>
                            <th>${msg("user.reg.grade")}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list registrations.content as item>
                            <tr>
                                <td align="center">
                                    <a class="btn btn-default"><em class="fa fa-pencil"></em></a>
                                    <a class="btn btn-danger"><em class="fa fa-trash"></em></a>
                                </td>
                                <td>${item.courseName}</td>
                                <td>
                                    <#if item.courseStatus == "open">
                                        ${msg("course.open")}
                                    <#elseif item.courseStatus == "in_progress">
                                        ${msg("course.in_progress")}
                                    <#elseif item.courseStatus == "completed">
                                        ${msg("course.completed")}
                                    </#if>
                                </td>
                                <td>${item.progress}</td>
                                <td>${item.grade}</td>
                            </tr>
                        </#list>
                        </tbody>

                    </table>

                    <@p.pager "user" registrations/>

                </div>



            </div>

        </div>
    </div>
</div>

</body>
</html>