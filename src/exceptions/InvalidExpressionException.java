package src.exceptions;

public class InvalidExpressionException extends RuntimeException{
    public InvalidExpressionException(String message) {
        super(message);
    }
}
