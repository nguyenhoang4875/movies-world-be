package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dao.User;
import com.movies.entity.dto.ReservationDTO;
import com.movies.service.ReservationService;
import com.movies.service.SeatService;
import com.movies.service.ShowTimeFilmService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
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
                                         @RequestBody @Valid ReservationDTO reservationDTO) throws ParseException {
        User user = userService.findOneByUsername(principal.getName());
        ShowTimeFilm showTimeFilm = showTimeFilmService.getOneByFilmAndTime(reservationDTO.getFilmId(), reservationDTO.getShowTime());

        //Add data to reservation table
        Reservation reservation = new Reservation();
        reservation.setStatus(false);
        reservation.setTime(LocalDateTime.now());
        reservation.setUser(user);
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
}
