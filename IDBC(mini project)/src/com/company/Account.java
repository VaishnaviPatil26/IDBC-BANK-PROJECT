package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Account implements TransInterface{

    Scanner sc = new Scanner(System.in);

    @Override
    public int transfer(int balance,int id) {
        int result = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
            System.out.print("Enter Customer Id number to transfer Amount : ");
            String cusId = sc.next();
            System.out.print("Enter your Amount : ");
            int amount = sc.nextInt();
            String getBalance2 = "select balance from bank_account where customerId = "+ cusId;
            Statement statement2 = con.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(getBalance2);
            while (resultSet2.next()) {
                int currBalance = resultSet2.getInt("balance");
                int updateBalance = currBalance+amount;
                Math.abs(updateBalance);
                boolean check = balance<amount;
                if (check){
                    // transfer to the account showing transaction
                    Date newDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateStr = dateFormat.format(newDate);
                    String transType = "transfer";
                    String transferQuery =  "insert  into bank_transaction (transaction_date,trans_type,trans_amount,customerId) values ('"+dateStr +"','"+transType+"',"+amount+","+id+")";
                    Statement transfer = con.createStatement();
                    int row3 = transfer.executeUpdate(transferQuery);

                    //transfer from the account showing transaction
                    String updateBalanceQuary ="update bank_account set balance="+updateBalance+" where customerId="+cusId;
                    Statement st6=con.createStatement();
                    int row1 = st6.executeUpdate(updateBalanceQuary);
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    String str = format.format(date);
                    String traType = "deposite";
                    String withdrawalQuery =  "insert  into bank_transaction (transaction_date,trans_type,trans_amount,customerId) values ('"+str +"','"+traType+"',"+amount+","+cusId+")";
                    Statement trans = con.createStatement();
                    int row2 = trans.executeUpdate(withdrawalQuery);
                    System.out.println("Transaction Successful");
                }else {
                    System.out.println("Insufficient Balance");
                }
            }
            result = balance-amount;
            Math.abs(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int withdrawal(int balance , int id) {
        int result=0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
            System.out.print("Enter Amount you want to Withdraw : ");
            int amount = sc.nextInt();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String str = format.format(date);
            String transType = "withdrawal";
            String withdrawalQuery =  "insert  into bank_transaction (transaction_date,trans_type,trans_amount,customerId) values ('"+str +"','"+transType+"',"+amount+","+id+")";
            Statement trans = con.createStatement();
            int row2 = trans.executeUpdate(withdrawalQuery);
            boolean check = balance>amount;
            if (check){
                Math.abs(result =balance-amount);
            }else {
                System.out.println("Insufficient Balance");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deposit(int balance , int id) {
        int result = 0;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
            System.out.print("Enter Amount you want to deposit : ");
            int amount = sc.nextInt();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String str = format.format(date);
            String transType = "deposite";
            String depositeQuery =  "insert  into bank_transaction (transaction_date,trans_type,trans_amount,customerId) values ('"+str +"','"+transType+"',"+amount+","+id+")";
            Statement trans = con.createStatement();
            int row2 = trans.executeUpdate(depositeQuery);
            result =balance+amount;
        }catch (Exception e){
            System.out.println("Error --"+e);;
        }
        return result;
    }

    @Override
    public void interest(int balance ,int id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBCBank", "root", "Pass@123");
            String accountQuery = "select acc_type from bank_account where customerId= "+id;
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(accountQuery);
            while (resultSet.next()){
                String getType = resultSet.getString("acc_type");
                //System.out.println(getType);
                String str = new String("Saving");
                if(getType.equals(str)){
                    double intrest = (balance * 2.5)/100;
                    System.out.println("Interest Amount : "+ (balance + intrest));
                }
                else {
                    System.out.println("Pay account is not eligible for calculating Interest");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
