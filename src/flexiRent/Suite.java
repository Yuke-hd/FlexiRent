package flexiRent;

class Suite extends Property{
	DateTime _mntDate= new DateTime();
	public Suite(String propId, String streetNum, String streetName, String suburb
			) {
		super(propId, streetNum, streetName, suburb, 3, false, false);
		
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
				"Last maintenance:"+_mntDate+"\n"+
				"RENTAL RECORD"+"\n"+
				super.getRecords()+"\n";
	}
	
	@Override
	public boolean performMaintenance() {
		System.out.println("Suite "+super.getPropId()+" is now under maintenance");
		return true;
	}
	@Override
	public boolean completeMaintenance(DateTime completionDate) {
		_mntDate = completionDate;
		return false;
	}
	@Override
	public String toString() {
		return super.getPropId() + super.getStreetNum() + super.getStreetName() + super.getSuburb() + 
				this.getClass().getSimpleName() + super.getBedNum() + super.getTypeName()+this._mntDate;
	}

}