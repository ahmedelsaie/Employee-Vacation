package org.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CreateEmployeeDTo {

    @Schema(defaultValue = "user1")
    private final String name;

    @Schema(defaultValue = "senior")
    private final String position;

    @Email(message = "Email must be formed well")
    @Schema(defaultValue = "user1@gmail.com")
    private final String email;

    @DecimalMin("100")
    @Schema(defaultValue = "100.0")
    private final float salary;

    @NotBlank
    private final String password;
}
