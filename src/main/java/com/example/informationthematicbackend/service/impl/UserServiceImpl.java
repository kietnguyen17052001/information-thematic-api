package com.example.informationthematicbackend.service.impl;

import com.example.informationthematicbackend.common.exception.NotFoundException;
import com.example.informationthematicbackend.model.entity.UserEntity;
import com.example.informationthematicbackend.repository.jpa.UserRepository;
import com.example.informationthematicbackend.security.CustomUser;
import com.example.informationthematicbackend.service.UserService;
import com.example.informationthematicbackend.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username invalid"));
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        List.of(user.getRole()).forEach(role -> {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return new CustomUser(user.getUsername(), user.getPassword(), simpleGrantedAuthorities, user.getUserId(), user.getRole().getRole(),
                user.getFirstName(), user.getLastName(), (user.getSchool() != null) ? user.getSchool().getSchoolId() : -1L,
                RequestUtil.blankIfNull(user.getStreet()), RequestUtil.blankIfNull(user.getDistrict()), RequestUtil.blankIfNull(user.getCity()),
                user.getCreatedDate() != null ? user.getCreatedDate() : new Timestamp(System.currentTimeMillis()));
    }
}
