package models;

public class Property {
	private String propertyType;
	private boolean isListed;
	private int numOfBed;
	private int numOfBath;
	private boolean isFurnished;
	private int propertyId;
	private String cityQuadrant;
	private String propertyStatus;
	private String address;
	private int listingPeriod;
	private double amountOfFee;

	public Property(String propertyType, boolean isListed, int numOfBed, int numOfBath, boolean isFurnished,
			int propertyId, String cityQuadrant, String propertyStatus, String address, int listingPeriod,
			double amountOfFee) {
		this.propertyType = propertyType;
		this.isListed = isListed;
		this.numOfBed = numOfBed;
		this.numOfBath = numOfBath;
		this.isFurnished = isFurnished;
		this.propertyId = propertyId;
		this.cityQuadrant = cityQuadrant;
		this.propertyStatus = propertyStatus;
		this.address = address;
		this.listingPeriod = listingPeriod;
		this.amountOfFee = amountOfFee;
	}

	public String getPropertyType() {
		return this.propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public boolean isIsListed() {
		return this.isListed;
	}

	public boolean getIsListed() {
		return this.isListed;
	}

	public void setIsListed(boolean isListed) {
		this.isListed = isListed;
	}

	public int getNumOfBed() {
		return this.numOfBed;
	}

	public void setNumOfBed(int numOfBed) {
		this.numOfBed = numOfBed;
	}

	public int getNumOfBath() {
		return this.numOfBath;
	}

	public void setNumOfBath(int numOfBath) {
		this.numOfBath = numOfBath;
	}

	public boolean isIsFurnished() {
		return this.isFurnished;
	}

	public boolean getIsFurnished() {
		return this.isFurnished;
	}

	public void setIsFurnished(boolean isFurnished) {
		this.isFurnished = isFurnished;
	}

	public int getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getCityQuadrant() {
		return this.cityQuadrant;
	}

	public void setCityQuadrant(String cityQuadrant) {
		this.cityQuadrant = cityQuadrant;
	}

	public String getPropertyStatus() {
		return this.propertyStatus;
	}

	public void setPropertyStatus(String propertyStatus) {
		this.propertyStatus = propertyStatus;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getListingPeriod() {
		return this.listingPeriod;
	}

	public void setListingPeriod(int listingPeriod) {
		this.listingPeriod = listingPeriod;
	}

	public double getAmountOfFee() {
		return this.amountOfFee;
	}

	public void setAmountOfFee(double amountOfFee) {
		this.amountOfFee = amountOfFee;
	}

	public void ListedProperties() {

	}

}
