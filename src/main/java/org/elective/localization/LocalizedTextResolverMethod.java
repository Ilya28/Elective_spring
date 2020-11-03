package org.elective.localization;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class LocalizedTextResolverMethod implements TemplateMethodModelEx {
    private final MessageSource messageSource;
    private final Locale locale;

    /**
     * Body of custom freemarker method. This method needed for access to messageSource
     * (locale property files) from '.ftlh' pages.
     * @param list Key of messageSource (one entity, String)
     * @return Localized text (String)
     * @throws TemplateModelException When smth went wrong
     */
    @Override
    public Object exec(List list) throws TemplateModelException {
        if (list.size() != 1)
           throw new TemplateModelException("Wrong number of arguments");
        String code = ((SimpleScalar) list.get(0)).getAsString();
        if (code == null || code.isEmpty()) {
            throw new TemplateModelException("Invalid code value '" + code + "'");
        }
        return messageSource.getMessage(code, null, locale);
    }
}
