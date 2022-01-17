package midtest.yshwang;

public class Car {
	protected String ownerName;
	protected String carNumber;
	
	public Car(String ownerName, String carNumber) {
		this.ownerName = ownerName;
		this.carNumber = carNumber;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public double getParkingfee(double parkingFee) {
		return parkingFee;
	}
	
	@Override
	public String toString() {
		String str = "차량소유주 : "+this.ownerName+", 차량번호: "+carNumber;
		return str;
	}

	
}
