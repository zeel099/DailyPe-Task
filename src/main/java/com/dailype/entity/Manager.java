package com.dailype.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Manager{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private UUID managerId;

    private String fullName;
}
