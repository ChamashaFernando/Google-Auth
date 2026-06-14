package lk.chamasha.googleauth.service;



import lk.chamasha.googleauth.controller.request.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
}