
/**
 * LabServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package org.pahospital.www.labservice;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.pahospital.www.labcallbackservice.LabCallbackServiceStub;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
    /**
     *  LabServiceSkeleton java skeleton for the axisService
     */
public class LabServiceSkeleton{
	Integer labOrderCounter = 0;
	private static final String defaultUrl = "http://localhost:8080/LaboratoryClient/services/LabCallbackService";
	public static org.pahospital.www.labcallbackservice.LabCallbackServiceStub stub;
	
	public LabServiceSkeleton() {
		
	}
        /**
         * Auto generated method signature
         * 
         * @param labOrder 
         * @return labOrderId 
         * @throws InterruptedException 
         * @throws RemoteException 
         */
        
	public org.pahospital.www.labservice.LabOrderId orderLabTest(org.pahospital.www.labservice.LabOrder labOrder) 
			throws InterruptedException, RemoteException {
		LabOrderId response = new LabOrderId();
		stub = 	new org.pahospital.www.labcallbackservice.LabCallbackServiceStub(defaultUrl);
		org.pahospital.www.labcallbackservice.LabCallbackServiceStub.LabReport labReport = 
				new org.pahospital.www.labcallbackservice.LabCallbackServiceStub.LabReport();
		org.pahospital.www.labcallbackservice.LabCallbackServiceStub.LabValues_type0[] labValues = 
				new org.pahospital.www.labcallbackservice.LabCallbackServiceStub.LabValues_type0[labOrder.getLabParameter().length];
		
		//logging purposes
		System.out.println("---------there is a request-------------");
		
		//1. add labOrder into 'database', assign labOrderId
		labOrderCounter++;
		response.setLabOrderId(labOrderCounter.toString());

		//Simulate the lab:
		//- assign all lab parameters requested by client with some random number
		
		for(int i = 0; i < labOrder.getLabParameter().length; i++) {
			labValues[i] = new org.pahospital.www.labcallbackservice.LabCallbackServiceStub.LabValues_type0();
			labValues[i].setLabParameter(labOrder.getLabParameter()[i]);
			labValues[i].setLabValue(new BigDecimal(i + 1024));
			System.out.println("labValues[" + i +"] parameter: " + labValues[i].getLabParameter()
					+ "  values: " + labValues[i].getLabValue());
		}

		
		//arrange labReport to be sent back to the client
		labReport.setLabOrderID(response.getLabOrderId());
		labReport.setLabValues(labValues);
		labReport.setPatientID(labOrder.getPatientID());
		labReport.setSampleID(labOrder.getSampleID());

		//org.pahospital.www.labcallbackservice.LabCallbackClient.execute(labReport);
		stub.sendLabReport(labReport);
		
		
		System.out.println("---END---");
		//4. return the labId
		return response;
	}
}
    