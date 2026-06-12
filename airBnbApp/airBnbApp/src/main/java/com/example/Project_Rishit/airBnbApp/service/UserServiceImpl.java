package com.example.Project_Rishit.airBnbApp.service;

import com.example.Project_Rishit.airBnbApp.Exceptions.ResourceNotFoundException;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.entity.enums.Role;
import com.example.Project_Rishit.airBnbApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User With this Id does not exist"));
    }
    public User getUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Override
    public void becomeHost() {
        User currentuser = getUser();
        User user = userRepository.findById(currentuser.getId()).orElseThrow(()->new NoSuchElementException("User Does not exist with Id :"+currentuser.getId()));

        if(user.getRoles().contains(Role.HOTEL_MANAGER)){

            throw new IllegalCallerException("You are Already a HotelManager");
        }
        user.getRoles().add(Role.HOTEL_MANAGER);
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User with email not found"));
    }
}
