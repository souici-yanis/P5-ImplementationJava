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

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    private static ParkingService parkingService;

    @Mock
    private static InputReaderUtil inputReaderUtil;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO;

    @BeforeEach
    private void setUpPerTest() {
        try {
            lenient().when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");

            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
            Ticket ticket = new Ticket();
            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
            ticket.setParkingSpot(parkingSpot);
            ticket.setVehicleRegNumber("ABCDEF");
            lenient().when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
            lenient().when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);

            lenient().when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);
    
            parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
    }

    @Test
    public void processExitingVehicleTest(){
    	parkingService.processExitingVehicle();
    	Ticket expected = ticketDAO.getTicket("ABCDEF");
    	assertNotNull(expected);
    	assertNotEquals(expected.getPrice(), 0);
    	assertNotNull(expected.getOutTime());
        verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
    }

    @Test
    public void getParkingNumber(){
    	ParkingSpot response = parkingService.getNextParkingNumberIfAvailable();
        ParkingSpot parkingSpotExpected = new ParkingSpot(0,ParkingType.CAR, true);
        
        assertEquals(response, parkingSpotExpected);
    }
    

    @Test
    public void processIncomingVehicleTest(){
    	parkingService.processIncomingVehicle();
    	Ticket expected = ticketDAO.getTicket("ABCDEF");
    	assertNotNull(expected);
    	assertEquals(expected.getPrice(), 0);
    	assertEquals(expected.getOutTime(), null);
    }

    @Test
    public void getNextParkingNumberIfAvailableTest(){
        ParkingSpot PS = parkingService.getNextParkingNumberIfAvailable();
        System.out.println(PS);
        //Check first place available exist and equal 0
    	assertEquals(PS.getId(), 0);
    }
    
    
}
