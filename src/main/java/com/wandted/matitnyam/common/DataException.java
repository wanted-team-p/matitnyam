package com.wandted.matitnyam.common;

public class DataException {
    public static class NoDataException extends BaseException{
        public NoDataException(String message) {
            super(message);
        }
    }
}
