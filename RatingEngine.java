package module3JavaCertificationProject;

import java.util.Scanner;

/*
 * The Policy Rating algorithm i.e. premium calculation:
When quoting a price for a Policy, the PAS system must utilize a rating engine which will calculate the premium for each vehicle in a policy as follows:

P (premium) = (vp x vpf) + ((vp/100)/dlx)
P = calculated premium
vp = vehicle purchase price
vpf = vehicle price factor
dlx = num of years since driver license was first issued 

The vehicle price factor (vpf) must be looked up from the following table which must be used by the Rating Engine:
Vehicle Age is less than (yrs)
Vehicle Price factor
-----------------
1	1.0%
3	0.8%
5	0.7%
10	0.6%
15	0.4%
20	0.2%
40	0.1%
-------------------
The Rating engine will select the highest factor for the vehicle age matches.
 * 
 */

public class RatingEngine {
	Scanner scan = new Scanner (System.in);
	
	private double vp; //vehicle purchase price
	private double vpf; //vpf = vehicle price factor
	private double yrs; //vehicle age
	private double dlx; //num of years since driver license was first issued 
	
	
	
	public double getVp() {
		return vp;
	}
	
	public void setVp(double vp) {
		this.vp = vp;
	}

	public double getVpf() {
		return vpf;
	}

	public void setVpf(double vpf) {
		this.vpf = vpf;
	}

	public double getYrs() {
		return yrs;
	}

	public void setYrs(double yrs) {
		this.yrs = yrs;
	}

	public double getDlx() {
		return dlx;
	}

	public void setDlx(double dlx) {
		this.dlx = dlx;
	}


	public void display() {
		
		double vehiclePrice;
		double vehicleAge;
		double vehiclePriceFactor;
		double licensedIssuedDate;
		
		
		System.out.println("Vehicle purchase price: ");
		vehiclePrice = scan.nextDouble();
		System.out.println("Vehicle Age: ");
		vehicleAge = scan.nextDouble();
		System.out.println("Vehicle price factor: ");
		vehiclePriceFactor = scan.nextDouble();
		System.out.println("Driver's License issuance date: ");
		licensedIssuedDate = scan.nextDouble();
	}
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	public double RatingEngineProcessor() {
		double policyPremium = 0;
		
		
		policyPremium = (vp * vpf) + ((vp/100)/dlx);
		
		
		return policyPremium;
	}

}
