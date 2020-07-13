package bank.service.benchmark;

import bank.service.benchmark.logger.ColloredHelpFormatter;
import bank.service.benchmark.logger.ColoredLogger;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Application.
 *
 * @author Rusaln Potapov
 */
public class Application {

    @SneakyThrows
    public static void main(String[] args) {

        OptionSet optionSet = null;

        OptionParser parser = new OptionParser();
        parser.formatHelpWith(new ColloredHelpFormatter());

        final OptionSpec<String> headerSpec = parser.accepts("H", "Headers(could be delimited by ;)")
                .withRequiredArg().withValuesSeparatedBy(";").ofType(String.class);
        final OptionSpec<Integer> threadSpec = parser.accepts("t", "threads")
                .withRequiredArg().ofType(Integer.class);
        final OptionSpec<Integer> connectionsSpec = parser.accepts("c", "connections")
                .withRequiredArg().ofType(Integer.class).defaultsTo(Integer.MAX_VALUE);
        final OptionSpec<Integer> maxRequestSpec = parser.accepts("r", "maxRequests")
                .withRequiredArg().ofType(Integer.class).defaultsTo(Integer.MAX_VALUE);
        final OptionSpec<String> addressSpec = parser.accepts("url", "requestURL")
                .withRequiredArg().ofType(String.class);
        final OptionSpec<String> durationSpec = parser.accepts("duration", "duration")
                .requiredUnless("r").withRequiredArg().ofType(String.class);
        final OptionSpec sslSpec = parser.accepts("k", "ignoreSSL");
        final OptionSpec sseSpec = parser.accepts("sse", "sseEnabled");
        final OptionSpec debugSpec = parser.accepts("debugEnabled", "debugEnabled");


        try {
            optionSet = parser.parse(args);
        } catch (OptionException e) {
            ColoredLogger.log(ColoredLogger.RED, e.getMessage());
            parser.printHelpOn(System.out);
            System.exit(1);
        }

        final Map<String, String> headers = optionSet.valuesOf(headerSpec).stream()
                .map(res -> res.split(":", 2))
                .collect(Collectors.toMap(res -> res[0], res -> res[1]));
    }

}
