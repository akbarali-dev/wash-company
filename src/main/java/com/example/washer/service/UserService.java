package com.example.washer.service;


import com.example.washer.dto.ChangePasswordDto;
import com.example.washer.dto.RegisterUserDto;
import com.example.washer.dto.UserDto;
import com.example.washer.model.Role;
import com.example.washer.model.User;
import com.example.washer.payload.ApiResponse;
import com.example.washer.repository.RoleRepository;
import com.example.washer.repository.UserRepository;
import com.example.washer.security.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AnswerService answerService;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username yoki parol xato!"));
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            System.out.println("username: " + user.getUsername());
            System.out.println("password: " + user.getPassword());
            System.out.println("username: " + username);
            System.out.println(getAuthority(user));
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getAuthority(user));
        }
        return null;
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

    public HttpEntity<ApiResponse> changePassword(ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        final Optional<User> byLogin = userRepository.findByUsername(currentPrincipalName);
        if (!byLogin.isPresent())
            return answerService.answer("user not found", false, null, HttpStatus.NOT_FOUND);
        User user = byLogin.get();
        final boolean matches = passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword());
        if (!matches) {
            return answerService.answer("old password error", false, null, HttpStatus.CONFLICT);
        }

        user.setPassword(changePasswordDto.getNewPassword());
        userRepository.save(user);
        return answerService.answer("success", true, null, HttpStatus.OK);
    }

    public HttpEntity<ApiResponse> login(UserDto userDto) {
        UserDetails userDetails = loadUserByUsername(userDto.getUsername());
        if (userDetails == null) {
            return answerService.answer("login or password error", true, null, HttpStatus.OK);
        }
        String generatedToken = jwtProvider.generateToken(userDetails.getUsername());
        return answerService.answer("success", true, generatedToken, HttpStatus.OK);
    }

    public HttpEntity<ApiResponse> register(RegisterUserDto loginUser) {
        final boolean hasUser = userRepository.existsByUsername(loginUser.getLogin());
        if (hasUser) {
            return answerService.answer("This [ " + loginUser.getLogin() + " ] username already exist", false, null, HttpStatus.ALREADY_REPORTED);
        }
        final Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole.get());
        final String encode = passwordEncoder.encode(loginUser.getPassword());
        final User save = userRepository.save(new User(loginUser.getLogin(), loginUser.getName(),  encode, roles));
        UserDetails userDetails = loadUserByUsername(loginUser.getLogin());
        if (userDetails == null) {
            return answerService.answer("error", false, null, HttpStatus.CONFLICT);
        }
        String generatedToken = jwtProvider.generateToken(userDetails.getUsername());
        return answerService.answer("success", true, generatedToken, HttpStatus.OK);

    }
}

