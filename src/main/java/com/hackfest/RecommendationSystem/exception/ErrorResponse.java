package com.hackfest.RecommendationSystem.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ErrorResponse implements Serializable {
    private String message;
    private HttpStatus status;
    private Date timeStamp;
}
