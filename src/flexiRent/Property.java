package flexiRent;

public abstract class Property {
	private String _propId;
	private String _streetNum;
	private String _streetName;
	private String _suburb;
	private String _bedNum;
	private boolean _isApt=false;
	private boolean _isRented=false;
	private String[] record=new String[10];
	public Property(String propId,String streetNum,String streetName,String suburb,String bedNum,boolean isApt,boolean isRented) {
		// TODO Auto-generated constructor stub
		_propId=propId;
		_streetNum=streetNum;
		_streetName=streetName;
		_suburb=suburb;
		_bedNum=bedNum;
		_isApt=isApt;
		_isRented=false;
		
	}
	public boolean getStat() {
		return _isRented;
	}
	public void setStat1() {
		_isRented=true;
	}
	public void setStat0() {
		_isRented=false;
	}
	public String getPropId() {
		return _propId;
	}
	public String _streetNum() {
		return _streetNum;
	}
	public String _streetName() {
		return _streetName;
	}
	public String _suburb() {
		return _suburb;
	}
	public String _bedNum() {
		return _bedNum;
	}
}
