package flexiRent;

import java.util.*;

public abstract class Property {
	private String _propId;
	private String _streetNum;
	private String _streetName;
	private String _suburb;
	private int _bedNum;
	private boolean _isApt = false;
	private boolean _isRented = false;
	private LinkedList<Record> propRecord = new LinkedList<Record>();


	public Property(String propId, String streetNum, String streetName, String suburb, int bedNum, boolean isApt,
			boolean isRented) {
		_propId = propId;
		_streetNum = streetNum;
		_streetName = streetName;
		_suburb = suburb;
		_bedNum = bedNum;
		_isApt = isApt;
		_isRented = false;

	}

	public boolean getType() {
		return _isApt;
	}
	public String getTypeName() {
		if (_isApt) {
			return "Apartment";
		} else return "Suite";
	}
	public boolean getStat() {
		return _isRented;
	}

	public void setStat1(boolean stat) {
		_isRented = stat;
	}

	public void setStat0() {
		_isRented = false;
	}

	public String getPropId() {
		return _propId;
	}

	public String getStreetNum() {
		return _streetNum;
	}

	public String getStreetName() {
		return _streetName;
	}

	public String getSuburb() {
		return _suburb;
	}

	public int getBedNum() {
		return _bedNum;
	}
	
	public String setRecordID(String propID, String customerId) {
		DateTime today = new DateTime();
		return propID + "_" + customerId + "_" + today.toString();
	}

	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		//this.setStat1();
		DateTime returnDay = new DateTime(rentDate, numOfRentDay);
		String recordID = setRecordID(getPropId(), customerId);
		Record record = new Record(recordID, rentDate, returnDay);
		System.out.println(customerId + " rent " + this.getPropId() + " on " + rentDate.toString() + " for "
				+ numOfRentDay + " Days");
		propRecord.addFirst(record);
		if (propRecord.size() == 10) {
			propRecord.pollLast();
		}
		System.out.println(propRecord.get(0).toString());
		return true;
	}

	// 123
	public boolean returnProperty(DateTime returnDate) {
		double fee = this.getFee(this._isApt, propRecord.get(0).getStartDat(),propRecord.get(0).getEndDat(), returnDate);
		double lateFee=this.getLateFee(this._isApt, propRecord.get(0).getEndDat(), returnDate);
		Record record = new Record(propRecord.get(0).getRecordID(), propRecord.get(0).getStartDat(),
				propRecord.get(0).getEndDat(), returnDate, fee, lateFee);
		propRecord.set(0, record);
		if (propRecord.size() == 10) {
			propRecord.pollLast();
		}
		System.out.println(propRecord.get(0).toString());
		return true;
	}

	private String getCustID() {
		String[] recordPart = propRecord.get(0).getRecordID().split("_");
		return recordPart[1];
	}

	private double getFee(boolean isApt, DateTime startDate,DateTime endDate, DateTime returnDate) {
		double fee = 0.0;
		int diffDays;
		if (DateTime.diffDays(returnDate, endDate)>0) {
			diffDays=DateTime.diffDays(endDate, startDate);
		}else {
			diffDays=DateTime.diffDays(returnDate, startDate);
		}
		if (isApt) {
			int x = this.getBedNum();
			switch (x) {
			case 1:
				fee = diffDays * 143.0;
			case 2:
				fee = diffDays * 210.0;
			case 3:
				fee = diffDays * 319.0;
			default:
				break;
			}
		} else {
			fee = diffDays * 554.0;
		}
		return fee;
	}

	private double getLateFee(boolean isApt, DateTime endDate, DateTime returnDate) {
		double lateFee = 0.0;
		int lateDays = DateTime.diffDays(returnDate, endDate);
		if (lateDays <= 0) {
			lateFee = 0.0;
		} else {
			if (isApt) {
				int x = this.getBedNum();
				switch (x) {
				case 1:
					lateFee = lateDays * 1.15 * 143.0;
				case 2:
					lateFee = lateDays * 1.15 * 210.0;
				case 3:
					lateFee = lateDays * 1.15 * 319.0;
				}
			} else
				lateFee = lateDays * 662;

		}

		return lateFee;
	}
	
	public abstract boolean performMaintenance();

	public abstract boolean completeMaintenance(DateTime completionDate);

	public abstract String getDetails();

	public String getRecords() {
		String rec = " empty ";
		if (propRecord.size() == 1)
			rec = "";
		for (int i = 0; i < propRecord.size(); i++) {
			rec += propRecord.get(i).getDetails() + "\n";
		}
		return rec;
	}
}
