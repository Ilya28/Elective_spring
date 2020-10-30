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
<div class="col-md-8 col-md-offset-4">
    <h4>${msg("courses.title")}</h4>
</div>


<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <#if message??>
                <h4>${message}</h4>
            </#if>

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">${subject}</h3>
                        </div>
                        <#if "admin" == "admin" && add_url??>
                            <div class="col col-xs-6 text-right">
                                <button class="btn btn-sm btn-primary" type="button" data-toggle="collapse" data-target="#collapseForm" aria-expanded="false" aria-controls="collapseForm">
                                    Create New
                                </button>
                            </div>
                            <div class="collapse" id="collapseForm">
                                <div class="card card-body">
                                    <br style="clear:both">
                                    <@course_form.course_form/>
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th>${msg("courses.table.subject")}</th>
                            <th>${msg("courses.table.name")}</th>
                            <th>${msg("courses.table.seats")}</th>
                            <th>${msg("courses.table.start")}</th>
                            <th>${msg("courses.table.end")}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list courses.content as item>
                            <tr>
                                <td align="center">
                                    <a class="btn btn-default"><em class="fa fa-pencil"></em></a>
                                    <a class="btn btn-danger"><em class="fa fa-trash"></em></a>
                                </td>
                                <td>${item.subject}</td>
                                <td><a href="/${locale}/courses/${item.subjectMapping}/course_${item.id}">${item.name}</a></td>
                                <td>${item.signedUp} / ${item.seats}</td>
                                <td>${item.dateStart}</td>
                                <td>${item.dateEnd}</td>
                            </tr>
                        </#list>
                        </tbody>

                    </table>

                </div>
                <div class="panel-footer">
                   <@p.pager url courses/>
                </div>
            </div>

        </div></div></div>

</body>
</html>