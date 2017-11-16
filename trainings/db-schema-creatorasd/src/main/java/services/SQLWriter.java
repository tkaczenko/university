package services;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by tkaczenko on 06.10.16.
 */
public class SQLWriter {
    public static final String DEFAULT_FILE_NAME = "request.sql";

    public static void writeToSqlFile(Map<Map<String, Object>, Template> inputTemplate, String destination)
            throws IOException, TemplateException {
        if (destination == null) {
            destination = DEFAULT_FILE_NAME;
        }
        Writer writer = new FileWriter(destination, true);
        for (Map.Entry<Map<String, Object>, Template> e :
                inputTemplate.entrySet()) {
            e.getValue().process(e.getKey(), writer);
        }
    }
}
