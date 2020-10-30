<#import "parts/navbar.ftlh" as navbar>
<#import "parts/header.ftlh" as header>
<#import "parts/pager.ftlh" as p>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <@header.all/>
    <link href="css/table.css" rel="stylesheet">
</head>
<body>
<@navbar.navbar/>

<br style="clear:both">
<br style="clear:both">
<br style="clear:both">
<!--div class="col-md-8 col-md-offset-4">
    <h4>Users</h4>
</div-->

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">${msg("users.title")}</h3>
                        </div>
                        <div class="col col-xs-6 text-right">
                            <button type="button" class="btn btn-sm btn-primary btn-create">${msg("btn.create")}</button>
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th><em class="fa fa-cog"></em></th>
                            <th class="hidden-xs">ID</th>
                            <th>${msg("user.name")}</th>
                            <th>${msg("user.email")}</th>
                            <th>${msg("user.role")}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list users.content as item>
                            <tr>
                                <td align="center">
                                    <a href="users/user_${item.id}/modify" class="btn btn-default"><em class="fa fa-pencil"></em></a>
                                    <a href="users/user_${item.id}/delete" class="btn btn-danger"><em class="fa fa-trash"></em></a>
                                </td>
                                <td class="hidden-xs">${item.id}</td>
                                <td>${item.name}</td>
                                <td>${item.email}</td>
                                <td>${item.role}</td>
                            </tr>
                        </#list>
                        </tbody>

                    </table>

                </div>
                <div class="panel-footer">
                    <@p.pager "users" users/>
                </div>
            </div>

        </div></div></div>

</body>
</html>