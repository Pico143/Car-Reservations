package com.seweryn91.CarReservations.controller;

import com.seweryn91.CarReservations.model.Reservation;
import com.seweryn91.CarReservations.repository.ReservationRepository;
import com.seweryn91.CarReservations.utils.JSONFormatter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationsController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private JSONFormatter jsonFormatter;

    @RequestMapping (value = "/reservations", method = RequestMethod.GET)
    private String getAllReservations() {
        StringBuilder sb = new StringBuilder();
        try {
            List<Reservation> reservations = new ArrayList<>();
            reservationRepository.findAll().forEach(reservations::add);
            sb.append(jsonFormatter.serializeCollectionReservations(reservations));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // sama rezerwacja powinna mieć getPrice (i pole z ceną) - nie ma , więc zakomentowuję
//    public Double getPrice(long reservationId) {
//        return reservationDAO.getPrice(reservationId);
//    }
}
