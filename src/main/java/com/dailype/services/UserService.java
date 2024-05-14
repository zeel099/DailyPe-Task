package com.dailype.services;

import com.dailype.entity.User;
import com.dailype.payload.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    List<User> getUsersByManagerId(UUID managerId);
    List<User> getUserByMobNum(String mobNum);
    void deleteUser(Integer userId);
//    void bulkUpdate(UserDto userDtoList);
}
