package com.example.relatorios.dao.expeptions;
public class DAOException extends Exception {
    public DAOException() {
    }

    public DAOException(String string) {
        super(string);
    }

    public DAOException(String string, Throwable throwable) {
        super(string, throwable);
    }
    
    
}