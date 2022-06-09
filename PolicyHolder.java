package module3JavaCertificationProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PolicyHolder {
	Scanner scan = new Scanner(System.in);
	public static PASApp pas = new PASApp(); 
	
//	public void display() {
//		
//		while(true) {
//			
//			try {
//				System.out.println("============================================================================================");
//				
//				
//				int selector;
//				
//				System.out.println("Would you like to add / use existing Policy holders?");
//				System.out.println("Press 1 to add new Policy holder.");
//				System.out.println("Press 2 to use existing Policy holder.");
//				System.out.println("Press 0 to cancel.");
//				System.out.print("Enter here: ");
//				selector = scan.nextInt();
//				
//				if (selector == 1) {
//					System.out.println("============================================================================================");
//					createPolicyHolder();
//					// Vehicle method here
//				}
//				
//				else if(selector == 2){
//					System.out.println("============================================================================================");
//					System.out.println(" use existing policy holder testing");
//					//
//				}
//				
//				if (selector == 0) {
//					System.out.println("============================================================================================");
//					System.out.println("Action cancelled.");
//					pas.MainMenu();
//				}
//				
//				else{
//					System.out.println("============================================================================================");
//					System.out.println("invalid input, Try again");
//				}
//				
//			}catch (Exception e) {
//				
//				//System.out.println("invalid input, Try again");
//				scan.nextLine();
//			}
//			
//			System.out.println("============================================================================================");
//			System.out.println("Current Policy Holders");
//			showPolicyHolders();
//			System.out.println("============================================================================================");
//			
//		
//	
//		}
//			
//	} // end of display method

	
	
	
	
	
	
	public void showPolicyHolders() {

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();
			
			
			String showPolicyHolders = "SELECT * FROM policyHolders;";		
			
			ResultSet rset  = stmt.executeQuery(showPolicyHolders);
			
			
			System.out.format("%5s %12s %14s %12s %14s %12s %14s %12s %12s","id","firstName" , "lastName","birthDate","address","driversLicenseNumber","driversLicenseIssued","Policy id","Account or Policy Owner" + "\n" );
			
			while (rset.next()) {
				String id = rset.getString("policy_holder_id");
				String firstName = rset.getString("first_name");
				String lastName = rset.getString("last_name");
				String birthDate = rset.getString("birth_date");
				String address = rset.getString("address");
				String driversLicenseNumber = rset.getString("driver_license_number");
				String driversLicenseIssued = rset.getString("driver_license_issued");
				String policy_id = rset.getString("policy_id");
				String account_policy_owner = rset.getString("account_policy_owner");
				
				 System.out.format("%5s %12s %14s %12s %14s %12s %14s %12s %12s",id ,firstName , lastName,birthDate,address,driversLicenseNumber,driversLicenseIssued,policy_id,account_policy_owner + "\n");	
			}
						
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	public void createPolicyHolder() {
		try {
		String firstName,lastName,dateOfBirth,address,driversLicenseNumber,driversLicenseIssued,policyHolderType;
		
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();
			
			System.out.println("Create Policy holder");
			
			System.out.println("Account Number: ");
			int customer_id = scan.nextInt();
			
			ResultSet rset = stmt.executeQuery("SELECT * from customers_create where account_number LIKE '" + customer_id + "'");
			
			if(rset.next()) {
				
				System.out.print("FirstName: ");
				firstName = scan.next();
				System.out.print("LastName: ");
				lastName = scan.next();
				System.out.print("Date of Birth: ");
				dateOfBirth = scan.next();
				System.out.print("Address: ");
				address = scan.next();
				System.out.print("Driver's Lisence Number: ");
				driversLicenseNumber = scan.next();
				System.out.print("Driver's Lisence Issued Date: ");
				driversLicenseIssued = scan.next();		
				
				
				String AddToDB = "INSERT INTO policyHolders (First_name,Last_name,birth_date,address,driver_license_number,driver_license_issued)"
						+ " VALUES  ('"+ firstName + "','" + lastName + "','" + dateOfBirth + "','" + address + "','" + driversLicenseNumber + "','" + driversLicenseIssued +"')";
						
				stmt.executeUpdate(AddToDB);
				
				
				System.out.println("New Policy holder has been successfully created!");
				
				
				
				//shows the latest created policy holder
				ResultSet resultPolicyHolder = stmt.executeQuery("SELECT * FROM policyholders ORDER BY policy_holder_id DESC LIMIT 1");
				
				System.out.format("%6s %6s %12s %12s %12s %12s %12s %12s %12s %12s","PolicyHolderID","Type","FirstName","LastName","Date of Birth","Address","Driver's License #","D.L. Issued","Policy ID","Account Owner" + "\n");
				while(resultPolicyHolder.next()) {
					String policy_holder_id = resultPolicyHolder.getString("policy_holder_id");
					String first_name = resultPolicyHolder.getString("first_name");
					String last_name = resultPolicyHolder.getString("last_name");
					String birth_date = resultPolicyHolder.getString("birth_date");
					String holderAddress = resultPolicyHolder.getString("address");
					String driver_license_number = resultPolicyHolder.getString("driver_license_number");
					String driver_license_issued = resultPolicyHolder.getString("driver_license_issued");
					int policy_id = resultPolicyHolder.getInt("policy_id");
					int account_policy_owner = resultPolicyHolder.getInt("account_policy_owner");
					
					System.out.format("%6s %12s %12s %12s %12s %12s %12s %12s %12s",policy_holder_id, first_name,last_name,birth_date,holderAddress,driver_license_number,driver_license_issued,policy_id,account_policy_owner + "\n");
				}
				
				
				
				
				

			}
			else {
				System.out.println("create account first");
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
		
	}
	
	
	
	
	
		
	
	
	
} // end of policy holder class




/*


First name and last name
Date of birth
Address
Driver’s license number
Date on which driver’s license was first issued.


*/



