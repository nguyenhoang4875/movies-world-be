package com.movies.controlller;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.DateTimeFilmDTO;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.entity.dto.ShowTimeFilmDto;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/showtimefilms")
public class ShowTimeFilmController {

    @Autowired
    private ShowTimeFilmService showTimeFilmService;

    @GetMapping("films/{filmId}")
    public List<ShowTimeFilm> getShowTimeFilmByFilmId(@PathVariable Integer filmId) {
        return showTimeFilmService.getShowTimeFilmByFilmId(filmId);
    }

    @GetMapping("/{showTimeFilmId}")
    public ShowTimeFilm getShowTimeFilmById(@PathVariable Integer showTimeFilmId) {
        return showTimeFilmService.getShowTimeFilmById(showTimeFilmId);
    }

    @PostMapping("/{filmId}")
    public ShowTimeFilmDto addShowTimeFilm(@PathVariable Integer filmId, @RequestBody ShowTimeFilmDto showTimeFilmDto) {
        return showTimeFilmService.addShowTimeFilm(filmId, showTimeFilmDto);
    }

    @PutMapping("/{showTimeFilmId}")
    public ShowTimeFilmDto updateShowTimeFilm(@PathVariable Integer showTimeFilmId, @RequestBody ShowTimeFilmDto showTimeFilmDto) {
        return showTimeFilmService.updateShowTimeFilm(showTimeFilmId, showTimeFilmDto);
    }

    @DeleteMapping("/{showTimeFilmId}")
    public void deleteShowTimeFilm(@PathVariable Integer showTimeFilmId) {
        showTimeFilmService.deleteShowTimeFilm(showTimeFilmId);
    }

    @PostMapping("lists/{filmId}")
    public void addShowTimeFilList(@PathVariable Integer filmId, @RequestBody List<ShowTimeFilmDto> showTimeFilmList) {
        showTimeFilmService.addShowTimeFilmList(filmId, showTimeFilmList);
    }

    @GetMapping("/dateTime")
    public List<DateTimeFilmDTO> getDateTimeOfFilm(@RequestParam("filmId") Integer filmId) {
        List<LocalDateTime> dateList = showTimeFilmService.getDateShow(filmId);
        List<DateTimeFilmDTO> dateTimeFilmDTOS = new ArrayList<>();
        for (LocalDateTime date : dateList) {
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
