package com.thrddqno.expense_tracker_api.common.exceptions.dto;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //lombok annotation
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private int status;
    private Instant timestamp;
    private String path;
}
