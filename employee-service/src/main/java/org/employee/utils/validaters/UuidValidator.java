package org.employee.utils.validaters;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UuidValidator implements ConstraintValidator<ValidUuid, String> {
    private final String regex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";// the regex

    @Override
    public void initialize(ValidUuid validUuid) {
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext cxt) {
        return uuid.matches(this.regex);
    }
}
