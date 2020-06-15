package com.movies.controlller;

import com.movies.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/show-time")
public class ShowTimeController {
    @Autowired
    private ShowTimeService showTimeService;

    @GetMapping
    public List<String> getDate(@RequestParam("filmId") Integer filmId) {
        List<Date> dateList = showTimeService.getDateShow(filmId);
        SimpleDateFormat formatter = new SimpleDateFormat("E, MM/dd/yyyy");
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
        List<Date> timeList = showTimeService.getTimeShow(filmId, d);
        List<String> stringList = new ArrayList<>();
        format = new SimpleDateFormat("HH:mm:ss");
        for (Date time: timeList) {
            stringList.add(format.format(time));
        }
        return stringList;
    }
}

