package com.freelancing.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alaa jawhar
 */
@Data
@AllArgsConstructor
public class ClientException extends RuntimeException{

    private String message;
}
