package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.User;
import com.movies.entity.dto.ReservationDTO;
import com.movies.service.ReservationService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<Reservation, ReservationDTO> reservationReservationDTOConverter;

    @GetMapping("/history")
    public List<ReservationDTO> getReservationHistory(Principal principal) {
        User user = userService.findOneByUsername(principal.getName());
        List<Reservation> reservations = reservationService.findReservationByUser(user);
        return reservationReservationDTOConverter.convert(reservations);
    }
}
