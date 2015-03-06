package localhost.carrental;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import org.apache.axis2.AxisFault;

public class CarRentalServiceGUI extends JFrame {
	
	//panels
	private Panel p1; //panel for 'get availability'
	private Panel p2; //panel for 'car rental'
	

	//elements in panel 'get availability'
	private JLabel panel1TitleLabel = null;
	private JLabel pickupDateLabel = null;
	private JLabel pickupLocationLabel = null;
	private JLabel dropoffDateLabel = null;
	private JLabel dropoffLocationLabel = null;
	private JLabel panel1ResultLabel = null;
	
	private JTextField pickupDateField = null;
	private JTextField pickupLocationField = null;
	private JTextField dropoffDateField = null;
	private JTextField dropoffLocationField = null;
	private JTextField panel1ResultField = null;
	
	private JButton panel1Button = null;
	
	//elements in panel 'rent car'
	private JLabel panel2TitleLabel = null;
	private JLabel carCodeLabel = null;
	private JLabel pickupDate2Label = null;
	private JLabel pickupLocation2Label = null;
	private JLabel dropoffDate2Label = null;
	private JLabel dropoffLocation2Label = null;
	private JLabel panel2ResultLabel = null;
	
	private JTextField carCodeField = null;
	private JTextField pickupDate2Field = null;
	private JTextField pickupLocation2Field = null;
	private JTextField dropoffDate2Field = null;
	private JTextField dropoffLocation2Field = null;
	private JTextField panel2ResultField = null;
	
	private JButton panel2Button = null;
	
	private final String defaultUrl = "http://localhost:8080/CarRental/services/CarRentalService";
	
	public static void main(String[] args) throws AxisFault {
		new CarRentalServiceGUI();
	}

	public CarRentalServiceGUI() throws AxisFault {
		super("Car Rental Client");
		wsInit(defaultUrl);
		initialize();
	}

	public CarRentalServiceStub stub;
	
	private void wsInit(String endpoint) throws AxisFault {
		stub = new CarRentalServiceStub(endpoint);
	}
	private void initialize() {
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		
		Panel p1 = new Panel(); //panel for 'get availability'
		Panel p2 = new Panel(); //panel for 'car rental'
		Panel p3 = new Panel();	//panel for panel1 label
		Panel p4 = new Panel(); //panel for panel2 label
		p1.setLayout(new GridLayout(7,2,10,10));
		p2.setLayout(new GridLayout(7,2,10,10));
		p3.setLayout(new GridLayout(1,1,0,10));
		p4.setLayout(new GridLayout(1,1,0,10));

		//panel1 initialization
		panel1TitleLabel = new JLabel();
		panel1TitleLabel.setText("Get available car");
		pickupDateLabel = new JLabel();
		pickupDateLabel.setText("Pick up date");
		pickupLocationLabel = new JLabel();
		pickupLocationLabel.setText("Pick up location");
		dropoffDateLabel = new JLabel();
		dropoffDateLabel.setText("Drop off date");
		dropoffLocationLabel = new JLabel();
		dropoffLocationLabel.setText("Drop off location");
		panel1ResultLabel = new JLabel();
		panel1ResultLabel.setText("Available cars");
		
		pickupDateField = new JTextField(20);
		pickupLocationField = new JTextField(20);
		dropoffDateField = new JTextField(20);
		dropoffLocationField = new JTextField(20);
		panel1ResultField = new JTextField(20);
		
		panel1Button = new JButton();
		panel1Button.setText("Get available cars");
		
		JLabel dummy1Label = new JLabel();
		dummy1Label.setText("");
		
		//panel2 initialization
		panel2TitleLabel = new JLabel();
		panel2TitleLabel.setText("Rent a car");
		carCodeLabel = new JLabel();
		carCodeLabel.setText("Car code");
		pickupDate2Label = new JLabel();
		pickupDate2Label.setText("Pick up date");
		pickupLocation2Label = new JLabel();
		pickupLocation2Label.setText("Pick up location");
		dropoffDate2Label = new JLabel();
		dropoffDate2Label.setText("Drop off date");
		dropoffLocation2Label = new JLabel();
		dropoffLocation2Label.setText("Drop off location");
		panel2ResultLabel = new JLabel();
		panel2ResultLabel.setText("Available cars");
		
		carCodeField = new JTextField(20);
		pickupDate2Field = new JTextField(20);
		pickupLocation2Field = new JTextField(20);
		dropoffDate2Field = new JTextField(20);
		dropoffLocation2Field = new JTextField(20);
		panel2ResultField = new JTextField(20);
		
		panel2Button = new JButton();
		panel2Button.setText("Rent a car");
		
		JLabel dummy2Label = new JLabel();
		dummy1Label.setText("");
		
		panel1Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
					
					stub = new CarRentalServiceStub(defaultUrl);
					CarRentalServiceStub.GetAvailability request = new CarRentalServiceStub.GetAvailability();
					CarRentalServiceStub.GetAvailabilityResponse response;
					
					request.setPickupDate(formatter.parse(pickupDateField.getText()));
					request.setPickupLocation(pickupLocationField.getText());
					request.setDropOffDate(formatter.parse(dropoffDateField.getText()));
					request.setDropOffLocation(dropoffLocationField.getText());
					
					response = stub.getAvailability(request);
					panel1ResultField.setText(response.getCarCode());
				} catch(AxisFault e1) {
					e1.printStackTrace();
				} catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}				
			}
		});
		
		panel2Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
					
					stub = new CarRentalServiceStub(defaultUrl);
					CarRentalServiceStub.RentCar request = new CarRentalServiceStub.RentCar();
					CarRentalServiceStub.RentCarResponse response;
					
					request.setCarCode(carCodeField.getText());
					request.setPickupDate(formatter.parse(pickupDate2Field.getText()));
					request.setPickupLocation(pickupLocation2Field.getText());
					request.setDropOffDate(formatter.parse(dropoffDate2Field.getText()));
					request.setDropOffLocation(dropoffLocation2Field.getText());
					
					response = stub.rentCar(request);
					panel2ResultField.setText(response.getConfirmation());
				} catch(AxisFault e1) {
					e1.printStackTrace();
				} catch(RemoteException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}				
			}
		});
		
		p3.add(panel1TitleLabel);
		
		p1.add(pickupDateLabel);
		p1.add(pickupDateField);
		p1.add(pickupLocationLabel);
		p1.add(pickupLocationField);
		p1.add(dropoffDateLabel);
		p1.add(dropoffDateField);
		p1.add(dropoffLocationLabel);
		p1.add(dropoffLocationField);
		p1.add(panel1Button);
		p1.add(dummy1Label);
		p1.add(panel1ResultLabel);
		p1.add(panel1ResultField);
		
		p4.add(panel2TitleLabel);
		
		p2.add(carCodeLabel);
		p2.add(carCodeField);
		p2.add(pickupDate2Label);
		p2.add(pickupDate2Field);
		p2.add(pickupLocation2Label);
		p2.add(pickupLocation2Field);
		p2.add(dropoffDate2Label);
		p2.add(dropoffDate2Field);
		p2.add(dropoffLocation2Label);
		p2.add(dropoffLocation2Field);
		p2.add(panel2Button);
		p2.add(dummy2Label);
		p2.add(panel2ResultLabel);
		p2.add(panel2ResultField);
				
		c.add(p3);
		c.add(p1);
		c.add(p4);
		c.add(p2);
		
		this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
            	System.exit(0); 				
            	}
	    });
		
		this.setSize(500, 650);
		this.setVisible(true);
	}
}
