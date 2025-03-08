package com.githubappi.errors;

public class Error404 {
    public int status;
    public String message;

    public Error404(int status, String message) {
        this.status = status;
        this.message = message;
    }
}