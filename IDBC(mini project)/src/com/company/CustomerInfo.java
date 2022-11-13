package com.company;

public class CustomerInfo {
    private int customerId;private String customerF_Name;private String customerL_Name;private long mobileNo;private String email;
    public CustomerInfo(){}
    public CustomerInfo(int customerId, String customerF_Name, String customerL_Name, long mobileNo, String email) {
        this.customerId = customerId;
        this.customerF_Name = customerF_Name;this.customerL_Name = customerL_Name;this.mobileNo = mobileNo;this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerF_Name() {return customerF_Name;}
    public void setCustomerF_Name(String customerF_Name) {this.customerF_Name = customerF_Name;}
    public String getCustomerL_Name() {return customerL_Name;}
    public void setCustomerL_Name(String customerL_Name) {this.customerL_Name = customerL_Name;}
    public long getMobileNo() {return mobileNo;}
    public void setMobileNo(long mobileNo) {this.mobileNo = mobileNo;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}



}
