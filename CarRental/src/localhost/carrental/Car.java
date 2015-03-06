package localhost.carrental;

import java.util.Date;

public class Car {
	
	private String pickupLocation;
	private Date startAvailableDate;
	private String dropoffLocation;
	private Date endAvailableDate;
	
	public Car() {
	}

	public Car(String pickupLocation, Date startAvailableDate, String dropoffLocation,
			Date endAvailableDate) {
		this.pickupLocation = pickupLocation;
		this.startAvailableDate = startAvailableDate;
		this.dropoffLocation = dropoffLocation;
		this.endAvailableDate = endAvailableDate;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public Date getstartAvailableDate() {
		return startAvailableDate;
	}

	public void setstartAvailableDate(Date startAvailableDate) {
		this.startAvailableDate = startAvailableDate;
	}

	public String getDropoffLocation() {
		return dropoffLocation;
	}

	public void setDropoffLocation(String dropoffLocation) {
		this.dropoffLocation = dropoffLocation;
	}

	public Date getendAvailableDate() {
		return endAvailableDate;
	}

	public void setendAvailableDate(Date endAvailableDate) {
		this.endAvailableDate = endAvailableDate;
	}
	
}
