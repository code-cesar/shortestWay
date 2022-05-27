package Exception;

public class SolutionException extends Exception{
    public SolutionException() {
    }

    public SolutionException(String message) {
        super(message);
    }

    public SolutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SolutionException(Throwable cause) {
        super(cause);
    }

    public SolutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
