package com.movies.controlller;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.DateTimeFilmDTO;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.entity.dto.ShowTimeFilmDto;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/showtimefilms")
public class ShowTimeFilmController {

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @GetMapping("/{filmId}")
    public List<ShowTimeFilm> getShowTimeFilmByFilmId(@PathVariable Integer filmId) {
        return showTimeFilmService.getShowTimeFilmByFilmId(filmId);
    }

    @PostMapping("/{filmId}")
    public ShowTimeFilmDto addShowTimeFilmBy(@PathVariable Integer filmId, @RequestBody ShowTimeFilmDto showTimeFilmDto) {
        return showTimeFilmService.addShowTimeFilm(filmId, showTimeFilmDto);
    }


    @GetMapping("/dateTime")
    public List<DateTimeFilmDTO> getDateTimeOfFilm(@RequestParam("filmId") Integer filmId) {
        List<Date> dateList = showTimeFilmService.getDateShow(filmId);
        List<DateTimeFilmDTO> dateTimeFilmDTOS = new ArrayList<>();
        for (Date date : dateList) {
            DateTimeFilmDTO dateTimeFilmDTO = new DateTimeFilmDTO(date.toLocalDate());
            List<LocalDateTime> timeList = showTimeFilmService.getTimeShow(filmId, date.toLocalDate());
            dateTimeFilmDTO.setTimeList(timeList);
            dateTimeFilmDTOS.add(dateTimeFilmDTO);
        }
        return dateTimeFilmDTOS;
    }

    @GetMapping("/filmInDate")
    public List<FilmTimeDTO> getShowTimeInDay(@RequestParam("date") String date) throws ParseException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        List<FilmTimeDTO> filmTimeDTOS = showTimeFilmService.getShowTimeInDay(dateTime);
        return filmTimeDTOS;
    }
}
