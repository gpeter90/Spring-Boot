package hu.webuni.exam.logistics.exception;

import org.springframework.http.HttpStatus;

public class NotValidAddressException extends RuntimeException {

    public NotValidAddressException() {
        super("Invalid Address!");
    }
}
