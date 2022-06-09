package module3JavaCertificationProject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class Policy{
	
	Scanner scan = new Scanner(System.in);
	public static PASApp pas = new PASApp();
	PolicyHolder holder = new PolicyHolder();
	Vehicle vehicles = new Vehicle();
	
	
	// search account number before purchase policy method occurs
	public void searchAccountNumber() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();

			int accountNumber;
			
			System.out.println("Enter Account number: " );
			accountNumber = scan.nextInt();
			
			ResultSet rset = stmt.executeQuery("SELECT * from customers_create where account_number LIKE '" + accountNumber + "'");
			//Display here searched account number & policy owned. 
			//then ask if want to purchase another policy.
			
			if(rset.next()) {
				
				int policyOwned = 0;
				ResultSet policyOwnedCounter = stmt.executeQuery("select COUNT(policy_owner) from policy where policy_owner = " + accountNumber);
				
				while (policyOwnedCounter.next()) {
					policyOwned = policyOwnedCounter.getInt(1);
				}
				
				int selector=0;
				System.out.println("Account Number: " + accountNumber);
				System.out.println("Policy owned: " + policyOwned);	
				
			
				//Quotation here
				//Add new  or use existing policy holder
				
				int policyHolderSelector;
				System.out.println("\nProceed for the Quotation.\nEnter 1.) continue or Any key to cancel.");
				System.out.println("Enter here: ");
				policyHolderSelector = scan.nextInt();
				
				
				if(policyHolderSelector==1) {
					
					
					
					//how many policy holders ?
					
					System.out.println("How many policy holders?");
					System.out.println("Enter here: ");
					int inputPolicyHolders =scan.nextInt();
					
					
					for(int count = 0;count <inputPolicyHolders;count++) {
						
						//ask for effectivity date & expiration date
						//policy holder creation
						//Get Vehicle details & Premium charge
						
						
						policyDetails(accountNumber); // effectivity
						holder.createPolicyHolder();
						vehicles.getVehicleDetails();
					}
					
					//show quotation method here
					showQuotation();
					
				}
				else {
					
					pas.MainMenu();
				}
				
			
			}
			else {
				System.out.println("CREATE ACCOUNT FIRST!");		
				pas.MainMenu();
			}					
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void showQuotation() {
		
		
			System.out.format("%6s %12s %12s","Effectivity Date","Expiration Date","Premium Charge");
			
				
			

		
		
		
	}
	
	

	
	
	
	
	// purchase policy method
	public void policyDetails(int accountNumber) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();
			
			int month,day,year;
			
			System.out.println("Enter Effectivity Date (MM-dd-YYYY). ");
			System.out.println("Month (1-12): ");
			month  = scan.nextInt()-1;
			System.out.println("Day (1-30/31): ");
			day = scan.nextInt();
			System.out.println("Year: ");
			year = scan.nextInt();
						
			processPolicyQuery(month, day, year, accountNumber);
					
//			// shows the effective date and expiration date
//			String show = "SELECT * FROM policy ORDER BY policy_number DESC LIMIT 1";
//			ResultSet rset = stmt.executeQuery(show);
//
//			System.out.format("%2s %12s %12s" ,"Policy ID","Effective Date","Expiration Date\n");
//			while(rset.next()) {
//				int policy_id = rset.getInt("policy_number");
//				java.sql.Date policy_effective_date = rset.getDate("policy_effective_date");
//				java.sql.Date policy_expiration_date = rset.getDate("policy_expiration_date");
//				
//				System.out.format("%2s %16s %16s" ,policy_id , policy_effective_date,policy_expiration_date +"\n");
//			}
					
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	// Effectivity Date & Expiration Date
	public void processPolicyQuery(int month,int day, int year,int accountNumber) {
		
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
		
			//Statement stmt = conn.createStatement();
			
			String query = "INSERT into policy(policy_effective_date,policy_expiration_date,policy_owner) VALUES (?,?,?)";
			PreparedStatement preparedStatementquery = conn.prepareStatement(query); // to prevent sql injection
			
			 Calendar calendar = Calendar.getInstance();
			  
	         calendar.set(year,month,day);
	         java.util.Date dateUtil = calendar.getTime();
	         java.sql.Date date = new java.sql.Date(dateUtil.getTime());
	         
	         calendar.set(year,month + 6,day);
	         java.util.Date dateExpUtil = calendar.getTime();
	         java.sql.Date dateExp = new java.sql.Date(dateExpUtil.getTime());
	         
	         
	         preparedStatementquery.setDate(1, (java.sql.Date)date);
	         preparedStatementquery.setDate(2, (java.sql.Date)dateExp);
	         preparedStatementquery.setInt(3, accountNumber);
	         
	         preparedStatementquery.execute();
	         
	         
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	 	
	}
	
	
	
//public void processEditExpDateQuery(int month,int day, int year,int accountNumber) {
//		
//		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
//		
//			Statement stmt = conn.createStatement();
//			
//			String query = "UPDATE into policy(policy_expiration_date) VALUES (?)";
//			PreparedStatement preparedStatementquery = conn.prepareStatement(query); // to prevent sql injection
//			
//			 Calendar calendar = Calendar.getInstance();
//			 
//	         
//	         calendar.set(year,month + 6,day);
//	         java.util.Date dateExpUtil = calendar.getTime();
//	         java.sql.Date dateExp = new java.sql.Date(dateExpUtil.getTime());
//	         
//	         
//	       
//	         preparedStatementquery.setDate(2, (java.sql.Date)dateExp);
//	         preparedStatementquery.setInt(3, accountNumber);
//	         
//	         preparedStatementquery.execute();
//	         
//	         
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//	 	
//	}
//	
	
	// #3 Cancel a specific policy
	public void cancelSpecificPolicy() {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();
			
			String showPolicyTable = "SELECT * FROM policy";
			ResultSet rset = stmt.executeQuery(showPolicyTable);	
			
			
			System.out.format("%2s %12s %12s %12s %12s" ,"Policy ID","Effective Date","Expiration Date","Policy Owned","Policy Premium"+"\n");
			while(rset.next()) {
				int policy_id = rset.getInt("policy_number");
				java.sql.Date policy_effective_date = rset.getDate("policy_effective_date");
				java.sql.Date policy_expiration_date = rset.getDate("policy_expiration_date");
				
				
				System.out.format("%5s %16s %16s" ,policy_id , policy_effective_date,policy_expiration_date +"\n");
			}
			
			
			// Cancel a specific policy (i.e change the expiration date of a policy to an earlier date than originally specified) 
			int selector;
			
			System.out.println("Press 1 to change expiration date of a policy.");
			System.out.println("Press 2 to delete a policy.");
			System.out.println("Press 0 to cancel");
			System.out.print("Enter here:");
			selector = scan.nextInt();
			if (selector == 1 ) {
				
				
				System.out.println("Changing an expiration date of policy");
			}
			
			if (selector == 2) {
				int deleteSelector = 0;
				System.out.println("Select and Enter Policy ID to delete");
				deleteSelector = scan.nextInt();
				
				
				String delete = "delete from policy where policy_number = " + deleteSelector;
				stmt.executeUpdate(delete);
				
				System.out.println("Delete Successful");
			if (selector == 0) {
				pas.MainMenu();
			}
			
				//needs validator if input id is not existing must output "NO POLICY RESULT FOUND" 
			} 
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}
	
	
	
	public void searchPolicy() {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();
		
		
			System.out.println("Database connection successful!");
			
			
			System.out.println("============================================================================================");
			
			int searchPolicy;
			System.out.println("Enter Policy ID: ");
			searchPolicy = scan.nextInt();
			
			ResultSet rset = stmt.executeQuery("SELECT * FROM policy where policy_number = "+ searchPolicy);
			System.out.println("Result: ");
			if(rset.next()) {
				
				ResultSet result = stmt.executeQuery("SELECT * FROM policy where policy_number = "+ searchPolicy);
				System.out.println("======================Policy details======================");
			
				System.out.format("%2s %14s %12s %12s %12s" ,"Policy ID","Effective Date","Expiration Date","Policy owner","Policy Premium"+ "\n");
				while(result.next()) {
					
					int policy_number = result.getInt("policy_number");
					java.sql.Date policy_effective_date = result.getDate("policy_effective_date");
					java.sql.Date policy_expiration_date = result.getDate("policy_expiration_date");
					int policy_owner = result.getInt("policy_owner");
					int policy_premium = result.getInt("policy_premium");
					
					System.out.format("%2s %15s %12s %18s %12s",  policy_number,policy_effective_date,policy_expiration_date, policy_owner , policy_premium + "\n");
				
					System.out.println("===========================================================");
				
				}
				
			}
			else {
				System.out.println("No record found.");
				pas.MainMenu();
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
//public void searchPolicy() {
//		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
//			Statement stmt = conn.createStatement();
//		
//		
//			System.out.println("Database connection successful!");
//			
//			int searchPolicy;
//			System.out.println("============================================================================================");
//			
//			System.out.println("Enter Policy ID: ");
//			searchPolicy = scan.nextInt();
//			
//			System.out.println("Result: ");
//				
//					String showSearchedPolicy = "SELECT * FROM policy where account_number LIKE '"+ searchPolicy +"'"; 
//					
//					ResultSet result = stmt.executeQuery(showSearchedPolicy);
//					
//					if(result.next()) {
//						
//						System.out.println("Account details: ");	
//						while(result.next()) {
//							int policy_id = result.getInt("policy_id");
//							String first_Name = result.getString("first_Name");
//							String last_Name = result.getString("last_Name");
//							String address = result.getString("address");
//						
//						System.out.format("%5s %12s %14s %12s", policy_id,first_Name,last_Name, address + "\n");	
//						}
//						
//					}
//					else {
//						System.out.println("No record found.");
//						pas.MainMenu();
//					}
//					
//					
//					
//			
//			System.out.println("============================================================================================");
//			
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//	
//	}/// end of searchAccount
	
}
