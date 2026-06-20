package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.dto.ProfileUpdateRequest;
import com.example.Project_Rishit.airBnbApp.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
     User getUserById(Long id);

    void becomeHost();

    void UpdateProfileDetails(ProfileUpdateRequest updateRequest);
}
