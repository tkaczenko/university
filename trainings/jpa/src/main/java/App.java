import command.*;
import org.apache.commons.cli.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class App {
    private static final ResourceBundle bundle;
    private static final Locale locale;

    static {
        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("resource", locale);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        prepareOptions(options);

        try {
            CommandLine line = parser.parse(options, args);
            //// TODO: 24.11.16 Implement operations
            Switch switcher = new Switch();
            if (line.hasOption("scc")) {
                Command selectCountryCapitals = new SelectCountryCapitalsCommand();
                switcher.execute(selectCountryCapitals);
            }
            if (line.hasOption("scm")) {
                String option = line.getOptionValue("scm");
                Command selectCountryCitiesCommand = new SelectCountryCitiesCommand(option);
                switcher.execute(selectCountryCitiesCommand);
            }
            if (line.hasOption("u")) {
                String[] arguments = line.getOptionValues("u");
                Command updateCapitalCommand = new UpdateCapitalCommand(arguments[0], Integer.parseInt(arguments[1]));
                switcher.execute(updateCapitalCommand);
            }
            if (line.hasOption("a")) {
                String[] arguments = line.getOptionValues("a");
                Command insertCountryCommand = new InsertCountryCommand(arguments[0], arguments[1]);
                switcher.execute(insertCountryCommand);
            }
            if (line.hasOption("d")) {
                String continent = line.getOptionValue("d");
                Command deleteContinent = new DeleteContinentCommand(continent);
                switcher.execute(deleteContinent);
            }
            if (line.hasOption("t")) {
                long duration = System.currentTimeMillis() - startTime;
                System.out.println("Working time (ms) : " + (double) duration / 1000);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(duration * 1000);
                DateFormat currentFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
                System.out.println("Time: " + currentFormat.format(calendar.getTime()));
            }
            if (line.hasOption("h")) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("JDBCTask-1.0.jar", options);
            }
        } catch (ParseException e) {
            System.out.println("Unexpected exception:" + e.getMessage());
        }
    }

    private static void prepareOptions(Options options) {
        options.addOption("scc", "select-country-capital", false, bundle.getString("select-country-capitaldesc"));
        options.addOption(Option.builder("scn")
                .longOpt("select-city-countryname")
                .desc(bundle.getString("select-city-countrynamedesc"))
                .numberOfArgs(2)
                .argName("countryname")
                .build()
        );
        options.addOption(Option.builder("u")
                .longOpt("update")
                .desc(bundle.getString("updatedesc"))
                .numberOfArgs(2)
                .argName("countryCode capitalID")
                .build()
        );
        options.addOption(Option.builder("a")
                .longOpt("add-country")
                .desc(bundle.getString("adddesc"))
                .numberOfArgs(2)
                .argName("country-fields")
                .build()
        );
        options.addOption("d", "delete-continent", true, bundle.getString("deletedesc"));
        options.addOption(Option.builder("d")
                .longOpt("update")
                .desc(bundle.getString("deletedesc"))
                .numberOfArgs(1)
                .argName("continent")
                .build());
        options.addOption("t", "time", false, bundle.getString("timedesc"));
        options.addOption("h", "help", false, bundle.getString("helpdesc"));
    }
}
