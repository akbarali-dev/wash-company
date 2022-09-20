package com.example.washer.common;

import com.example.washer.dto.Change;
import com.example.washer.model.*;
import com.example.washer.repository.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


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
    private final JournalRepository journalRepository;
    private final RestTemplate restTemplate;




    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {


            Role roleSuperAdmin = roleRepository.save(new Role("ROLE_SUPER_ADMIN"));
            userRepository.save(new User("s","s", passwordEncoder.encode("1"), Collections.singleton(roleSuperAdmin)));

            Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
            userRepository.save(new User("a","a", passwordEncoder.encode("1"), Collections.singleton(roleAdmin)));

            Role roleUser = roleRepository.save(new Role("ROLE_USER"));
            userRepository.save(new User("u","u", passwordEncoder.encode("1"), Collections.singleton(roleUser)));

            WashCompany washCompany = washCompanyRepository.save(new WashCompany("wash company name", "wash company location", null));
            final Order savedOrder = orderRepository.save(new Order(100, "car model 1", "car number1", "client name1", 12341, true, LocalDate.of(2019, 6, 6), washCompany));
            orderRepository.save(new Order(200, "car model 2", "car number2", "client name2", 12342, true, LocalDate.of(2020, 6, 6), washCompany));
            orderRepository.save(new Order(300, "car model 3", "car number3", "client name3", 12343, true, LocalDate.of(2021, 6, 6), washCompany));
            orderRepository.save(new Order(400, "car model 4", "car number4", "client name4", 12344, true, LocalDate.of(2022, 6, 6), washCompany));
            washerRepository.save(new Washer("washer", 123, 12, null, true, washCompany));
            washerRepository.save(new Washer("washer2", 1234, 10, null, true, washCompany));
            serviceRepository.save(new Service("service name1", 1, 100));
            serviceRepository.save(new Service("service name2", 2, 200));
            serviceRepository.save(new Service("service name3", 3, 300));
            serviceRepository.save(new Service("service name4", 4, 400));
            Change journalJsonDto1 = new Change(
                    savedOrder.getId(),
                    "userName111",
                    "previousCarModel111",
                    "currentCarModel111",
                    "previousCarNumber111",
                    "currentCarNumber111",
                    "previousServicesName111",
                    "currentServicesName111",
                    "previousServicesPrice111",
                    "currentServicesPrice111",
                    "previousPrice111",
                    "currentPrice111",
                    true,
                    "cancelledReaso111n"
            );


            Change journalJsonDto2 = new Change(
                    savedOrder.getId(),
                    "userName222",
                    "previousCarModel222",
                    "currentCarModel222",
                    "previousCarNumber222",
                    "currentCarNumber222",
                    "previousServicesName222",
                    "currentServicesName222",
                    "previousServicesPrice222",
                    "currentServicesPrice222",
                    "previousPrice222",
                    "currentPrice222",
                    true,
                    "cancelledReaso222n"
            );
            Gson gson = new Gson();
            final String journalJsonDto1Str = gson.toJson(journalJsonDto1);
            gson = new Gson();
            final String journalJsonDto2Str = gson.toJson(journalJsonDto2);
            System.out.println(journalJsonDto2Str);
            String test = "{\"orderId\":\"e5ebf88a-dfa9-48c9-8700-b5636228e527\",\"userName\":\"userName111\"}";


            journalRepository.saveObjectJson(journalJsonDto2Str);


        }
//        runner();
    }

    private void runner() throws InterruptedException {

        while (true) {
            String forObject = restTemplate.getForObject(
                    "https://washer-company.herokuapp.com/api/test/hello",
                    String.class
            );
            Thread.sleep(50000);

        }
    }
}
