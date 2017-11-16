package services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import models.Column;
import models.Database;
import models.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tkaczenko on 05.10.16.
 */
public class SQLBuilder {
    private static final String TEMPLATE_DIRECTORY = "src/main/resources/templates/";
    private static final String CREATE_TABLE = "/createTable.ftl";
    private static final String ALTER_TABLE_PK = "/alterTablePK.ftl";
    private static final String ALTER_TABLE_FK = "/alterTableFK.ftl";

    private static Configuration cfg = null;

    private Database database;

    public SQLBuilder() throws IOException {
        initConfiguartion();
    }

    public SQLBuilder(Database database) throws IOException {
        this.database = database;

        initConfiguartion();
    }

    public List<String> createQueries(String sourceFile) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(sourceFile));
        stream.forEach(s -> stringBuilder.append(s));

        return Stream.of(stringBuilder.toString().split(";")).filter(isNotEmpty())
                .collect(Collectors.toList());
    }

    public Map<Map<String, Object>, Template> createTemplateTables() throws IOException {
        Map<Map<String, Object>, Template> inputTemplateMap = new HashMap<>();
        for (Table table :
                database.getTables()) {
            Map<String, Object> input = getTableStructure(table);
            Template template = cfg.getTemplate(CREATE_TABLE);

            inputTemplateMap.put(input, template);
        }
        return inputTemplateMap;
    }

    public Map<Map<String, Object>, Template> primaryTemplateTables() throws IOException {
        Map<Map<String, Object>, Template> inputTemplateMap = new HashMap<>();
        for (Table table :
                database.getTables()) {
            Map<String, Object> input = getUpdateTable(table);
            Template template = cfg.getTemplate(ALTER_TABLE_PK);

            inputTemplateMap.put(input, template);
        }
        return inputTemplateMap;
    }

    public Map<Map<String, Object>, Template> foreignTemplateTables() throws IOException {
        Map<Map<String, Object>, Template> inputTemplateMap = new HashMap<>();
        for (Table table :
                database.getTables()) {
            if (table.getForeignKeys().isEmpty()) {
                continue;
            }
            Map<String, Object> input = getForeignTable(table);
            Template template = cfg.getTemplate(ALTER_TABLE_FK);

            inputTemplateMap.put(input, template);
        }
        return inputTemplateMap;
    }

    private void initConfiguartion() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_DIRECTORY));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.ENGLISH);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    private Map<String, Object> getTableStructure(Table table) {
        Map<String, Object> input = new HashMap<>();
        List<Column> columns = table.getColumns();

        input.put("tableName", table.getName());
        input.put("columns", columns);

        return input;
    }

    private Map<String, Object> getUpdateTable(Table table) {
        Map<String, Object> input = new HashMap<>();

        input.put("tableName", table.getName());
        input.put("keyName", table.getPrimaryKeys().get(0).getName());
        input.put("columnNames", table.getPrimaryKeys().get(0).getColumnNames());

        return input;
    }

    private Map<String, Object> getForeignTable(Table table) {
        Map<String, Object> input = new HashMap<>();

        input.put("tableName", table.getName());
        input.put("keyName", table.getForeignKeys().get(0).getName());
        input.put("columnNames", table.getForeignKeys().get(0).getColumnNames());
        input.put("pkTableName", table.getForeignKeys().get(0).getPkTableName());
        input.put("pkColumnName", table.getForeignKeys().get(0).getPkColumnName());

        return input;
    }

    private Predicate<String> isNotEmpty() {
        return s -> (!s.trim().equals("") && !s.trim().equals("\t"));
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
