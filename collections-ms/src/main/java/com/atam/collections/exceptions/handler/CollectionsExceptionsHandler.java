package com.atam.collections.exceptions.handler;

import com.atam.collections.exceptions.CollectionsException;
import com.atam.collections.exceptions.messages.Message;
import com.atam.collections.exceptions.messages.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class CollectionsExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CollectionsException.class)
    protected ResponseEntity<Messages> handleCollectionsException(CollectionsException ex) {
        Message message = getMessage(ex.getMessage(), "controlledError");
        Messages messages = Messages.builder().messages(Arrays.asList(message)).build();
        return new ResponseEntity<>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Messages> handleCollectionsException(Exception ex) {
        Message message = getMessage(ex.getMessage(), "genericError");
        Messages messages = Messages.builder().messages(Arrays.asList(message)).build();
        return new ResponseEntity<>(messages, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Message getMessage(String message, String alias) {
        return Message.builder()
                .type("ERROR")
                .description(message)
                .alias(alias)
                .build();
    }
}
