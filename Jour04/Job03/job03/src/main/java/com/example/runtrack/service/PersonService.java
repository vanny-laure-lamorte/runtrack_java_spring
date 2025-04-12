package com.example.runtrack.service;

import com.example.runtrack.model.Person;
import com.example.runtrack.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }

        return User.builder()
                .username(person.getUsername())
                .password(person.getPassword())
                .roles(person.getRole())
                .build();
    }
}
