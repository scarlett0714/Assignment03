package midtest.yshwang;

public class SmallCar extends Car{

	private int displacement; //배기량
	
	

	public SmallCar(String ownerName, String carNumber, int displacement) {
		super(ownerName, carNumber);
		this.displacement = displacement;
	}

	public int getDisplacement() {
		return displacement;
	}

	@Override
	public double getParkingfee(double parkingFee) {
		return parkingFee * 0.5;
	}

	@Override
	public String toString() {
		String str = "차량소유주 : "+ownerName+", 차량번호: "+carNumber+", 배기량 : "+displacement+"cc";
		return str;
	}
	
	
	
}
