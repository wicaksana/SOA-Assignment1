
/**
 * TransportationServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package org.pahospital.www.transportationservice;

import java.util.ArrayList;

    /**
     *  TransportationServiceSkeleton java skeleton for the axisService
     */

public class TransportationServiceSkeleton{
	private ArrayList<Patient> patients;
	private ArrayList<Sample> samples; 

	
	
	public TransportationServiceSkeleton() {
		patients = new ArrayList<Patient>();
		samples = new ArrayList<Sample>();
	}


	/**
      * Auto generated method signature
      * 
      * @param patientOrder 
      * @return  
      */
        
	public void orderPatientTransport(org.pahospital.www.transportationservice.PatientOrder patientOrder) {
		patients.add(new Patient(patientOrder.getPatientID(),patientOrder.getRequestingUnit(),
				patientOrder.getDestinationUnit(), patientOrder.getTransportationDate()));
		
		System.out.println("New object created:\n " +
				"\tPatient id: " + patients.get(patients.size()-1).getPatientID() + "\n" +
				"\tRequesting unit: " + patients.get(patients.size()-1).getRequestingUnit() + "\n" +
				"\tDestination unit: " + patients.get(patients.size()-1).getDestinationUnit() + "\n" +
				"\tTransportation date: " + patients.get(patients.size()-1).getTransportationDate().toString()
				);
	}
     
         
        /**
         * Auto generated method signature
         * 
         * @param sampleOrder 
         * @return  
         */
        
	public void orderSampleTransport(org.pahospital.www.transportationservice.SampleOrder sampleOrder){
		samples.add(new Sample(sampleOrder.getPatientID(),sampleOrder.getRequestingUnit(),
				sampleOrder.getSampleID()));

		System.out.println("New object created:\n " +
				"\tPatient id: " + samples.get(samples.size()-1).getPatientID() + "\n" +
				"\tRequesting unit: " + samples.get(samples.size()-1).getRequestingUnit() + "\n" +
				"\tSample id: " + samples.get(samples.size()-1).getSampleID() + "\n" 
				);
		
	}
     
}
    