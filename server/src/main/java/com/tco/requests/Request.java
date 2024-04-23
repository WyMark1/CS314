package com.tco.requests;

import com.tco.misc.BadRequestException;

public abstract class Request {

    protected String requestType;

    public String getRequestType() {
        return requestType;
    }

    public abstract void buildResponse() throws BadRequestException;
}