package com.dailype.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "User"
)
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String full_name;

    private String mobNum;

    private String pan_num;
    private UUID managerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;

    @ElementCollection
    private List<Integer> users = new ArrayList<>();

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users != null ? users : new ArrayList<>();
    }
}
