package project.controller;

import project.exception.BadRequestException;

public class EntityIdParser {

    public static Long parseId(String id) {
        long entityId;
        try {
            entityId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException(ExceptionConstants.INCORRECT_ID);
        }
        return entityId;
    }
}
