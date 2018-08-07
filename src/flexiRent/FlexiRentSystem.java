package flexiRent;

import java.util.Scanner;
import java.util.ArrayList;

public class FlexiRentSystem {
	Scanner sc = new Scanner(System.in);
	private ArrayList<Property> allProp = new ArrayList<Property>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FlexiRentSystem admin = new FlexiRentSystem();
		admin.addProp();
		//admin.addProp();
		System.out.println();
		System.out.println();
		admin.rentProp();
		System.out.println();
		System.out.println();
		admin.returnProp();
	}

	public void addProp() {
		boolean _isApt = false;
		System.out.println("1 for Apartment, 0 for Suite");
		int input = sc.nextInt();
		sc.nextLine();
		if (input == 1)
			_isApt = true;
		else
			_isApt = false;
		System.out.println("Property ID:");
		String _propId = sc.nextLine();
		System.out.println("Street number:");
		String _streetNum = sc.nextLine();
		System.out.println("Street name:");
		String _streetName = sc.nextLine();
		System.out.println("Suburb");
		String _suburb = sc.nextLine();
		if (_isApt) {
			System.out.println("Bed number");
			String _bedNum = sc.nextLine();
			Apartment apt = new Apartment(_propId, _streetNum, _streetName, _suburb, _bedNum);
			allProp.add(apt);
		} else {
			Suite suite = new Suite(_propId, _streetNum, _streetName, _suburb);
			allProp.add(suite);
		}
	}

	public boolean rentProp() {
		int objNum = inputPropID();
		if (objNum<0) {
			System.out.println("Invalid property ID");
			return false;
		}
		System.out.println("Customer id:");
		String _custID = sc.nextLine();
		DateTime _startDate = inputDate();
		System.out.println("How many days?:");
		int _rentDay = sc.nextInt();sc.nextLine();
		return allProp.get(objNum).rent(_custID, _startDate, _rentDay);
	}
	private DateTime inputDate() {
		System.out.println("Rent date (dd/mm/yyyy):");
		String _inputDate = sc.nextLine();
		String[] _datePart = _inputDate.split("/");
		int _day = Integer.parseInt(_datePart[0]);
		int _month = Integer.parseInt(_datePart[1]);
		int _year = Integer.parseInt(_datePart[2]);
		DateTime _startDate = new DateTime(_day, _month, _year);
		return _startDate;
	}

	public boolean returnProp() {
		int objNum = inputPropID();
		if (objNum<0) {
			System.out.println("Invalid property ID");
			return false;
		}
		
		DateTime returnDate = new DateTime();
		allProp.get(objNum).returnProperty(returnDate);
		return true;
	}
	
	private int inputPropID() {
		System.out.println("Please input property ID:");
		String _propId = sc.nextLine();
		int objNum = -1;
		for (int i = 0; i < allProp.size(); i++) {
			if (_propId.equals(allProp.get(i).getPropId())) {
				objNum = i;
				System.out.println("valid property ID");
			} 
		}
		return objNum;
	}
}
