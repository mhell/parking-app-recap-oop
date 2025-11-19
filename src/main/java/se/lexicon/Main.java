package se.lexicon;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.ParkingSpot;
import se.lexicon.service.CustomerService;
import se.lexicon.service.ParkingService;
import se.lexicon.service.ReservationService;
import se.lexicon.service.VacateService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDaoImpl();
//        ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();
//        ReservationDao reservationDao = new ReservationDaoImpl();
//
//        parkingSpotDao.create(new ParkingSpot(1,101,false));
//        parkingSpotDao.create(new ParkingSpot(2,102,false));
//        parkingSpotDao.create(new ParkingSpot(3,103,false));
//
//        CustomerService costumerService = new CustomerService(customerDao);
//        ParkingService parkingService = new ParkingService(parkingSpotDao);
//        ReservationService reservationService = new ReservationService(reservationDao, parkingSpotDao);
//        VacateService vacateService = new VacateService(reservationDao);
//
//        new ParkingApp(costumerService, parkingService, reservationService, vacateService).start();

        SwingUtilities.invokeLater(ParkingAppUI::new);
    }
}
