/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;

/**
 *a
 * @author Student
 */

        
import java.util.Scanner;

  class User {
        
         //
         String username;
         String password;
         String firstName;
         String lastName;
        
         //Username validation
         public boolean checkUserName(String username){
             return username.contains("_")&& username.length()<=5;
         }  
            
         //Password validation
         public boolean checkPasswordComplexity(String password){
         boolean hasUppercase = false;
         boolean hasNumber = false;
         boolean hasSpecial = false;
        
         for (char c : password.toCharArray()){
             if (Character.isUpperCase(c)) hasUppercase = true;
             if (Character.isDigit(c)) hasNumber = true;
             if(!Character.isLetterOrDigit(c)) hasSpecial = true;
         }
        
         return password.length()>=8 && hasUppercase && hasNumber && hasSpecial;
         }
        
         //Phone validation
         public boolean checkCellPhoneNumber(String phoneNumber){
             return phoneNumber.startsWith("+27") && phoneNumber.length() == 12;
         }
        
         //Register user(PRINT + RETURN)
         public String registerUser(String username, String password, String phoneNumber){
            
           
             if (!checkUserName(username)){
                 System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
                 return "fail";
             }
            
             if(!checkPasswordComplexity(password)){
                 System.out.println("Password is not correctly formatted, please ensure that the password contains atleast 8 characters, a capital letter, a number and a special character.");
                 return"fail";
             }
            
             if(!checkCellPhoneNumber(phoneNumber)){
                 System.out.println("Cell phone number incorectly formatted or does not contain international code.");
                 return"fail";
             }
           
             this.username = username;
             this.password = password;
            
             System.out.println("User registered successfully!");
             return"success";
         }
        
         //Login check(safe)
         public boolean loginUser(String username, String password)
         {
             return username.equals(this.username) && password.equals(this.password);
         }
        
         //Login message
         public void returnLoginStatus(boolean loginStatus){
             if (loginStatus){
                 System.out.println("Welcome" + firstName + "" + lastName + ", it is great to see you again.");
             }else{
                 System.out.println("Username or password incorrect, please try again.");
             }
         }
     }
    
     public class Registration {
         public static void main(String[] args){
             
             Scanner input = new Scanner(System.in);
             User user = new User();
            
             //=====REGISTER=====
             System.out.println("=== REGISTRATION ===");
            
             System.out.print("Enter first name: ");
             user.firstName = input.nextLine();
            
             System.out.print("Enter last name: ");
             user.lastName = input.nextLine();
            
             System.out.print("Enter username: ");
             String username = input.nextLine();
            
             System.out.print("Enter password: ");
             String password = input.nextLine();
            
             System.out.print("Enter phoneNumber(+27...):");
             String phoneNumber = input.nextLine();
            
             String result = user.registerUser(username, password, phoneNumber);
            
             //Only login if success
             if (result.equals("success")){
                 System.out.println("\n=== LOGIN ===");
                
                 System.out.print("Enter username: ");
                 String loginUser = input.nextLine();
                
                 System.out.print("Enter password: ");
                 String loginPass = input.nextLine();
                
                 boolean status = user.loginUser(loginUser, loginPass);
                 user.returnLoginStatus(status);
                
                 input.close();
             } }
     }
    
      
    
       
       
       
       
   
 
 

    

