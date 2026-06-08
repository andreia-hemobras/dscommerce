package dev.andreia.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant moment, Integer status, String error, String path) {
        super(moment, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String field, String message){
        errors.removeIf(error -> error.getField().equals(field));
        errors.add(new FieldMessage(field, message));
    }
}
