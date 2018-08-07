package flexiRent;

public class Record {
	private String _recordID;
	private DateTime _startDate = new DateTime();
	private DateTime _endDate = new DateTime();
	private DateTime _returnDate = new DateTime();
	private double _rentFee;
	private double _lateFee;
	public Record(/*String recordID, DateTime startDate, DateTime endDate,*/DateTime returnDate, double rentFee, double lateFee) {
		/*_recordID = recordID.replace("/","");
		_startDate=startDate;
		_endDate=endDate;*/
		_returnDate=returnDate;
		_rentFee=rentFee;
		_lateFee=lateFee;
	}
	public Record(String recordID, DateTime startDate, DateTime endDate) {
		_recordID =recordID.replace("/","");
		_startDate=startDate;
		_endDate=endDate;
	}
	public String getRecordID() {
		return _recordID;
	}

	@Override
	public String toString() {
		return _recordID+":"+_startDate.toString()+":"+_endDate.toString()+":"+_returnDate.toString()
			+":"+_rentFee+":"+_lateFee;
	}

	public String getDetails(String recordID, DateTime startDate, DateTime endDate) {

		return "Record ID:" + "\t" + recordID + "\n" +
				"Rent Date:"+ "\t" + startDate.toString() + "\n" +
				"Estimated Return Date:"+"\t" + startDate.toString();
	}
}
