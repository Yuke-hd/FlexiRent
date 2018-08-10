package flexiRent;

class Apartment extends Property{

	public Apartment(String propId, String streetNum, String streetName, String suburb, int bedNum) {
		super(propId, streetNum, streetName, suburb, bedNum, true, false);
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
				"RENTAL RECORD"+"\n"+
				super.getRecords()+"\n";
	}
	
	@Override
	public boolean performMaintenance() {
		System.out.println("Apartment "+super.getPropId()+" is now under maintenance");
		return true;
	}

	@Override
	public boolean completeMaintenance(DateTime completionDate) {
		
		return false;
	}
	
}