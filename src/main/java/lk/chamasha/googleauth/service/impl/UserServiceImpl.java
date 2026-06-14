package lk.chamasha.googleauth.service.impl;


import lk.chamasha.googleauth.controller.request.UserResponse;
import lk.chamasha.googleauth.model.User;
import lk.chamasha.googleauth.repository.UserRepository;
import lk.chamasha.googleauth.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .profilePicture(user.getProfilePicture())
                        .build())
                .toList();
    }
}