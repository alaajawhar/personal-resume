package com.freelancing.models.base;

import lombok.Data;

/**
 * @author Alaa jawhar
 */
@Data
public class ErrorResponse extends BaseModel{

    public ErrorResponse(String message){
        this.setErrorMessage(message);
    }

    public ErrorResponse(){
        this.setIsError(Boolean.TRUE);
    }
}
