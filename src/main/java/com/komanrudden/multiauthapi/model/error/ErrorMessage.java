package com.komanrudden.multiauthapi.model.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.komanrudden.multiauthapi.config.CustomDateSerializer;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents an error message with a timestamp, message, and HTTP status.
 * <p>
 * This class is used to encapsulate error details in a structured format.
 * </p>
 *
 * <p>Fields:</p>
 * <ul>
 *   <li><b>time</b>: The timestamp of the error, serialized using `CustomDateSerializer`.</li>
 *   <li><b>message</b>: The error message.</li>
 *   <li><b>httpStatus</b>: The HTTP status associated with the error.</li>
 * </ul>
 */
@Data
public class ErrorMessage {

    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime time = LocalDateTime.now();

    private String message;

    private HttpStatus httpStatus;

    public ErrorMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
