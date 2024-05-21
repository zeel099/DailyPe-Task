package com.dailype.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class FetchUserResponse {

    private String name;

    private List<FetchUserResponse> users;

    public FetchUserResponse(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }
    public void addUser(FetchUserResponse user) {
        this.users.add(user);
    }

}
