package com.example.Project_Rishit.airBnbApp.Security;

import com.example.Project_Rishit.airBnbApp.dto.LoginRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.SignUpRequestDto;
import com.example.Project_Rishit.airBnbApp.dto.UserResponseDto;
import com.example.Project_Rishit.airBnbApp.entity.User;
import com.example.Project_Rishit.airBnbApp.entity.enums.Role;
import com.example.Project_Rishit.airBnbApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public UserResponseDto signup(@RequestBody SignUpRequestDto requestDto){
        User user = userRepository.findByEmail(requestDto.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeException("Email Already Exists");
        }
        User newUser = modelMapper.map(requestDto, User.class);
        newUser.setRoles(Set.of(Role.GUEST));
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        newUser=userRepository.save(newUser);

        return modelMapper.map(newUser, UserResponseDto.class);
    }
    public String[] signIn(@RequestBody LoginRequestDto loginRequestDto){


       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));

       User user = (User) authentication.getPrincipal();
       String[] arr = new String[2];
       arr[0]= jwtService.GenerateAccessToken(user);
       arr[1]= jwtService.GenerateRefreshToken(user);

       return  arr;

    }


}
