package com.practice.exceptionhandler;

public class UserNotFoundException extends RuntimeException{

   public UserNotFoundException(String message) {
       super(message);
   }
}
