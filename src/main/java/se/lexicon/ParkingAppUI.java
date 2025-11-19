package se.lexicon;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.service.CustomerService;
import se.lexicon.service.ParkingService;
import se.lexicon.service.ReservationService;
import se.lexicon.service.VacateService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ParkingAppUI {
    private JFrame jFrame;
    private CustomerService customerService;
    ParkingService parkingService;
    ReservationService reservationService;
    VacateService vacateService;

    public ParkingAppUI(CustomerService costumerService, ParkingService parkingService,
                        ReservationService reservationService, VacateService vacateService) {
        this.customerService=costumerService;
        this.parkingService=parkingService;
        this.reservationService=reservationService;
        this.vacateService=vacateService;
        initializeUI();
    }

    private void initializeUI(){
        jFrame=new JFrame("Parking App");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);
        jFrame.setLayout(new GridLayout(5,2,10,10));

        //Buttons
        JButton registerCustomerBtn=new JButton("1. Register Customer");
        JButton displayParkingSpotsBtn=new JButton("2. Display Parking Spots");
        JButton reserveSpotBtn=new JButton("3. Reserve a Parking Spot");
        JButton vacateParkingSpotBtn=new JButton("4. Vacate Parking Spot");
        JButton exitBtn=new JButton("5. Exit");

        //Add buttons to frame
        jFrame.add(registerCustomerBtn);
        jFrame.add(displayParkingSpotsBtn);
        jFrame.add(reserveSpotBtn);
        jFrame.add(vacateParkingSpotBtn);
        jFrame.add(exitBtn);

        //Action listeners
        registerCustomerBtn.addActionListener(e->registerCustomer());
        displayParkingSpotsBtn.addActionListener(e->displayParkingSpots());
        reserveSpotBtn.addActionListener(e->reserveParkingSpot());
        vacateParkingSpotBtn.addActionListener(e->vacateParkingSpot());
        exitBtn.addActionListener(e->{ JOptionPane.showMessageDialog(jFrame,"Exiting... Goodbye!");
        jFrame.dispose();
        });

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }
    private void registerCustomer() {
        JTextField nameField=new JTextField(10);
        JTextField phoneField=new JTextField(10);
        JTextField plateField=new JTextField(10);

        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(5));
        panel.add(new JLabel("Phone Number:"));
        panel.add(phoneField);
        panel.add(Box.createVerticalStrut(5));
        panel.add(new JLabel("Plate Number:"));
        panel.add(plateField);

        int result=JOptionPane.showConfirmDialog(jFrame,panel,"Register Customer",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(result==JOptionPane.OK_OPTION){
            String name = nameField.getText();
            String phoneNumber = phoneField.getText();
            String plateNumber = plateField.getText();

            if(name.isBlank() || phoneNumber.isBlank() || plateNumber.isBlank()){
                JOptionPane.showMessageDialog(jFrame,"All are required fields!");
                return;
            }
            Customer customer=customerService.registerCustomer(name, phoneNumber, plateNumber);
            JOptionPane.showMessageDialog(jFrame,"The Customer has been registered.The Customer Id : "+customer.getId());
        }
    }

    private void displayParkingSpots() {
        String[] columns={"Spot Number","Area Code","Occupied"};
        //Creating a table
        DefaultTableModel model=new DefaultTableModel(columns,0);
        List<ParkingSpot> parkingSpots = parkingService.getAllParkingSpots();
        for (ParkingSpot parkingSpot : parkingSpots) {
            model.addRow(new Object[]{
                    parkingSpot.getSpotNumber(), parkingSpot.getAreaCode(), parkingSpot.isOccupied()
            });
        }
           JTable table=new JTable(model);
           JScrollPane scrollPane=new JScrollPane(table);
           JOptionPane.showMessageDialog(jFrame,scrollPane,"Parking Spot List",JOptionPane.PLAIN_MESSAGE);

    }

    private void reserveParkingSpot() {
            JTextField customerField=new JTextField(10);
            JTextField durationField=new JTextField(5);
            JTextField areaCodeField=new JTextField(5);
            JTextField spotNumberField=new JTextField(5);

            JPanel panel=new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
            panel.add(new JLabel("Customer Id:"));
            panel.add(customerField);
            panel.add(Box.createVerticalStrut(5));
            panel.add(new JLabel("Duration (hours)"));
            panel.add(durationField);
            panel.add(Box.createVerticalStrut(5));
            panel.add(new Label("Area code:"));
            panel.add(areaCodeField);
            panel.add(Box.createVerticalStrut(5));
            panel.add(new JLabel("Spot Number:"));
            panel.add(spotNumberField);

            int result=JOptionPane.showConfirmDialog(jFrame,panel,"Reserve Parking Spot",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(result==JOptionPane.OK_OPTION){
                try {
                    Customer customer = customerService.getCustomer(Integer.parseInt(customerField.getText()));
                    int duration = Integer.parseInt(durationField.getText());
                    int areaCode = Integer.parseInt(areaCodeField.getText());
                    int spotNumber = Integer.parseInt(spotNumberField.getText());
                    reservationService.reserveSpot(customer.getId(), duration, areaCode, spotNumber);
                    JOptionPane.showMessageDialog(jFrame,"The Spot has been reserved successfully");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(jFrame,ex.getMessage());
                }
            }
    }

    private void vacateParkingSpot() {
        JTextField areaCodeField=new JTextField();
        JTextField spotNumberField=new JTextField();

        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(new JLabel("Area Code:"));
        panel.add(areaCodeField);
        panel.add(Box.createVerticalStrut(5));
        panel.add(new JLabel("Spot Number:"));
        panel.add(spotNumberField);
        int result=JOptionPane.showConfirmDialog(jFrame,panel,"Vacate Parking Spot",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(result==JOptionPane.OK_OPTION){
            try {
                int areaCode = Integer.parseInt(areaCodeField.getText());
                int spotNumber = Integer.parseInt(spotNumberField.getText());
                vacateService.vacateSpot(areaCode, spotNumber);
                JOptionPane.showMessageDialog(jFrame,"Successfully vacated parking spot " + spotNumber + " with area code: " + areaCode);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(jFrame,e.getMessage());
            }
        }
    }
}
