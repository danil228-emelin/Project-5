package itmo.p3108.exception;

/**
 * CommandException occur when command fails to execute,
 * for instance,no argument provided but   command has 1 argument,
 * command has no argument,but it was provided
 */
public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }

}
