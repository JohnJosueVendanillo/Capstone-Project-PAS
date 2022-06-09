package module3JavaCertificationProject;

import java.sql.*;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class CustomerAccount {
	Scanner scan = new Scanner (System.in);
	public static PASApp pas = new PASApp(); 
	
	public void createAccount() {
		
//		do {
//			
//		}while(fName.equals('q'));
		
		//need to create an exit validation
			
			try {
				
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
				System.out.println("Database connection successful!");
				
					String fName;
					String lName;
					String address;
					
					System.out.print("Firstname: ");
					fName = scan.nextLine();
								
					System.out.print("Lastname: ");
					lName = scan.nextLine();
					
					System.out.print("Address: ");
					address = scan.next();
								
			
					System.out.println("Inserting data to database.");
					Statement stmt = conn.createStatement();
					String addToDB = "INSERT INTO customers_create (first_name,last_name,address) VALUES ('"+ fName + "','" + lName + "','" + address +"')";
					
					stmt.execute(addToDB);
					System.out.println("Data successfully inserted!.");		
					System.out.println("Account successfully created.");
					
					
					String show = "SELECT * FROM customers_create ORDER BY account_number DESC LIMIT 1";
					ResultSet rset = stmt.executeQuery(show);
					
					
					while(rset.next()) {
						
						int id = rset.getInt("account_number");
						String showFirstName = rset.getString("first_name");
						String showLastName = rset.getString("last_name");
						String showAddress = rset.getString("address");
						System.out.println("======================Account details======================");
						System.out.format("%6s %12s %12s %18s", "account_number","firstname","lastname","address \n");
						System.out.format("%2d %15s %12s %18s", id,showFirstName , showLastName, showAddress + "\n");
					
						System.out.println("===========================================================");
					
					}
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
		
			
		
	}

	

//	public void showAccountDetails() {
//		
//		
//		try {
//			
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
//			System.out.println("Database connection successful!");
//			
//			Statement stmt = conn.createStatement();
//			
//			String show = "SELECT * FROM customers";
//			ResultSet rset = stmt.executeQuery(show);
//			
//			
//			System.out.format("%6s","Account Number");
//			
//			
//			
//			System.out.println("Policy Owned");
//			
//			
//			
//			System.out.println("Policy holders");
//			
//			
//			
//			/*
//			 * Account number
//			 * policy owned
//			 * policy holders associated  with the policies in the account
//			 * 
//			 * */
//			
//			
//			
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		}
//		
//		
//		
		
//	}
	
	
	//// search account - not yet finished
	public void searchAccount() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();
		
		
			System.out.println("Database connection successful!");
			System.out.println("============================================================================================");
			
//			String searchAccountNumber;
//			System.out.println("Enter Account number: ");
//			searchAccountNumber = scan.nextLine();
			
			String firstName;
			String lastName;
			System.out.println("First Name: ");
			firstName = scan.nextLine();
			System.out.println("Last Name: ");
			lastName = scan.nextLine();
			
			System.out.println("Result: ");
				
					String showUsingAccountNumber = "SELECT * FROM customers_create where first_name,last_name LIKE '"+ firstName +"'," + lastName +"'"; 
					
					ResultSet result = stmt.executeQuery(showUsingAccountNumber);
					
					if(result.next()) {
						
						System.out.println("Account details: ");	
						while(result.next()) {
							int account_number = result.getInt("account_number"); 	
							String first_Name = result.getString("first_Name");
							String last_Name = result.getString("last_Name");
							String address = result.getString("address");
						
						System.out.format("%5s %12s %14s %12s", account_number,first_Name,last_Name, address + "\n");	
						}
						
					}
					else {
						System.out.println("No record found.");
						pas.MainMenu();
					}
					
					
					
			
			System.out.println("============================================================================================");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}/// end of searchAccount
	
}/// end of Customer Account class



/*
 * 
 * 
 * Each Customer Account must have the following information:

A unique numeric 4-digit account number 								- OK
The account owner (first name, last name, and address) 					- OK
A list of policies owned by that account 								- 		
A list of policy holders associated with the policies in the account	-
 * 
 * 
 * 
 * 
 * 
 * 
 * */
