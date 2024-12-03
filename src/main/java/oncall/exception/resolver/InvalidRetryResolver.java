package oncall.exception.resolver;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InvalidRetryResolver {
    public static <T> T resolve(Supplier<T> supplier, Consumer<Exception> exceptionHandler) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                exceptionHandler.accept(e);
            }
        }
    }
}
