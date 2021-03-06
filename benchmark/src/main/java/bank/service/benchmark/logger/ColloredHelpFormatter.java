package bank.service.benchmark.logger;

import joptsimple.BuiltinHelpFormatter;
import joptsimple.OptionDescriptor;

import java.util.Map;

/**
 * The type Collored help formatter.
 *
 * @author Ruslan Potapov
 */
public class ColloredHelpFormatter extends BuiltinHelpFormatter {

    public ColloredHelpFormatter() {
        super(80, 2);
    }

    public ColloredHelpFormatter(int desiredOverallWidth, int desiredColumnSeparatorWidth) {
      super(desiredOverallWidth, desiredColumnSeparatorWidth);
    }

    @Override
    public String format(Map<String, ? extends OptionDescriptor> options) {
        return ColoredLogger.GREEN + super.format(options);
    }
}
