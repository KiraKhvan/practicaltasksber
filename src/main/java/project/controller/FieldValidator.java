package project.controller;

import org.springframework.util.StringUtils;
import project.controller.exception.BadRequestException;

public class FieldValidator {

    public static void validateRequiredField(String fieldName, String field) {
        if (StringUtils.isEmpty(field)) {
            throw new BadRequestException(fieldName + " cannot be empty");
        }
    }

    public static void validateRequiredField(String fieldName, Long field) {
        if (field == null) {
            throw new BadRequestException(fieldName + " cannot be null");
        }
    }
}
