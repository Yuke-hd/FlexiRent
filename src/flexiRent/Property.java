package flexiRent;

import java.util.*;

public abstract class Property {
	private String _propId;
	private String _streetNum;
	private String _streetName;
	private String _suburb;
	private String _bedNum;
	private boolean _isApt = false;
	private boolean _isRented = false;
	private Record[] record = new Record[10];
	private LinkedList<Record> propRecord = new LinkedList<Record>();

	public Property(String propId, String streetNum, String streetName, String suburb, String bedNum, boolean isApt,
			boolean isRented) {
		// TODO Auto-generated constructor stub
		_propId = propId;
		_streetNum = streetNum;
		_streetName = streetName;
		_suburb = suburb;
		_bedNum = bedNum;
		_isApt = isApt;
		_isRented = false;

	}

	public boolean getStat() {
		return _isRented;
	}

	public void setStat1() {
		_isRented = true;
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

	public String getBedNum() {
		return _bedNum;
	}
	public String setRecordID(String propID,String customerId) {
		DateTime today = new DateTime();
		return propID+"_"+customerId+"_"+today.toString();
	}
	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		this.setStat1();
		DateTime returnDay = new DateTime(rentDate,numOfRentDay);
		String recordID = setRecordID(getPropId(),customerId);
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

}
