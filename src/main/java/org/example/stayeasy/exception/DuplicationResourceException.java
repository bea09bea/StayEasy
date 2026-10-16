package org.example.stayeasy.exception;

//Exception som kastas när ett värde som måste vara unikt redan finns i databasen
public class DuplicationResourceException extends RuntimeException {

    public DuplicationResourceException(String message) {
        super(message);
    }
}
