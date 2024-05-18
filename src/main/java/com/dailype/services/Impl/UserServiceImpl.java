package com.dailype.services.Impl;

import com.dailype.entity.User;
import com.dailype.exceptions.ResourceNotFoundException;
import com.dailype.exceptions.UserNotFoundException;
import com.dailype.payload.UserDto;
import com.dailype.repository.UserRepo;
import com.dailype.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;



    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto) {
        if (!this.isValidPAN(userDto.getPan_num())) {
            throw new IllegalArgumentException("Invalid PAN number");
        } else {
            String panNum = userDto.getPan_num().toUpperCase();
//            UUID userId = UUID.randomUUID();

            User user = this.modelMapper.map(userDto, User.class);
//            user.setId(userId);
            user.setPan_num(panNum);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(null);
            user.setActive(true);
            User savedUser = this.userRepo.save(user);
            return this.modelMapper.map(savedUser, UserDto.class);
        }
    }

    public UserDto getUserById(Integer id) {
        User user = this.userRepo.findById(id).
                orElseThrow(() ->new ResourceNotFoundException("User", "id", id));
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public List<User> getUsersByManagerId(UUID managerId) {
        return userRepo.findByManagerId(managerId);
    }

    @Override
    public List<User> getUserByMobNum(String mobNum) {
        return userRepo.findByMobNum(mobNum);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()->new UserNotFoundException("User not found "));
        this.userRepo.delete(user);
    }

    @Override
    public List<User> getUsers(Integer userId) {
           return this.userRepo.findByUserIdGreaterThanOrderByUserId(userId);
    }
























//    @Override
//    public void bulkUpdate(UserDto userDtoList) {
//        List<UUID> userIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID()); // Example list of UUIDs
////        List<User> users = userRepository.findAllById(userIds);
////        List<UUID> userIds = userDtoList.getUserIds();
////        User userData = userDtoList.getUser();
//
//        if(userData.getFull_name() != null || userData.getMobNum() != null || userData.getPan_num()!= null) {
//            throw new IllegalArgumentException("Only manager_id can be updated in bulk. Other fields must be updated individually");
//        }
////        List<User>users = userRepo.findAllById(userIds);
//        if(users.size() != userIds.size()) {
//            throw new IllegalArgumentException("One or more user IDs are not found in the database");
//        }
//        if(userData.getManagerId()!=null){
//            Optional<Manager>managerOptional = managerRepo.findById((userData.getManagerId()));
//            if(managerOptional.isPresent()) {
//                throw new IllegalArgumentException("Manager id does not exist in the database");
//            }
//            for(User user : users) {
//                if(user.getManagerId()==null) {
//                    user.setManagerId(userData.getManagerId());
//                    user.setUpdatedAt(LocalDateTime.now());
//                    userRepo.save(user);
//                }else{
//                    user.setActive(false);
//                    user.setUpdatedAt(LocalDateTime.now());
//                    userRepo.save(user);
//
//                    User newUser = new User();
////                    newUser.setUserId(UUID.randomUUID());
//                    newUser.setFull_name(user.getFull_name());
//                    newUser.setMobNum(user.getMobNum());
//                    newUser.setPan_num(user.getPan_num());
//                    newUser.setManagerId(userData.getManagerId());
//                    newUser.setCreatedAt(user.getCreatedAt());
//                    newUser.setUpdatedAt(LocalDateTime.now());
//                    newUser.setActive(true);
//                    userRepo.save(newUser);
//                }
//            }
//        }
//
//    }


    private boolean isValidPAN(String panNum) {
        String panPattern = "[a-z]{5}[0-9]{4}[a-z]{1}";
        return panNum.matches(panPattern);
    }
}
