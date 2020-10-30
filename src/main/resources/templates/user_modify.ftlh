<#import "parts/login_form.ftlh" as form>
<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<#import "parts/course_form.ftlh" as course_form>
<#import "parts/pager.ftlh" as p>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${msg("courses.title")}</title>
    <@header.all/>
    <link href="/css/pagination.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <!--#if message??>
                <h4>{message}</h4>
            </if-->

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <h3>${msg("user.modify.title")}</h3>
                </div>
                <div class="panel-body">
                    <div class="container">
                        <div class="row">



                            <form role="form" style="display: block; background-color:rgba(0,0,0,0) !important;" method="post"
                                  action="modify" autocomplete="off">

                                <div class="form-group col-md-6 ">
                                    <label id="nameLabel" for="name">${msg("user.name")}</label>
                                    <input type="text" name="name" id="name" class="form-control" value="${user.name}">
                                </div>
                                <br style="clear:both">
                                <div class="form-group col-md-6 ">
                                    <label id="emailLabel" for="email">${msg("user.email")}</label>
                                    <input type="text" name="email" id="email" class="form-control" value="${user.email}">
                                </div>
                                <br style="clear:both">
                                <div class="form-group col-md-6 ">
                                    <label id="roleLabel" for="role">${msg("user.role")}</label>
                                    <div class="col-md-4">
                                        <select class="form-control" name="role" id="role">
                                            <option value="${user.role}">${msg("user.role.current")}: ${user.role?keep_after("_")}</option>
                                            <option value="ROLE_USER">${msg("user.role.user")}</option>
                                            <option value="ROLE_TEACHER">${msg("user.role.teacher")}</option>
                                            <option value="ROLE_ADMIN">${msg("user.role.admin")}</option>
                                        </select>
                                    </div>
                                </div>
                                <br style="clear:both">
                                <div class="form-check">
                                    <input type="checkbox" class="form" id="blocked" name="blocked" <#if user.blocked==true>checked</#if>>
                                    <label class="form-check-label" for="blocked">${msg("user.blocked")}</label>
                                </div>

                                <br style="clear:both">
                                <div class="form-group col-md-2">
                                    <input type="submit"
                                           class="form-control input-sm btn btn-success"
                                           style="height:35px"
                                           value="${msg("btn.modify")}">
                                </div>
                            </form>



                        </div>
                    </div>
                </div>
            </div>

        </div></div></div>

</body>
</html>