package org.example.stayeasy.exception;

// exception som kastas när en användare eller ett boende inte hittas i databasen
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
