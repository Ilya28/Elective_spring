<#macro pager url page>
    <div class="row">
        <!--div class="col col-xs-4">Page {page.getNumber()+1} of {page.getTotalPages()}
        empty page: 1 of 0
        </div-->
        <div class="col col-xs-8">
            <ul class="pagination hidden-xs pull-right">
                <li class="page-item disabled">
                    <a class="page-link" href="#">${msg("pager.pages")}</a>
                </li>
                <#if page.getTotalPages() gt 0>
                    <#list 1..page.getTotalPages() as i>
                        <#if i-1 == page.getNumber()>
                            <li class="page-item active"><a class="page-link" href="#">${i}<span class="sr-only">(current)</span></a></li>
                        <#else>
                            <li class="page-item"><a class="page-link" href="${url}?page=${i-1}">${i}</a></li>
                        </#if>
                    </#list>
                 <#else >
                    <li class="page-item disabled">
                        <a class="page-link" href="#">0</a>
                    </li>
                </#if>
            </ul>
            <ul class="pagination visible-xs pull-right">
                <#if page.getNumber() gt 0>
                    <li class="page-item"><a class="page-link" href="${url}?page=${page.getNumber()-1}">«</a></li>
                <#else>
                    <li class="page-item"><a class="page-link" href="#">«</a></li>
                </#if>
                <#if page.getNumber() < page.getTotalPages()-1>
                    <li class="page-item"><a class="page-link" href="${url}?page=${page.getNumber()+1}">»</a></li>
                <#else>
                    <li class="page-item"><a class="page-link" href="#">»</a></li>
                </#if>
            </ul>
        </div>
    </div>
</#macro>