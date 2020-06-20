package com.movies.controlller;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.RowSeat;
import com.movies.entity.dto.SeatDTO;
import com.movies.service.SeatService;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @Autowired
    private Converter<Seat, SeatDTO> seatSeatDTOConverter;

    @GetMapping("/showTime")
    public List<RowSeat> getSeatsByShowTimeFilm(@RequestParam("filmId") Integer filmId,
                                             @RequestParam("dateTime") String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date dateTime = format.parse(time);
        ShowTimeFilm showTimeFilm = showTimeFilmService.getOneByFilmAndTime(filmId, dateTime);
        List<Seat> seats = seatService.getAllByShowTimeFilm(showTimeFilm);
        List<RowSeat> rowSeatList = convertToRow(seatSeatDTOConverter.convert(seats));
        return rowSeatList;
    }

    private List<RowSeat> convertToRow(List<SeatDTO> seats) {
        List<RowSeat> rowSeatList = new ArrayList<>();
        for (char row = 'A'; row <='Z' ; row ++) {
            RowSeat rowSeat = new RowSeat(row);
            List<SeatDTO> seatList = new ArrayList<>();
            for (SeatDTO seat : seats) {
                if (seat.getName().charAt(0) == row) {
                    seatList.add(seat);
                }
            }
            rowSeat.setSeats(seatList);
            if (seatList.size() > 0) rowSeatList.add(rowSeat);
        }
        return rowSeatList;
    }


}
