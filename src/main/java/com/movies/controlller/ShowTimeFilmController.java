package com.movies.controlller;

import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.entity.dto.ShowTimeFilmDto;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping
    public List<String> getDate(@RequestParam("filmId") Integer filmId) {
        List<Date> dateList = showTimeFilmService.getDateShow(filmId);
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd-MM-yyyy");
        List<String> stringList = new ArrayList<>();
        for (Date date : dateList) {
            stringList.add(formatter.format(date));
        }
        return stringList;
    }

    @GetMapping("/time")
    public List<String> getTime(@RequestParam("filmId") Integer filmId,
                                @RequestParam("date") String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d = format.parse(date);
        List<Date> timeList = showTimeFilmService.getTimeShow(filmId, d);
        List<String> stringList = new ArrayList<>();
        format = new SimpleDateFormat("HH:mm:ss");
        for (Date time: timeList) {
            stringList.add(format.format(time));
        }
        return stringList;
    }

    @GetMapping("/filmInDate")
    public List<FilmTimeDTO> getShowTimeInDay(@RequestParam("date") String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d = format.parse(date);
        List<FilmTimeDTO> filmTimeDTOS = showTimeFilmService.getShowTimeInDay(d);

        return filmTimeDTOS;
    }
}
