package com.example.washer.service;

import com.example.washer.dto.EmailDto;
import com.example.washer.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


@Service
@RequiredArgsConstructor
public class SupportService {
    private final AnswerService answerService;
    private final MailSender mailSender;


    public HttpEntity<ApiResponse> sendEmail(EmailDto emailDto, Errors errors) {
        final ResponseEntity<ApiResponse> error = answerService.getError(errors);
        if (error!=null)
            return error;

        SimpleMailMessage simpleMsg = new SimpleMailMessage();
        simpleMsg.setFrom("akbaraliasqaraliyev0610@gmail.com");
        simpleMsg.setTo(emailDto.getToEmail());
        simpleMsg.setSubject(emailDto.getSubject());
        simpleMsg.setText(emailDto.getMessage() + "\nContact: " + emailDto.getContact());

        try {
            mailSender.send(simpleMsg);
        } catch (Exception e) {
            return answerService.answer("error", false, null, HttpStatus.NOT_FOUND);
        }
        return answerService.answer("success", true, null, HttpStatus.OK);
    }
}
