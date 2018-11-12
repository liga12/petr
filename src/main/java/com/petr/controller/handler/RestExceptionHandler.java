package com.petr.controller.handler;

import com.petr.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = BankExistsException.class)
    private String bankExistsException(BankExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = BankNotFoundException.class)
    private String bankNotFoundException(BankNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PassportFistPageException.class)
    private String passportFistPageException(PassportFistPageException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PassportSecondPageException.class)
    private String passportSecondPageException(PassportSecondPageException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PassportLastPageException.class)
    private String passportLastPageException(PassportLastPageException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PhotoInnException.class)
    private String photoInnException(PhotoInnException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = PhotoException.class)
    private String photoException(PhotoException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserEmailExistsException.class)
    private String userEmailExistsException(UserEmailExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserINNExistsException.class)
    private String userINNExistsException(UserINNExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    private String userNotFoundException(UserNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserPassportExistsException.class)
    private String userPassportExistsException(UserPassportExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = UserPhoneExistsException.class)
    private String userPhoneExistsException(UserPhoneExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyExistsException.class)
    private String surveyExistsException(SurveyExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionExistsException.class)
    private String questionExistsException(QuestionExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = SurveyNotFoundException.class)
    private String surveyNotFoundException(SurveyNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionCanNotHasAnswerException.class)
    private String questionCanNotHasAnswerException(QuestionCanNotHasAnswerException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = QuestionNotFoundException.class)
    private String questionNotFoundException(QuestionNotFoundException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }

    @ExceptionHandler(value = AnswerExistsException.class)
    private String answerExistsException(AnswerExistsException e) {
        return String.format("{\"error\":\"%s\"}", e.getMessage());
    }
}