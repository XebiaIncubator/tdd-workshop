package com.xebia.tdd.training.exceptions;

public class InvalidArgumentsException extends RuntimeException{

    private static final long serialVersionUID = -2270354295409714513L;

    public InvalidArgumentsException(String message) {
        super(message + " Please enter the correct arguments. "
                + "Example -> Regular:16Mar2009,17Mar2009,18Mar2009");
    }

}
