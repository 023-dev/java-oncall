package oncall.exception.resolver;

import oncall.exception.custom.InvalidException;
import oncall.view.output.OutputView;

public class InputExceptionResolver implements ExceptionResolver {

    @Override
    public boolean resolve(Exception exception) {
        if(exception instanceof InvalidException) {
            OutputView.printError(exception.getMessage());
            return true;
        }
        return false;
    }
}
