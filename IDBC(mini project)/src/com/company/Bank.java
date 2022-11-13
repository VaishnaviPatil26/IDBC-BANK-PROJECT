package com.company;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    CustomerInfo customerInfo = new CustomerInfo();
    Account account = new Account();

    Scanner sc = new Scanner(System.in);

    public void  menu(int custId) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
        boolean status = true;
        while (status){
            System.out.println("-----------------------------");
            System.out.println("--- Bank Menu ---");
            System.out.println("----------------------------");
            System.out.println("1. Check Account Details");
            System.out.println("2. Check Balance");
            System.out.println("3. Withdrawal");
            System.out.println("4. Deposit");
            System.out.println("5. Money Transfer");
            System.out.println("6. Check Interest");
            System.out.println("7. Recent Transaction");
            System.out.println("8. LogOut");
            System.out.println("-----------------------------");
            System.out.print("Choose :  ");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    String selectQuery = "select * from customer_Info where customerId =" + custId;
                    Statement st1 = con.createStatement();
                    ResultSet rs1 = st1.executeQuery(selectQuery);
                    while (rs1.next()) {
                        System.out.println("Customer Id : " + rs1.getInt(1) + "\nName : " + rs1.getString(2) + "\t" +
                                rs1.getString(3) + "\nMobile No : " + rs1.getLong(4) + "\nEmail : " + rs1.getString(5));
                    }
                    String selectQuery2 = "select * from bank_account where customerId =" + custId;
                    Statement st2 = con.createStatement();
                    ResultSet rs2 = st2.executeQuery(selectQuery2);
                    while (rs2.next()) {
                        System.out.println("Account No : " + rs2.getString(1) + "\nBalance : " + rs2.getInt(2) + "\nAccount Type : " +
                                rs2.getString(3) + "\nAccount Status : " + rs2.getString(4));
                    }
                    break;

                case 2:
                    String getBalance = "select balance from bank_account where customerId = " + custId;
                    Statement statement = con.createStatement();
                    ResultSet resultSet = statement.executeQuery(getBalance);
                    while (resultSet.next()) {
                       System.out.println("Account Balance :  " + resultSet.getInt("balance"));
                    }
                    break;

                case 3:
                    String getBalance1 = "select balance from bank_account where customerId = " + custId;
                    Statement statement1 = con.createStatement();
                    ResultSet resultSet1 = statement1.executeQuery(getBalance1);
                    while (resultSet1.next()) {
                        int currBalance = resultSet1.getInt("balance");
                        int updateBalance = account.withdrawal(currBalance , custId);

                        String updateBalanceQuary = "update bank_account set balance=" + updateBalance + " where customerId=" + custId;
                        Statement st6 = con.createStatement();
                        int row1 = st6.executeUpdate(updateBalanceQuary);
                    }
                    System.out.println("Transaction Successful");
                    break;

                case 4:
                    String getBalance2 = "select balance from bank_account where customerId = " + custId;
                    Statement statement2 = con.createStatement();
                    ResultSet resultSet2 = statement2.executeQuery(getBalance2);
                    while (resultSet2.next()) {
                        int currBalance = resultSet2.getInt("balance");
                        int updateBalance = account.deposit(currBalance , custId);

                        String updateBalanceQuary = "update bank_account set balance=" + updateBalance + " where customerId=" + custId;
                        Statement st6 = con.createStatement();
                        int row1 = st6.executeUpdate(updateBalanceQuary);
                    }
                    System.out.println("Transaction Successful");
                    break;

                case 5:
                    String getBalance3 = "select balance from bank_account where customerId = " + custId;
                    Statement statement3 = con.createStatement();
                    ResultSet resultSet3 = statement3.executeQuery(getBalance3);
                    while (resultSet3.next()) {
                        int currBalance = resultSet3.getInt("balance");
                        int updateBalance = account.transfer(currBalance,custId);
                        String updateBalanceQuary = "update bank_account set balance=" + updateBalance + " where customerId=" + custId;
                        Statement st6 = con.createStatement();
                        int row1 = st6.executeUpdate(updateBalanceQuary);
                    }
                    break;

                case 6:
                    try {
                        String getBalance4 = "select balance from bank_account where customerId = " + custId;
                        Statement statement4 = con.createStatement();
                        ResultSet resultSet4 = statement4.executeQuery(getBalance4);
                        while (resultSet4.next()) {
                            int currBalance = resultSet4.getInt("balance");
                            account.interest(currBalance ,custId);
                        }
                    }catch(Exception e){
                     System.out.println("Error--"+e);
                    }
                    break;

                case 7:
                    try {
                        String recentTransaction = "select * from bank_transaction where customerId= "+custId;
                        Statement statement5 = con.createStatement();
                        ResultSet resultSet5 = statement5.executeQuery(recentTransaction);
                        System.out.println("Trans Id\tDate\t\t\tTransType\tTransAmount ");
                        while (resultSet5.next()){
                            System.out.println(""+resultSet5.getInt(1)+"\t\t\t"+resultSet5.getString(2)+"\t\t"+resultSet5.getString(3)+"\t\t"+resultSet5.getInt(4));
                        }
                    }catch (Exception e){
                        System.out.println("Error--"+e);
                    }
                    break;

                case 8:
                    System.out.println("LogOut Successfully");
                    status = false;
                    break;

                default:
                    System.out.println("Invalid Input");
            }

        }
    }


    public void login(){
        try {
            System.out.print("Enter Your CustomerId : ");
            int custId = sc.nextInt();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
            String query = "select * from customer_Info where customerId = "+ custId;
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                System.out.println("Login Successfully");
                menu(custId);
            }else {
                System.out.println("Login Failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createAcount() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
        System.out.print("Enter Customer ID : ");
        int id = sc.nextInt();
        System.out.print("Enter your First Name : ");
        String fName = sc.next();
        System.out.print("Enter your Last Name : ");
        String lName = sc.next();
        System.out.print("Enter your Mobile No. : ");
        long mobileNo = sc.nextLong();
        System.out.print("Enter your Email ID : ");
        String email = sc.next();
        System.out.print("Enter Type of Account (Saving Or Pay) : ");
        String type = sc.next();
        System.out.print("Enter Opening balance : ");
        int balance = sc.nextInt();
        Random rand = new Random();
        String accountNo = "IDBC";
        for (int i = 0; i < 7; i++){
            int n = rand.nextInt(10);
            accountNo = accountNo + Integer.toString(n);
        }

        String custQuery = "insert into customer_Info values(" + id + ",'" + fName + "','" + lName + "'," + mobileNo +",'"+email+"')";
        String bankQuery = "insert into bank_account (accNumber,balance,acc_type,customerId) values('" + accountNo + "'," + balance + ",'" + type + "'," + id + ")";
        Statement st = con.createStatement();
        int rows = st.executeUpdate(custQuery);
        Statement st1 = con.createStatement();
        int rows1 = st1.executeUpdate(bankQuery);
        System.out.println("Account Created Successfully"); 
        menu(id);
    }
}
