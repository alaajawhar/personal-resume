package com.freelancing.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Alaa Jawhar
 */
@Data
public class ContactMeRequest {

    @NotNull @NotEmpty
    private String name;
    @Email @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String subject;
    @NotNull @NotEmpty
    private String message;

}
