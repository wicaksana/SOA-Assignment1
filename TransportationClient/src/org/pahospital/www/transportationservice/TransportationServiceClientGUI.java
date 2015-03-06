package org.pahospital.www.transportationservice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import org.apache.axis2.AxisFault;

public class TransportationServiceClientGUI extends JFrame {

	private Panel p1; //panel 'patient order'
	private Panel p2; //panel 'sample order'

	//elements in 'patient order'
	private JLabel panel1TitleLabel = null;
	private JLabel patientIdLabel = null;
	private JLabel requestingUnitLabel = null;
	private JLabel destinationUnitLabel = null;
	private JLabel transportationDateLabel = null;
	
	private JTextField patientIdField = null;
	private JTextField requestingUnitField = null;
	private JTextField destinationUnitField = null;
	private JTextField transportationDateField = null;
	
	private JButton patientOrderButton = null;
	
	//elements in 'sample order'
	private JLabel panel2TitleLabel = null;
	private JLabel patientId2Label = null;
	private JLabel requestingUnit2Label = null;
	private JLabel sampleIdLabel = null;
	
	private JTextField patientId2Field = null;
	private JTextField requestingUnit2Field = null;
	private JTextField sampleIdField = null;
	
	private JButton sampleOrderButton = null;
	
	private final String defaultUrl = "http://localhost:8080/Transportation/services/TransportationService";
	
	public static void main(String[] args) throws AxisFault {
		new TransportationServiceClientGUI();	
	}
	
	public TransportationServiceClientGUI() throws AxisFault {
		super("Hospital Transportation Client");
		wsInit(defaultUrl);
		initialize();
	}
	
	public TransportationServiceStub stub;
	
	private void wsInit(String endPoint) throws AxisFault {
		stub = new TransportationServiceStub(endPoint);
	}

	
	private void initialize() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		
		Panel p1 = new Panel(); //panel for 'patient order'
		Panel p2 = new Panel(); //panel for 'sample order'
		Panel p3 = new Panel();	//panel for panel1 label
		Panel p4 = new Panel(); //panel for panel2 label
		p1.setLayout(new GridLayout(6,2,10,10));
		p2.setLayout(new GridLayout(5,2,10,10));
		p3.setLayout(new GridLayout(1,1,0,10));
		p4.setLayout(new GridLayout(1,1,0,10));
		
		//panel1 initialization
		panel1TitleLabel = new JLabel();
		panel1TitleLabel.setText("Patient Order");
		patientIdLabel = new JLabel();
		patientIdLabel.setText("Patient ID");
		requestingUnitLabel = new JLabel();
		requestingUnitLabel.setText("Requesting unit");
		destinationUnitLabel = new JLabel();
		destinationUnitLabel.setText("Destination unit");
		transportationDateLabel = new JLabel();
		transportationDateLabel.setText("Transportation date");
				
		patientIdField = new JTextField(20);
		requestingUnitField = new JTextField(20);
		destinationUnitField = new JTextField(20);
		transportationDateField = new JTextField(20);

		patientOrderButton = new JButton();
		patientOrderButton.setText("Patient order");
				
		JLabel dummy1Label = new JLabel();
		dummy1Label.setText("");
		
		//panel2 initialization
		panel2TitleLabel = new JLabel();
		panel2TitleLabel.setText("Sample Order");
		patientId2Label = new JLabel();
		patientId2Label.setText("Patient ID");
		requestingUnit2Label = new JLabel();
		requestingUnit2Label.setText("Requesting unit");
		sampleIdLabel = new JLabel();
		sampleIdLabel.setText("Sample ID");
				
		patientId2Field = new JTextField(20);
		requestingUnit2Field = new JTextField(20);
		sampleIdField = new JTextField(20);

		sampleOrderButton = new JButton();
		sampleOrderButton.setText("Sample order");
				
		JLabel dummy2Label = new JLabel();
		dummy2Label.setText("");

		//patientOrder action listener
		patientOrderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Calendar cal = Calendar.getInstance();
					DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
					stub = new TransportationServiceStub(defaultUrl);
					TransportationServiceStub.PatientOrder patientOrderRequest = 
							new TransportationServiceStub.PatientOrder();
					
					patientOrderRequest.setPatientID(patientIdField.getText());
					patientOrderRequest.setRequestingUnit(requestingUnitField.getText());
					patientOrderRequest.setDestinationUnit(destinationUnitField.getText());
					cal.setTime(formatter.parse(transportationDateField.getText())); // convert date input into Calendar
					patientOrderRequest.setTransportationDate(cal);
					
					//send to the server
					stub.orderPatientTransport(patientOrderRequest);
					
				} catch(AxisFault e1) {
					e1.printStackTrace();
				} catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					System.out.println("--incorrect input for transportation date--");
					e1.printStackTrace();
				}
			}
		});

		//sampleOrder action listener
		sampleOrderButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stub = new TransportationServiceStub(defaultUrl);
					TransportationServiceStub.SampleOrder sampleOrderRequest = 
							new TransportationServiceStub.SampleOrder();
					
					sampleOrderRequest.setPatientID(patientId2Field.getText());
					sampleOrderRequest.setRequestingUnit(requestingUnit2Field.getText());
					sampleOrderRequest.setSampleID(sampleIdField.getText());

					//send to the server
					stub.orderSampleTransport(sampleOrderRequest);;
					
				} catch(AxisFault e1) {
					e1.printStackTrace();
				} catch(RemoteException e1) {
					e1.printStackTrace();
				} 
			}
		});

		p3.add(panel1TitleLabel);
		
		p1.add(patientIdLabel);
		p1.add(patientIdField);
		p1.add(requestingUnitLabel);
		p1.add(requestingUnitField);
		p1.add(destinationUnitLabel);
		p1.add(destinationUnitField);
		p1.add(transportationDateLabel);
		p1.add(transportationDateField);
		p1.add(patientOrderButton);
		
		p4.add(panel2TitleLabel);
	
		p2.add(patientId2Label);
		p2.add(patientId2Field);
		p2.add(requestingUnit2Label);
		p2.add(requestingUnit2Field);
		p2.add(sampleIdLabel);
		p2.add(sampleIdField);
		p2.add(sampleOrderButton);
		
		c.add(p3);
		c.add(p1);
		c.add(p2);
		c.add(p2);
		
		this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
            	System.exit(0); 				
           	}
	    });
		
		this.setSize(500, 450);
		this.setVisible(true);
		
	}
}
