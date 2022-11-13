package com.company;

public class accountinfo {
   String accNumber;
   int balance ;
   String acc_type;
   String acc_status = "Active"  ;

    public accountinfo(String accNumber, int balance, String acc_type, String acc_status) {
        this.accNumber = accNumber;
        this.balance = balance;
        this.acc_type = acc_type;
        this.acc_status = acc_status;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getAcc_status() {
        return acc_status;
    }

    public void setAcc_status(String acc_status) {
        this.acc_status = acc_status;
    }
}
