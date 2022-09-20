package com.example.washer.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


public interface ChangeProjection {


   UUID getOrderId();
   String getUserName();
   String getPreviousCarModel();
   String getCurrentCarModel();
   String getPreviousCarNumber();
   String getCurrentCarNumber();
   String getPreviousServicesName();
   String getCurrentServicesName();
   String getPreviousServicesPrice();
   String getCurrentServicesPrice();
   String getPreviousPrice();
   String getCurrentPrice();
   Boolean getIsCancelled();
   String getCancelledReason();

}

//    public HttpEntity<ApiResponse> register(RegisterUserDto loginUser) {
//        final boolean hasUser = userRepository.existsByUsername(loginUser.getLogin());
//        if (hasUser) {
//            return answerService.answer("This [ " + loginUser.getLogin() + " ] username already exist", false, null, HttpStatus.ALREADY_REPORTED);
//        }
//        final Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
//        Set<Role> roles = new HashSet<>();
//        roles.add(userRole.get());
//        final String encode = passwordEncoder.encode(loginUser.getPassword());
//        final User save = userRepository.save(new User(loginUser.getLogin(), loginUser.getName(),  encode, roles));
//        UserDetails userDetails = loadUserByUsername(loginUser.getLogin());
//        if (userDetails == null) {
//            return answerService.answer("error", false, null, HttpStatus.CONFLICT);
//        }
//        String generatedToken = jwtProvider.generateToken(userDetails.getUsername());
//        return answerService.answer("success", true, generatedToken, HttpStatus.OK);
//
//    }

//    @PostMapping("/signUp")
//    public HttpEntity<ApiResponse> register(@RequestBody RegisterUserDto userDto) {
//        return userService.register(userDto);
//    }

//            JournalJsonDto journalJsonDto1 = new JournalJsonDto(
//                    savedOrder.getId(),
//                    "userName111",
//                    "previousCarModel111",
//                    "currentCarModel111",
//                    "previousCarNumber111",
//                    "currentCarNumber111",
//                    "previousServicesName111",
//                    "currentServicesName111",
//                    "previousServicesPrice111",
//                    "currentServicesPrice111",
//                    "previousPrice111",
//                    "currentPrice111",
//                    true,
//                    "cancelledReaso111n"
//            );
//
//
//            JournalJsonDto journalJsonDto2 = new JournalJsonDto(
//                    savedOrder.getId(),
//                    "userName222",
//                    "previousCarModel222",
//                    "currentCarModel222",
//                    "previousCarNumber222",
//                    "currentCarNumber222",
//                    "previousServicesName222",
//                    "currentServicesName222",
//                    "previousServicesPrice222",
//                    "currentServicesPrice222",
//                    "previousPrice222",
//                    "currentPrice222",
//                    true,
//                    "cancelledReaso222n"
//            );
//            final String journalJsonDto1Str = gson.toJson(journalJsonDto1);
//            final String journalJsonDto2Str = gson.toJson(journalJsonDto2);
//            journalRepository.save(new Journal(journalJsonDto1Str));
//            journalRepository.save(new Journal(journalJsonDto2Str));
