package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class ParkingSpotDAOTest {
	
	private ParkingSpot PS;
	
    @Test
    public void isReccuringFalse() {
		ParkingSpotDAO ParkingSpot = new ParkingSpotDAO();
        Boolean isReccured = ParkingSpot.isReccuringUser("test");
        assertEquals(isReccured, false);
    }
    
    @Test
    public void getNextSpotTest() {
		ParkingSpotDAO ParkingSpot = new ParkingSpotDAO();
        int spotted = ParkingSpot.getNextAvailableSlot(ParkingType.CAR);
        assertEquals(spotted, 2);
    }

    @Test
    public void updateParkingEmptyTest() {
		ParkingSpotDAO ParkingSpotDAO = new ParkingSpotDAO();
        Boolean isUpdated = ParkingSpotDAO.updateParking(PS);
        assertEquals(isUpdated, false);
    }

}