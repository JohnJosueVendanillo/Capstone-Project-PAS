package module3JavaCertificationProject;

import java.sql.*;
import java.util.Scanner;


/*
 * 
 * 
VehicleID
AccountID
PolicyID
DateofAcc
AddrAccHappen
DescOfAcc
DesOfDamage
EstimatedCostOfRep

 */

public class Vehicle {
	Scanner scan = new Scanner(System.in);
	
	
	
	private String make,model,color,vehicleType,fuelType;
	private int year;
	private double purchasePrice,premiumChargeOfVehicle;

	
	
	
	public void getVehicleDetails() {
		String make,model,color,vehicleType,fuelType;
		int year;
		double purchasePrice,premiumChargeOfVehicle;
		
		System.out.println("Enter details");
		System.out.println("Make: ");
		make = scan.nextLine();
		 
		System.out.println("Model: ");
		model = scan.nextLine();
		
		System.out.println("Color: ");
		color = scan.nextLine();
		
		System.out.println("Year: ");
		year = scan.nextInt();
		
		System.out.println("Vehicle Type: ");
		vehicleType = scan.nextLine();
		
		System.out.println("Fuel Type: ");
		fuelType = scan.nextLine();
		
		System.out.println("Purchase Price: ");
		purchasePrice = scan.nextDouble();
		
		System.out.println("Premium Charge: ");
		premiumChargeOfVehicle = scan.nextDouble();
		
		
	}
	
	
	
	
	
	
	
	
	
	public void addVehicleClaims() {
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pas_database","root", "Password123!");
			Statement stmt = conn.createStatement();

			String dateOfAccident;
			String locationOfAccident;
			String descriptionOfAccident;
			String descriptionOfDamage;
			double estimatedCostOfRepair;
			
			System.out.println("Date of Accident: ");
			dateOfAccident = scan.nextLine();
			System.out.println("Location of the accident: ");
			locationOfAccident = scan.nextLine();
			System.out.println("Description of the accident: ");
			descriptionOfAccident = scan.nextLine();
			System.out.println("Description of the damage: ");
			descriptionOfDamage = scan.nextLine();
			System.out.println("Estimated Cost of Repair: ");
			estimatedCostOfRepair = scan.nextDouble();
			
			
			
			String sql = "INSERT INTO vehicles (date_of_accident,location_of_accident,decription_of_accident,decription_of_damage,estimated_cost_of_repair) VALUES ('"+dateOfAccident+"','"+locationOfAccident+"','"+descriptionOfAccident+"','" + descriptionOfDamage+ "','"+estimatedCostOfRepair+"') ";
			stmt.execute(sql);
			
			
			String show = "SELECT * FROM vehicles ORDER BY vehicle_id DESC LIMIT 1";;
			ResultSet rset = stmt.executeQuery(show);
			
			
			System.out.format("%6s %12s %12s %12s %12s %12s %12s %12s","Vehicle ID","Date of Accident","Location of Accident","Description of Accident","Description of Damage","Estimated Cost of Repair","Policy ID","Account Policy Owner" + "\n");
			while(rset.next()) {
				int vehicle_id = rset.getInt("vehicle_id");
				String date_of_accident = rset.getString("date_of_accident");
				String location_of_accident = rset.getString("location_of_accident");
				String decription_of_accident = rset.getString("decription_of_accident");
				String decription_of_damage = rset.getString("decription_of_damage");
				double estimated_cost_of_repair = rset.getDouble("estimated_cost_of_repair");
				int policy_id = rset.getInt("policy_id");
				int account_policy_owner = rset.getInt("account_policy_owner");
				
				
				System.out.format("%6s %12s %12s %12s %12s %12s %12s %12s",vehicle_id,date_of_accident,location_of_accident,decription_of_accident,decription_of_damage,estimated_cost_of_repair,policy_id,account_policy_owner + "\n");
				
			}	
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
