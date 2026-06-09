package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     User getUserById(Long id);
}
