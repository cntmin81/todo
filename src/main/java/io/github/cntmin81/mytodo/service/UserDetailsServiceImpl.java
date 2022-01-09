package io.github.cntmin81.mytodo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.github.cntmin81.mytodo.entity.UserEntity;
import io.github.cntmin81.mytodo.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(username).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return new User(user.getName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }

}
