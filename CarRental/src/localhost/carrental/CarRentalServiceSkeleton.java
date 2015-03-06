
/**
 * CarRentalServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package localhost.carrental;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


/**
  *  CarRentalServiceSkeleton java skeleton for the axisService
  */
public class CarRentalServiceSkeleton {
	private Map<String,Car> cars;
	
	public CarRentalServiceSkeleton() {
		Calendar startAv1 = Calendar.getInstance();
		startAv1.set(2015, Calendar.MARCH,2);
		Calendar endAv1 = Calendar.getInstance();
		endAv1.set(2015, Calendar.MARCH,10);
		Calendar startAv2 = Calendar.getInstance();
		startAv2.set(2015, Calendar.MARCH,5);
		Calendar endAv2 = Calendar.getInstance();
		endAv2.set(2015, Calendar.MARCH,15);
		Calendar startAv3 = Calendar.getInstance();
		startAv3.set(2015, Calendar.MARCH,4);
		Calendar endAv3 = Calendar.getInstance();
		endAv3.set(2015, Calendar.MARCH,6);
		
		cars = new HashMap<String,Car>();
		cars.put("1", new Car("Enschede", startAv1.getTime(), "Hengelo", endAv1.getTime()));
		cars.put("2", new Car("Enschede", startAv2.getTime(), "Hengelo", endAv2.getTime()));
		cars.put("3", new Car("Almelo", startAv3.getTime(), "Hengelo", endAv3.getTime()));
	}


		/**
         * Auto generated method signature
         * 
         * @param rentCar 
         * @return rentCarResponse 
         */
	public localhost.carrental.GetAvailabilityResponse getAvailability(
		localhost.carrental.GetAvailability getAvailability) {
		
		ArrayList<String> availableCars = new ArrayList<String>();
		GetAvailabilityResponse response = new GetAvailabilityResponse();
		
		//iterate through the data
		for(Map.Entry<String, Car> entry : cars.entrySet()) {
			//see if the requested pickup location matches with the available cars
			//if(getAvailability.getPickupLocation().equals(entry.getValue().getPickupLocation()) &&
			//		getAvailability.getDropOffLocation().equals(entry.getValue().getDropoffLocation())) {
			if(getAvailability.getPickupLocation().equals(entry.getValue().getPickupLocation())) {	
				//if the requested pickup location matches with the car's base, see if the requested date
				//matches the available date of the cars
				//
				//overlapped date
				if(getAvailability.getPickupDate().after(entry.getValue().getstartAvailableDate()) &&
						getAvailability.getDropOffDate().before(entry.getValue().getendAvailableDate())) {
					availableCars.add(entry.getKey());
				}
			} else {
				System.out.println(getAvailability.getPickupLocation() + " does not match with "
						+ entry.getValue().getPickupLocation());
			}
		}
		
		//if there is no matched car..
		if(availableCars.isEmpty()) {
			response.setCarCode("no car available");
		} else {
			response.setCarCode(availableCars.toString());
		}
		
		return response;
	}

	
	public localhost.carrental.RentCarResponse rentCar(localhost.carrental.RentCar rentCar) {
		RentCarResponse response = new RentCarResponse();
		//check if the requested car is available...
		if(cars.get(rentCar.getCarCode()).getPickupLocation().equals(rentCar.getPickupLocation()) &&
				cars.get(rentCar.getCarCode()).getDropoffLocation().equals(rentCar.getDropOffLocation()) &&
				rentCar.getPickupDate().after(cars.get(rentCar.getCarCode()).getstartAvailableDate()) &&
				rentCar.getDropOffDate().before(cars.get(rentCar.getCarCode()).getendAvailableDate())) {
			response.setConfirmation("car " + rentCar.getCarCode() + " is available");
		} else {
			response.setConfirmation("car " + rentCar.getCarCode() + " is not available");
		}
		
		return response;
	}
}
    