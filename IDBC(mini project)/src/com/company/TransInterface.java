package com.company;

public interface TransInterface {
    int transfer(int balance,int id);
    int withdrawal(int balance , int id);
    int deposit(int balance , int id);
    void interest(int balance , int id);
}
