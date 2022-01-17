package midtest.yshwang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLot {
	private final double parkingFee; //주차가격
	static Scanner scan = new Scanner(System.in); //scan : 값을 입력받기 위한 Scanner객체
	int ROW;
	int COL;
	int number;
	int[][] parkinglot; //파일에서 불러올 값을 배열에 저장
	Car[][] parkingCar; //입력되는 차량정보 자체를 저장할 수 있는 배열
	double totalIncome;

	public ParkingLot(String filename, double parkingFee) {
		this.parkingFee = parkingFee;
		
		try {
			Scanner scanner = new Scanner(new File(filename));
			ROW = scanner.nextInt();
			COL = scanner.nextInt();
			parkingCar = new Car[ROW][COL]; //2차원배열을 객체를 생성해야 사용가능(크기 초기화)
			parkinglot = new int[ROW][];
			for(int i=0;i<ROW;i++) {
				parkinglot[i] = new int[COL]; //2차원 배열 객체 생성
				for(int j =0;j<COL;j++) {
					parkinglot[i][j] = scanner.nextInt(); //scanner : 파일에서 불러온 값을 읽어내기 위한 Scanner객체
				}
			}
			scanner.close();
		}catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}


	public void Parking(Car car) {
		
		while(true) {
			showMap();
			
			System.out.println("주차할 자리를 선택하세요 (예, 1 1) : ");
			
			int i = scan.nextInt();
			int j = scan.nextInt();
			
			if(parkinglot[i-1][j-1]>2) {
				System.out.println("이미 다른 차량이 주차되어 있습니다. 다른 자리를 선택해주세요.");
				continue; //다시 while문의 첫번째로 돌아감
			}
			
			boolean check = false;
			switch (parkinglot[i-1][j-1]) {
			case 0 : 
				check = checkCarforDisabledPerson(car);
				if(!check)
					System.out.println("장애인 전용 주차 구역입니다.");
				break;
			case 1:
				check = checkSmallCar(car);
				if(!check)
					System.out.println("소형차 전용 주차 구역입니다.");
				break;
			case 2:
				check = true;
				break;
			}
			
			if(check == true) {
				parkinglot[i-1][j-1] = parkinglot[i-1][j-1] + 3;
				parkingCar[i-1][j-1] = car; //차 정보 그대로 저장
				System.out.println("주차가 완료 되었습니다.");
				showMap();
				return; //반복문 빠져나오기
			} else {
				System.out.println("다른 자리를 선택해 주세요.");
			}
		}
	}

	private boolean checkSmallCar(Car car) {
		if (car instanceof SmallCar)
			return true;
		else
			return false;
	}

	private boolean checkCarforDisabledPerson(Car car) {
		if (car instanceof CarForDisabledPerson)
			return true;
		else
			return false;
	}


	public void showMap() {

		System.out.println("============================================");
		for (int i = 0; i < COL; i++)
			System.out.print("\t" + (i + 1));
		System.out.println("\n");

		for (int i = 0; i < ROW; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < COL; j++) {
				switch (parkinglot[i][j]) {
				case 0:
					System.out.print("\t☆");
					break;
				case 1:
					System.out.print("\t○");
					break;
				case 2:
					System.out.print("\t◇");
					break;
				case 3:
					System.out.print("\t★");
					break;
				case 4:
					System.out.print("\t●");
					break;
				case 5:
					System.out.print("\t◆");
					break;
				}
			}
			System.out.println();
		}
		System.out.println("============================================");
	}

	public void PullOut() {
		System.out.print("출차할 차량번호를 입력하세요 : ");
		String carNumber = scan.next();
		for(int i =0;i<parkinglot.length;i++) {
			for (int j = 0; j < parkinglot[i].length; j++) {
				if (parkingCar[i][j] != null) {
					if (carNumber.equals(parkingCar[i][j].getCarNumber())) {
						totalIncome += parkingCar[i][j].getParkingfee(this.parkingFee);
						System.out.println("주차가격은 " + parkingCar[i][j].getParkingfee(this.parkingFee) + "원 입니다.");
						parkinglot[i][j] = parkinglot[i][j] - 3;
						return;

					}
				}
			}
		}
		System.out.println("차량 번호를 확인해 주세요.");
	}

	@Override
	public String toString() {
		String str = "기본 주차료 : "+this.parkingFee+"원\n";
		str += "전체 수입 : "+this.totalIncome;
		return str;
	}

}
