package org.pahospital.www.labcallbackservice;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

public class LabCallbackServiceClient {
	private final static String defaultUrl = "http://localhost:8080/axis2/LaboratoryClient/services/LabCallbackService";
	public static LabCallbackServiceStub stub;
	
	public static void main(String[] args) throws RemoteException {
		stub = new LabCallbackServiceStub(defaultUrl);
		LabCallbackServiceStub.LabReport labReport = new LabCallbackServiceStub.LabReport();
		LabCallbackServiceStub.LabValues_type0[] labValues = 
				new LabCallbackServiceStub.LabValues_type0[3];
		
		labValues[0] = new LabCallbackServiceStub.LabValues_type0();
		labValues[0].setLabParameter("cortisol");
		labValues[0].setLabValue(new BigDecimal(1024));
		
		labValues[1] = new LabCallbackServiceStub.LabValues_type0();
		labValues[1].setLabParameter("estradiol");
		labValues[1].setLabValue(new BigDecimal(1025));

		labValues[2] = new LabCallbackServiceStub.LabValues_type0();
		labValues[2].setLabParameter("progesterone");
		labValues[2].setLabValue(new BigDecimal(1026));

		labReport.setLabOrderID("1");
		labReport.setPatientID("123");
		labReport.setSampleID("678");
		labReport.setLabValues(labValues);

		stub.sendLabReport(labReport);
	}

}
