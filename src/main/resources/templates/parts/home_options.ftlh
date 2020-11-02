<#import "pager.ftlh" as p>
<#macro home_registrations>

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
                    <a class="btn btn-danger" href="/${locale}/courses/subject/course_${item.courseId}/register/cancel"><em class="fa fa-trash"></em></a>
                </td>
                <td><a href="/${locale}/courses/subject/course_${item.courseId}">${item.courseName}</a></td>
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

    <div class="panel-footer">
        <@p.pager uri registrations/>
    </div>
</#macro>

<#macro home_courses>

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

    <div class="panel-footer">
        <@p.pager uri courses/>
    </div>

</#macro>