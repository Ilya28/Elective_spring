<#macro navbar role>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Elective</a>
            </div>

            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav">
                    <#if role != "guest">
                        <li class="active">
                            <a href="#">Home<span class="sr-only">(current)</span></a>
                        </li>
                        <li>
                            <a href="#">Profile</a>
                        </li>
                    </#if>
                    <li class="dropdown">
                        <a href="/courses" class="dropdown-toggle" data-toggle="dropdown"
                           aria-expanded="false">Courses <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <#list subjects as subject>
                                <li><a href="/${locale}/courses/subject/${subject.mapping}">${subject.name}</a></li>
                                <li class="divider"></li>
                            </#list>
                            <li><a href="/${locale}/courses">All courses</a></li>
                        </ul>
                    </li>
                    <#if role == "admin">
                        <li>
                            <a href="#">Users</a>
                        </li>
                    </#if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <#if role != "guest">
                        <li><a href="/logout">Log out</a></li>
                    <#else>
                        <li><a href="/login">Log in</a></li>
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