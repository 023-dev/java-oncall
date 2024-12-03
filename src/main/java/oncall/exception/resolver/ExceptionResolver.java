package oncall.exception.resolver;

@FunctionalInterface
public interface ExceptionResolver {
    boolean resolve(Exception e);
}
