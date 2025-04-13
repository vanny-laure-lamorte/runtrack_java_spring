package com.example.runtrack.configuration;

import com.example.runtrack.model.Person;
import com.example.runtrack.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserSeeder {

    @Bean
    public CommandLineRunner initData(PersonRepository personRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (personRepository.findByUsername("admin") == null) {
                Person admin = new Person();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin"));
                admin.setRole("ROLE_ADMIN");
                personRepository.save(admin);
                System.out.println("Admin créé !");
            }

            if (personRepository.findByUsername("user") == null) {
                Person user = new Person();
                user.setUsername("user");
                user.setPassword(encoder.encode("user"));
                user.setRole("ROLE_USER");
                personRepository.save(user);
                System.out.println("User créé !");
            }
        };
    }
}
