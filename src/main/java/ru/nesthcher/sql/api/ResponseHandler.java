package ru.nesthcher.sql.api;

public interface ResponseHandler<H, R> {
    R handleResponse(final H handle) throws Exception;
}