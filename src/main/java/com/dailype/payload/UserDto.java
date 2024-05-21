package com.dailype.payload;

import com.dailype.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int userId;
    private @NotBlank(
            message = "full name is required"
    ) String full_name;
    private @Pattern(
            regexp = "^(0|\\+91)?[1-9][0-9]{9}$",
            message = "Invalid mobile number"
    )String mobNum;

    private String pan_num;

    private UUID managerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;

    private List<Integer> users;


}
