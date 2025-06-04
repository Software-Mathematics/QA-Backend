package com.messagecreation.utility;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Component
public class MessageCreationAdapter {

    public String createTemplate(String templatePath, String templateName, Map<String, Object> model) throws IOException, TemplateException {
        for (Map.Entry<String,Object> entry : model.entrySet()) {
            if (entry.getValue() == null){
                model.put(entry.getKey(), " ");
            }
        }
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Template t = configuration.getTemplate(templateName);
        return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
    }
}
