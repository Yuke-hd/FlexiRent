package flexiRent;

class Suite extends Property{

	public Suite(String propId, String streetNum, String streetName, String suburb
			) {
		super(propId, streetNum, streetName, suburb, 3, false, false);
		// TODO Auto-generated constructor stub
	}
	public String getDetails() {
		String status;
		if (super.getStat()) {
			status = "Rented";
		}else status ="Available";

		return "Property ID:" + "\t" + super.getPropId() + "\n" + 
				"Address:" + "\t" + super.getStreetNum()+" "+super.getStreetName()+" "+super.getSuburb()+ "\n" + 
				"Type:" + "\t" + this.getClass().getSimpleName()+"\n"+
				"Bedroom:"+ "\t"+ super.getBedNum()+"\n"+
				"Status:" +"\t"+ status+"\n"+
				"Last maintenance:"+"\n"+
				"RENTAL RECORD"+"\n"+
				super.getRecords()+"\n";
	}
}