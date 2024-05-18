package com.dailype.controller;



import com.dailype.entity.User;
import com.dailype.exceptions.UserNotFoundException;
import com.dailype.payload.ApiResponse;
import com.dailype.payload.UserDto;
import com.dailype.response.ResponseHandler;
import com.dailype.services.UserService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return ResponseHandler.responseBuilder("User created successfully", HttpStatus.OK, createUserDto);
    }

    @GetMapping("/user/{userId}/get_users")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @GetMapping("/get_users")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/get_users/{userId}")
    public ResponseEntity<List<User>> getUsers(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUsers((userId)));
    }


    @GetMapping("/by_manager/{managerId}/get_users")
    public ResponseEntity<List<User>> getUsersByManagerId(@PathVariable UUID managerId) {
        List<User> users = userService.getUsersByManagerId(managerId);
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/{userId}/delete_user")
    public ApiResponse deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ApiResponse("User is succesfully deleted !!",true);
    }

    @GetMapping("{mobNum}/get_users")
    public ResponseEntity<List<User>> getUserByMobNum(@PathVariable String mobNum) {
        List<User> users = userService.getUserByMobNum(mobNum);
        return ResponseEntity.ok(users);
//        return ResponseEntity.ok(this.userService.getUserByMobNum(mobNum));
    }

//    @PutMapping("/bulk_update")
//    public ResponseEntity<String> bulkUpdate(@RequestBody UserDto userDto){
//        try {
//            userService.bulkUpdate(userDto);
//            return ResponseEntity.ok("Users updated successfully.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("An error occurred while updating users.");
//        }
//    }
//    @PostMapping("/delete")
//    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto) {
//        try {
//            userService.deleteUser(userDto);
//            return ResponseEntity.ok("User deleted successfully");
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(404).body(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

}
