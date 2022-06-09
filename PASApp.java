package module3JavaCertificationProject;

import java.sql.*;

import java.util.Scanner;

public class PASApp {
	Scanner scan = new Scanner(System.in);

	public String[] selectionArr = {
						  "Create a new Customer Account.",
						  "Get a policy quote and buy the policy.",
						  "Cancel a specific policy","File an accident claim against a policy.(All claims must be maintained by system and should be searchable.)",
						  "Search for a Customer account ",
						  "Search for and display a specific policy",
						  "Search for and display a specific claim",
						  "Exit the PAS System"
						};
	
	CustomerAccount account = new CustomerAccount();	
	//PolicyHolder holder = new PolicyHolder();
	Policy policy = new Policy();
	Vehicle vehicles = new Vehicle();
	
//	DesignClass design = new DesignClass();

	public void MainMenu() {
		
		while(true) {
			try {
				int select;
				System.out.println("============================================================================================");
				System.out.println("Automobile Insurance Policy and Claims Administration System (PAS)\n");
				System.out.println("============================================================================================");
				System.out.println("Welcome! Please select your option:");
				for (int index = 0; index < selectionArr.length ; index++) {
					System.out.format("%6d %6s",(index+1),selectionArr[index] + "\n");
				}	
				System.out.print("Enter here: ");
				select = scan.nextInt();
				
				if (select == 1) {
					System.out.println("============================================================================================");
					
					System.out.println("You select Create a new Customer Account.");
					account.createAccount();
					
				}
				else if (select == 2) {
					System.out.println("============================================================================================");
					System.out.println("Get a policy quote and buy the policy.");
								
					policy.searchAccountNumber();
					//holder.display();
					//holder.showPolicyHolders();
					//vehicles.addVehicle();
				}
				else if (select ==3) {
					System.out.println("============================================================================================");
					
					System.out.println("Cancel a specific policy.");
					policy.cancelSpecificPolicy();
				}
				else if (select == 4) {
					System.out.println("============================================================================================");
					System.out.println("File an accident claim against a policy. All claims must be maintained by system and should be searchable.");
				}
				else if (select == 5) { // not yet finished need search via firstname & lastname
					System.out.println("============================================================================================");
					System.out.println("Search for a Customer account");
					account.searchAccount();
				}
				else if (select == 6) {
					System.out.println("============================================================================================");
					System.out.println("Search for and display a specific policy.");
					policy.searchPolicy();
				}
				else if (select == 7) {
					System.out.println("Search for and display a specific claim");
				}
				else if (select ==8) {
					System.out.println("Exit the PAS System");
					System.exit(0);

				}					
				
			}catch (Exception e) {
				System.out.println("Invalid Input,try again");
				scan.nextLine();
			}
			
		}
	}
	
	
	
}
