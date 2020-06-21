package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dao.User;
import com.movies.entity.dto.ReservationDTO;
import com.movies.exception.BadRequestException;
import com.movies.exception.NotFoundException;
import com.movies.service.ReservationService;
import com.movies.service.SeatService;
import com.movies.service.ShowTimeFilmService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @Autowired
    private Converter<Reservation, ReservationDTO> reservationReservationDTOConverter;

    @GetMapping("/history")
    public List<ReservationDTO> getReservationHistory(Principal principal) {
        User user = userService.findOneByUsername(principal.getName());
        List<Reservation> reservations = reservationService.findReservationByUser(user);
        return reservationReservationDTOConverter.convert(reservations);
    }

    @PostMapping
    public ReservationDTO addReservation(Principal principal,
                                         @RequestBody @Valid ReservationDTO reservationDTO) {
        User user = userService.findOneByUsername(principal.getName());
        ShowTimeFilm showTimeFilm = showTimeFilmService.getOneByFilmAndTime(reservationDTO.getFilmId(), reservationDTO.getShowTime());

        if (showTimeFilm == null) {
            throw new BadRequestException("NOT FOUND");
        }
        //Add data to reservation table
        Reservation reservation = new Reservation();
        reservation.setStatus(0);
        reservation.setTime(LocalDateTime.now());
        reservation.setUser(user);
        reservation.setShowTimeFilm(showTimeFilm);
        reservationService.save(reservation);
        List<Seat> seats = new ArrayList<>();
        //update Seat data
        for (String nameSeat : reservationDTO.getSeat()) {
            Seat seat = seatService.getOneByNameAndShowTimeFilm(nameSeat, showTimeFilm);
            seat.setStatus(1);
            seat.setReservation(reservation);
            seats.add(seat);
            seatService.save(seat);
        }

        reservation.setSeats(seats);
        return reservationReservationDTOConverter.convert(reservation);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<ReservationDTO> cancelReservation(Principal principal,
                                                            @PathVariable("id") int reservationId) {
        Reservation reservation = reservationService.getOneById(reservationId).get();
        if (!reservation.getUser().getUsername().equalsIgnoreCase(principal.getName())) {
            throw new NotFoundException("THIS IS NOT YOUR RESERVATION");
        }

        LocalDateTime timeShow = reservation.getSeats().get(0).getShowTimeFilm().getTime();
        if (!checkIfNowBeforeShowing(timeShow)) {
            throw new BadRequestException("You can only cancel after 30 minutes before show time!");
        } else {
            //change status of reservation to 2-canceled
            reservation.setStatus(2);
            reservationService.save(reservation);
            for (Seat seat : reservation.getSeats()) {
                Seat updatedSeat = seatService.getById(seat.getId()).get();
                updatedSeat.setStatus(0);
                updatedSeat.setReservation(null);
                seatService.save(updatedSeat);
            }
        }
        return new ResponseEntity<>(reservationReservationDTOConverter.convert(reservation), HttpStatus.OK);
    }

    private boolean checkIfNowBeforeShowing(LocalDateTime timeShow) {
        return timeShow.minusMinutes(30).isBefore(LocalDateTime.now()) ? false : true;
    }
}
