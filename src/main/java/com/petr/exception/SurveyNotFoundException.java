package com.petr.exception;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException() {
        super("Survey not found");
    }
}