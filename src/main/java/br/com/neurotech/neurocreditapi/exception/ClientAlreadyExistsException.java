package br.com.neurotech.neurocreditapi.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String id) {
        super("Client with ID " + id + " already exists");
    }
}