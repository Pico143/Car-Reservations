package com.seweryn91.CarReservations.dao;

import com.seweryn91.CarReservations.model.Reservation;
import com.seweryn91.CarReservations.repository.ReservationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReservationDAOTest {

    @Autowired
    ReservationRepository reservationRepository;

    private long testReservationId;

    @BeforeAll
    void before() {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(3);
        reservation.setCarId(3);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse("2030-03-03");
            endDate = simpleDateFormat.parse("2030-03-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);

        reservationRepository.save(reservation);
        testReservationId = reservation.getReservationId();
    }

    @AfterAll
    void after() {
        reservationRepository.deleteById(testReservationId);
    }

    @Test
    @DisplayName("Test get reservation from DB")
    void testGetReservation() {
        Reservation reservation = reservationRepository.findById(1L).get();
        Assertions.assertEquals(2, reservation.getCustomerId());
        Assertions.assertEquals(9, reservation.getCarId());
    }

    @Test
    @DisplayName("Test save reservation in DB")
    void testSaveReservation() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        int prevSize = reservations.size();
        Reservation reservation = new Reservation();
        reservation.setCustomerId(1);
        reservation.setCarId(1);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse("1991-01-01");
            endDate = simpleDateFormat.parse("1991-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservationRepository.save(reservation);
        long testReservationId2 = reservation.getReservationId();
        List<Reservation> reservationsAfterInsert = new ArrayList<>();
        reservationRepository.findAll().forEach(reservationsAfterInsert::add);
        int newSize = reservationsAfterInsert.size();
        Assertions.assertEquals(prevSize + 1, newSize);
        reservationRepository.deleteById(testReservationId2);
    }

    @Test
    @DisplayName("Test delete reservation from DB")
    void testDeleteReservation() {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(1);
        reservation.setCarId(1);
        String pattern ="yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = simpleDateFormat.parse("1991-01-01");
            endDate = simpleDateFormat.parse("1991-02-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservationRepository.save(reservation);
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        int prevSize = reservations.size();
        reservationRepository.deleteById(reservation.getReservationId());
        List<Reservation> reservationsAfterDelete = new ArrayList<>();
        reservationRepository.findAll().forEach(reservationsAfterDelete::add);;
        int newSize = reservationsAfterDelete.size();
        Assertions.assertEquals(prevSize - 1, newSize);
    }

//    @Test
//    @DisplayName("Test update car")
//    void testUpdateReservationCar() {
//        long carId = 21;
//        reservationRepository.updateReservationCar(testReservationId, carId);
//        Reservation afterUpdate = reservationRepository.getReservation(testReservationId);
//        long newId = afterUpdate.getCarId();
//        Assertions.assertEquals(carId, newId);
//    }

//    @Test
//    @DisplayName("Test update customer")
//    void testUpdateReservationCustomer() {
//        long customerId = 3;
//        reservationRepository.updateReservationCustomer(testReservationId, customerId);
//        Reservation afterUpdate = reservationRepository.getReservation(testReservationId);
//        long newId = afterUpdate.getCustomerId();
//        Assertions.assertEquals(customerId, newId);
//    }

//    @Test
//    @DisplayName("Test update start date")
//    void testUpdateReservationStart() {
//        String pattern ="yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        Date startDate = null;
//        try {
//            startDate = simpleDateFormat.parse("2030-02-01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        reservationRepository.updateReservationStart(testReservationId, startDate);
//        Reservation afterUpdate = reservationRepository.getReservation(testReservationId);
//        String newDate = afterUpdate.getStartDate().toString();
//        Assertions.assertEquals("2030-02-01 00:00:00.0", newDate);
//    }

//    @Test
//    @DisplayName("Test update end date")
//    void testUpdateReservationEnd() {
//        String pattern ="yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        Date endDate = null;
//        try {
//            endDate = simpleDateFormat.parse("2030-05-01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        reservationRepository.updateReservationEnd(testReservationId, endDate);
//        Reservation afterUpdate = reservationRepository.getReservation(testReservationId);
//        String newDate = afterUpdate.getEndDate().toString();
//        Assertions.assertEquals("2030-05-01 00:00:00.0", newDate);
//    }

    @Test
    @DisplayName("Test get all reservations")
    void testGetAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        Assertions.assertEquals(4, reservations.size());
    }

//    @Test
//    @DisplayName("Test get price")
//    void testGetPrice() {
//        Double price = 126.00;
//        Double actual = reservationRepository.getPrice(1);
//        Assertions.assertEquals(price, actual);
//    }

}