package com.movies.converter;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Seat;
import com.movies.entity.dto.SeatDTO;
import org.springframework.stereotype.Component;

@Component
public class SeatToSeatDTOConverter extends Converter<Seat, SeatDTO> {
    @Override
    public SeatDTO convert(Seat source) {
        SeatDTO seatDTO = new SeatDTO();
        seatDTO.setId(source.getId());
        seatDTO.setName(source.getName());
        seatDTO.setStatus(source.getStatus());
        seatDTO.setShowTimeFilmId(source.getShowTimeFilm().getId());
        seatDTO.setReservationId(source.getReservation().getId());
        return seatDTO;
    }
}
