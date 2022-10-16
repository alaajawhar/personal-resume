package com.freelancing.models.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alaa Jawhar
 */
@Data
@NoArgsConstructor
public class BaseModel {

    private Boolean isError = Boolean.FALSE;
    private String errorCode;
    private String errorMessage;
}
