package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }
        
        Long inHour = ticket.getInTime().getTime();
        Long outHour = ticket.getOutTime().getTime();

        long duration = (outHour - inHour);
        int timeStampConverted = 3600000; // (1000 * 60 * 60);
        float durationConverted = (float) duration / timeStampConverted; // Convert milisecond to Hour



        if (durationConverted <= 0.5) {
            ticket.setPrice(0);
        } else {
            switch (ticket.getParkingSpot().getParkingType()){
                case CAR: 
                    ticket.setPrice(durationConverted * Fare.CAR_RATE_PER_HOUR);
                    break;
                case BIKE: 
                    ticket.setPrice(durationConverted * Fare.BIKE_RATE_PER_HOUR);
                    break;
            }
        }
    }

    public void applyDiscountTicket(Ticket ticket, double discount) {
        ticket.setPrice(ticket.getPrice() * (1 - discount));
    }

}