package org.employee.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.EnumType.STRING;

@Entity
@NoArgsConstructor
@ToString
@Setter
@Getter
public class RequestCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(STRING)
    private RequestCategoryType requestCategoryType;
}
