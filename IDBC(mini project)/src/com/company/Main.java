package com.company;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanObj = new Scanner(System.in);
        Bank bank = new Bank();
        while (true) {
            try {
                System.out.println("================================");
                System.out.println("!!!!!!Welcome to IDBC Bank!!!!!!");
                System.out.println("1.Login\n2.Sign Up\n3.Exit");
                System.out.print("Enter Your choice : ");
                int choice = scanObj.nextInt();
                switch (choice) {
                    case 1:
                        bank.login();
                        break;
                    case 2:
                        bank.createAcount();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Error -- "+e);
            }
        }
    }
}
