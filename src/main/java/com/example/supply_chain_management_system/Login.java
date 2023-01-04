package com.example.supply_chain_management_system;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    private byte[] getSHA(String input){
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String getEncryptedPassword(String password){
        String encryptedPassword = "";
        try{
            BigInteger number = new BigInteger(1, getSHA(password));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean customerLogin(String email, String password){
        String query = String.format("SELECT * FROM CUSTOMER WHERE email = '%s' AND password = '%s'",email, password );
        try{
            DatabaseConnection dbCon = new DatabaseConnection();
            ResultSet rs = dbCon.getQueryTable(query);
            if(rs != null && rs.next()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) { //write psvm + enter for shortcut
//        Login login = new Login();
//        System.out.print(login.customerLogin("iamrohanvidhate@gmail.com", "123456"));
//    }

//    public static void main(String[] args) {
//        Login login = new Login();
//        System.out.print(login.getEncryptedPassword("abc123"));
//    }

}
