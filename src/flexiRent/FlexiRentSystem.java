package flexiRent;

import java.util.*;

public class FlexiRentSystem {
	Scanner sc = new Scanner(System.in);
	private ArrayList<Property> allProp = new ArrayList<Property>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FlexiRentSystem admin = new FlexiRentSystem();
		while (true) {
			System.out.println("**** FLEXIRENT SYSTEM MENU **** ");
			System.out.println("Add Property:                  1");
			System.out.println("Rent Property:                 2");
			System.out.println("Return Property:               3");
			System.out.println("Property Maintenance:          4");
			System.out.println("Complete Maintenance:          5");
			System.out.println("Display All Properties:        6");
			System.out.println("Exit Program:                  7");
			int x = sc.nextInt();
			sc.nextLine();
			switch (x) {
			case 1:
				admin.addProp();
				break;
			case 2:
				admin.rentProp();
				break;
			case 3:
				admin.returnProp();
				break;
			case 4:
				admin.performMaintenance();
				break;
			case 5:
				admin.completeMaintenance();
				break;
			case 6:
				admin.displayAllProp();
				break;
			case 7:
				System.exit(0);
			case 8: break;
			default:
				break;
			}// sc.close();
		}
	}

	public void addProp() {
		boolean isApt = false;
		System.out.println("1 for Apartment, 0 for Suite");
		int input = sc.nextInt();
		sc.nextLine();
		if (input == 1)
			isApt = true;
		else {
			if (input == 0) {
				isApt = false;
			} else
				return;
		}
		System.out.println("Property ID:");
		String _propId = sc.nextLine();
		for (int i =0; i<allProp.size();i++) {
			if (_propId.equals(allProp.get(i).getPropId())) return;
		}
		String propId = _propId.substring(0, 2);//extract first two letter "A_"
		if (isApt) {
			if (!propId.equals("A_"))
				return;
		} else {
			if (!propId.equals("S_"))
				return;
		}
		System.out.println("Street number:");
		String _streetNum = sc.nextLine();
		System.out.println("Street name:");
		String _streetName = sc.nextLine();
		System.out.println("Suburb");
		String _suburb = sc.nextLine();
		if (isApt) {
			System.out.println("Bed number");
			int _bedNum = sc.nextInt();
			sc.nextLine();
			Apartment apt = new Apartment(_propId, _streetNum, _streetName, _suburb, _bedNum);
			allProp.add(apt);
		} else {
			Suite suite = new Suite(_propId, _streetNum, _streetName, _suburb);
			allProp.add(suite);
		}
	}

	public boolean rentProp() {
		int objNum = inputPropID();
		if (objNum < 0 || allProp.get(objNum).getStat()) {
			System.out.println("Invalid property ID");
			return false;
		}
		System.out.println("Customer id:");
		String _custID = sc.nextLine();
		System.out.println("Rent date (dd/mm/yyyy):");
		String inputDate = sc.nextLine();
		DateTime _startDate = inputDate(inputDate);
		int weekDay = DateTime.calcWeekDay(inputDate);
		System.out.println("How many days?:");
		int _rentDay = sc.nextInt();sc.nextLine();
		if (allProp.get(objNum).getType()) {
		if (0 <= weekDay && weekDay <= 4) {	
			if (_rentDay <= 2 || _rentDay > 28)
				return false;
		} else if (_rentDay <= 3 || _rentDay > 28)
			return false;
		}
		allProp.get(objNum).setStat1(allProp.get(objNum).rent(_custID, _startDate, _rentDay));
		return true;
	}
	
	private DateTime inputDate(String Date) {
		String[] datePart = Date.split("/");
		int day = Integer.parseInt(datePart[0]);
		int month = Integer.parseInt(datePart[1]);
		int year = Integer.parseInt(datePart[2]);
		DateTime startDate = new DateTime(day, month, year);
		return startDate;//@return date for rentProp() and ReturnProp()
	}
	
	public boolean returnProp() {
		int objNum = inputPropID();
		if (objNum < 0||!allProp.get(objNum).getStat()) {
			System.out.println("Invalid property ID");
			return false;
		}
		System.out.println("Return date (dd/mm/yyyy):");
		String inputDate = sc.nextLine();
		DateTime returnDate = inputDate(inputDate);
		//allProp.get(objNum).returnProperty(returnDate);
		allProp.get(objNum).setStat1(allProp.get(objNum).returnProperty(returnDate));
		return true;
	}

	private int inputPropID() {
		System.out.println("Please input property ID:");
		String _propId = sc.nextLine();
		int objNum = -1;
		for (int i = 0; i < allProp.size(); i++) {
			if (_propId.equals(allProp.get(i).getPropId())) {
				objNum = i;
				// System.out.println("valid property ID");
			}
		}
		return objNum;
	}

	public void displayAllProp() {
		System.out.println("==================================");
		for (int i = 0; i < allProp.size(); i++) {
			// TODO add getDetails() in Property
			System.out.println(allProp.get(i).getDetails());
			System.out.println("==================================");
		}
	}

	public void performMaintenance() {
		int objNum = inputPropID();
		if (objNum < 0 || allProp.get(objNum).getStat()) {
			System.out.println("Invalid property ID");
			return;
		}
		allProp.get(objNum).setStat1(allProp.get(objNum).performMaintenance());
		return;
	}
	
	public void completeMaintenance() {
		System.out.println("Enter property id: ");
		int objNum = inputPropID();
		if (objNum < 0 || !allProp.get(objNum).getStat()) {
			System.out.println("Invalid property ID");
			return;
		}
		System.out.println("Maintenance completion date (dd/mm/yyyy): ");
		DateTime cmptDate = inputDate(sc.nextLine());
		allProp.get(objNum).setStat1(allProp.get(objNum).completeMaintenance(cmptDate));
	}

}
