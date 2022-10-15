package com.freelancing.models;

/**
 * @author Alaa Jawhar
 */
public class SuccessResponse extends BaseModel {

    public SuccessResponse(String message) {
        this.setIsError(Boolean.FALSE);
        this.setErrorMessage(message);
    }

    public SuccessResponse() {
        this.setIsError(Boolean.FALSE);
    }
}
