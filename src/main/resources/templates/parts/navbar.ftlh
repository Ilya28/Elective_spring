<#macro navbar>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">${msg("navbar.title")}</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <#if role != "ROLE_ANONYMOUS">
                        <li class="active">
                            <a href="#">${msg("navbar.home")}<span class="sr-only">(current)</span></a>
                        </li>
                        <li>
                            <a href="#">${msg("navbar.profile")}</a>
                        </li>
                    </#if>
                    <li class="dropdown">
                        <a href="/courses" class="dropdown-toggle" data-toggle="dropdown"
                           aria-expanded="false">${msg("navbar.courses")} <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/${locale}/subjects">${msg("navbar.show_subjects")}</a></li>
                            <li class="divider"></li>
                            <li class="divider"></li>
                            <#list subjects as item>
                                <li><a href="/${locale}/courses/${item.mapping}">${item.name}</a></li>
                                <li class="divider"></li>
                            </#list>
                            <li><a href="/${locale}/courses">${msg("navbar.all_courses")}</a></li>
                        </ul>
                    </li>
                    <#if role == "admin">
                        <li>
                            <a href="#">${msg("navbar.users")}</a>
                        </li>
                    </#if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    ${role}
                    <#if role != "ROLE_ANONYMOUS">
                        <li><a href="/logout">${msg("navbar.log_out")}</a></li>
                    <#else>
                        <li><a href="/login">${msg("navbar.log_in")}</a></li>
                    </#if>
                    <#if locale = "en">
                        <li><a href="#" onclick="changeLocale('ua')">UA</a></li>
                    <#else>
                        <li><a href="#" onclick="changeLocale('en')">EN</a></li>
                    </#if>
                    <script>
                        function changeLocale(locale) {
                            location.href = "/" + locale + (location.pathname).substring(3);
                        }
                    </script>
                </ul>
            </div>
        </div>
    </nav>
</#macro>