package com.freelancing.services;

import com.freelancing.exceptions.ClientException;
import com.freelancing.models.base.BaseValidation;

/**
 * @author Alaa Jawhar
 */
public abstract class ServiceManager<Req, Res> {

    public abstract BaseValidation validate(Req req);

    public abstract Res execute(Req req);

    public Res run(Req req) {
        BaseValidation baseValidation = this.validate(req);
        if (baseValidation != null && baseValidation.getIsError() == Boolean.TRUE) {
            throw new ClientException(baseValidation.getErrorMessage());
        }
        return this.execute(req);
    }

}
