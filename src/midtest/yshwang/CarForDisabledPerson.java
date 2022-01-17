package midtest.yshwang;

public class CarForDisabledPerson extends Car{
	private String disabledNumber;
	private String disabledType;
	
	

	public CarForDisabledPerson(String ownerName, String carNumber, String disabledNumber, String disabledType) {
		super(ownerName, carNumber);
		this.disabledNumber = disabledNumber;
		this.disabledType = disabledType;
	}


	public String getDisabledNumber() {
		return disabledNumber;
	}


	public String getDisabledType() {
		return disabledType;
	}


	@Override
	public double getParkingfee(double parkingFee) {
		if (this.disabledType.equals("중증")) {
			return parkingFee * 0.3;
		}
		else {
			return parkingFee * 0.4;
		}
	}


	@Override
	public String toString() {
		
		return super.toString();
	}

	

}
