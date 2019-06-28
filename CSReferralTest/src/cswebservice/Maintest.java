package cswebservice;

public class Maintest {

	public static void main(String[] args) {
		
		System.out.println("Running web service");
		
		System.out.println(new CSReferral().getCSReferralSoap().getInsuranceCompanies());

		System.out.println("done executing web service");
	}

}
