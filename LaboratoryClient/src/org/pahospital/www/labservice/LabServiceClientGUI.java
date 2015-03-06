package org.pahospital.www.labservice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.*;

import org.apache.axis2.AxisFault;
import org.pahospital.www.labservice.LabServiceStub.SampleMaterial_type1;

public class LabServiceClientGUI extends JFrame {
	
	private Panel p1;
	
	private JLabel patientIdLabel = null;
	private JLabel caseIdLabel = null;
	private JLabel sampleIdLabel = null;
	private JLabel sampleMaterialLabel = null;
	private JLabel labParameterLabel = null;
	private JLabel dummyLabel = null;
	
	private JTextField patientIdField = null;
	private JTextField caseIdField = null;
	private JTextField sampleIdField = null;
	private JTextField sampleMaterialField = null;
	private JTextField labParameterField = null;
	
	private JButton sendButton = null;

	private final String defaultUrl = "http://localhost:8080/axis2/Laboratory/services/LabService";
	
	public static void main(String[] args) throws AxisFault {
		new LabServiceClientGUI();
	}
	
	public LabServiceClientGUI() throws AxisFault {
		super("Laboratory Order");
		wsInit(defaultUrl);
		initialize();
	}

	public LabServiceStub stub;

	private void wsInit(String endPoint) throws AxisFault {
		stub = new LabServiceStub(endPoint);
	}

	private void initialize() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(7,2,10,10));
		
		patientIdLabel = new JLabel();
		patientIdLabel.setText("Patient ID");
		caseIdLabel = new JLabel();
		caseIdLabel.setText("Case ID");
		sampleIdLabel = new JLabel();
		sampleIdLabel.setText("Sample ID");
		sampleMaterialLabel = new JLabel();
		sampleMaterialLabel.setText("Sample material");
		labParameterLabel = new JLabel();
		labParameterLabel.setText("Lab parameters");
		dummyLabel = new JLabel();
		dummyLabel.setText("");
		
		patientIdField = new JTextField(20);
		caseIdField = new JTextField(20);
		sampleIdField = new JTextField(20);
		sampleMaterialField = new JTextField(20);
		labParameterField = new JTextField(20);
		
		sendButton = new JButton();
		sendButton.setText("Send");
		
		//button action handler
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stub = new LabServiceStub(defaultUrl);
					LabServiceStub.LabOrder labOrder = new LabServiceStub.LabOrder();
		
					labOrder.setPatientID(patientIdField.getText());
					labOrder.setCaseID(caseIdField.getText());
					labOrder.setSampleID(sampleIdField.getText());
					labOrder.setSampleMaterial(
							new SampleMaterial_type1(sampleMaterialField.getText().toUpperCase(), true));
					labOrder.setLabParameter(labParameterField.getText().split(","));
					//send to the WS
					stub.orderLabTest(labOrder);
					
				} catch(AxisFault e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		p1.add(patientIdLabel);
		p1.add(patientIdField);
		p1.add(caseIdLabel);
		p1.add(caseIdField);
		p1.add(sampleIdLabel);
		p1.add(sampleIdField);
		p1.add(sampleMaterialLabel);
		p1.add(sampleMaterialField);
		p1.add(labParameterLabel);
		p1.add(labParameterField);
		p1.add(sendButton);
		p1.add(dummyLabel);
		
		c.add(p1);
		
		this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
            	System.exit(0); 				
           	}
	    });
		
		this.setSize(500, 350);
		this.setVisible(true);
	}
}
