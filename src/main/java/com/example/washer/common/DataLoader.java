package com.example.washer.common;

import com.example.washer.model.*;
import com.example.washer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Collections;
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    String initMode;

    private final WashCompanyRepository washCompanyRepository;
    private final OrderRepository orderRepository;
    private final WasherRepository washerRepository;
    private final ServiceRepository serviceRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {


            Role roleSuperAdmin = roleRepository.save(new Role("ROLE_SUPER_ADMIN"));
            userRepository.save(new User("s", passwordEncoder.encode("1"), Collections.singleton(roleSuperAdmin)));

            Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
            userRepository.save(new User("a", passwordEncoder.encode("1"), Collections.singleton(roleAdmin)));

            Role roleUser = roleRepository.save(new Role("ROLE_USER"));
            userRepository.save(new User("u", passwordEncoder.encode("1"), Collections.singleton(roleUser)));

            WashCompany washCompany = washCompanyRepository.save(new WashCompany("wash company name", "wash company location", null));
            orderRepository.save(new Order(100, "car model 1", "car number1", "client name1", 12341, true, LocalDate.of(2019, 6, 6), washCompany));
            orderRepository.save(new Order(200, "car model 2", "car number2", "client name2", 12342, true, LocalDate.of(2020, 6, 6), washCompany));
            orderRepository.save(new Order(300, "car model 3", "car number3", "client name3", 12343, true, LocalDate.of(2021, 6, 6), washCompany));
            orderRepository.save(new Order(400, "car model 4", "car number4", "client name4", 12344, true, LocalDate.of(2022, 6, 6), washCompany));
            washerRepository.save(new Washer("washer", 123, 12, null, true, washCompany));
            washerRepository.save(new Washer("washer2", 1234, 10, null, true, washCompany));
            serviceRepository.save(new Service("service name1", 1, 100));
            serviceRepository.save(new Service("service name2", 2, 200));
            serviceRepository.save(new Service("service name3", 3, 300));
            serviceRepository.save(new Service("service name4", 4, 400));


        }
    }
}
