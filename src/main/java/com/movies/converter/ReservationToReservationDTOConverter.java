package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Reservation;
import com.movies.entity.dao.Seat;
import com.movies.entity.dto.ReservationDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationToReservationDTOConverter extends Converter<Reservation, ReservationDTO> {
    @Override
    public ReservationDTO convert(Reservation source) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(source.getId());
        reservationDTO.setTimeCreated(source.getTime());
        reservationDTO.setStatus(source.getStatus());
        reservationDTO.setUserId(source.getUser().getId());
        reservationDTO.setFilmId(source.getShowTimeFilm().getFilm().getId());
        reservationDTO.setFilmName(source.getShowTimeFilm().getFilm().getName());
        reservationDTO.setFilmPoster(source.getShowTimeFilm().getFilm().getPoster());
        reservationDTO.setShowTime(source.getShowTimeFilm().getTime());
        reservationDTO.setRoom(source.getShowTimeFilm().getRoom().getName());

        if (source.getSeats().size() > 0) {
            List<String> seats = new ArrayList<>();
            for (Seat seat : source.getSeats()) {
                seats.add(seat.getName());
            }
            reservationDTO.setSeat(seats);
        }

        return reservationDTO;
    }
}
