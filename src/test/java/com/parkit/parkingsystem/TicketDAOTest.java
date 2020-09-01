package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TicketDAOTest {

    private Ticket ticket;
    
    @BeforeEach
    private void setUpPerTest() {
        ticket = new Ticket();
    }
    
    @Test
    public void getTicketTest(){
        String vehicleNumber = "ABCDEF";

        TicketDAO ticketDAO = new TicketDAO();

        Ticket response = ticketDAO.getTicket(vehicleNumber);
        System.out.println("ticket  " + response);

    }
    
    @Test
    public void saveTicketEmptyTest(){

    	TicketDAO ticketDAO = new TicketDAO();
    	Date inTime = new Date();
        inTime.setTime( System.currentTimeMillis() - (  60 * 60 * 1000) );
        Date outTime = new Date();
        outTime.setTime( System.currentTimeMillis() - (  30 * 60 * 1000) );
        ticket.setOutTime(outTime);
        ticket.setInTime(inTime);
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        ticket.setParkingSpot(parkingSpot);
        
        Boolean response = ticketDAO.saveTicket(ticket);
        assertEquals(response, false);
        System.out.println("ticket  " + response);

    } 
    
    @Test
    public void updateTicketEmptyTest(){

        TicketDAO ticketDAO = new TicketDAO();
        Date outTime = new Date();
        ticket.setOutTime(outTime);
        Boolean response = ticketDAO.updateTicket(ticket);
        assertEquals(response, true);
        System.out.println("ticket  " + response);

    }

}
