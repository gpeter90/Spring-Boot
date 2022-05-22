package hu.webuni.exam.logistics.exception;

public class NotValidAddressException extends RuntimeException {

    public NotValidAddressException() {
        super("Invalid Address: ");
    }
}
