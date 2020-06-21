package com.movies.service.impl;

import com.movies.converter.bases.Converter;
import com.movies.entity.dao.Film;
import com.movies.entity.dao.Room;
import com.movies.entity.dao.Seat;
import com.movies.entity.dao.ShowTimeFilm;
import com.movies.entity.dto.FilmDTO;
import com.movies.entity.dto.FilmTimeDTO;
import com.movies.entity.dto.ShowTimeFilmDto;
import com.movies.repository.FilmRepository;
import com.movies.repository.RoomRepository;
import com.movies.repository.SeatRepository;
import com.movies.repository.ShowTimeFilmRepository;
import com.movies.service.ShowTimeFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShowTimeFilmServiceImpl implements ShowTimeFilmService {

    @Autowired
    private ShowTimeFilmRepository showTimeFilmRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private Converter<Film, FilmDTO> filmFilmDTOConverter;

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<ShowTimeFilm> getShowTimeFilmByFilmId(Integer filmId) {
        return showTimeFilmRepository.findAllByFilmId(filmId);
    }

    @Override
    public ShowTimeFilmDto addShowTimeFilm(Integer filmId, ShowTimeFilmDto showTimeFilmDto) {
        ShowTimeFilm showTimeFilm = new ShowTimeFilm();
        showTimeFilm.setTime(showTimeFilmDto.getTime());
        showTimeFilm.setFilm(filmRepository.getOne(filmId));
        showTimeFilm.setRoom(roomRepository.getOne(showTimeFilmDto.getRoom().getId()));
        showTimeFilm = showTimeFilmRepository.save(showTimeFilm);
        Film film = filmRepository.getOne(filmId);
        film.getShowTimeFilms().add(showTimeFilm);
        showTimeFilmDto.setFilmId(filmId);
        showTimeFilmDto.setId(showTimeFilm.getId());
        List<String> roomNames = getListSeats(showTimeFilmDto.getRoom().getId());

        for (String roomName : roomNames) {
            Seat seat = new Seat();
            seat.setShowTimeFilm(showTimeFilm);
            seat.setName(roomName);
            seatRepository.save(seat);
        }

        return showTimeFilmDto;
    }

    @Override
    public List<Date> getDateShow(Integer filmId) {
        return showTimeFilmRepository.findDate(filmId);
    }

    @Override
    public List<LocalDateTime> getTimeShow(Integer filmId, LocalDate date) {
        return showTimeFilmRepository.findTimeFromNow(filmId, date);
    }

    @Override
    public List<FilmTimeDTO> getShowTimeInDay(LocalDate d) {
        List<Film> films = showTimeFilmRepository.findFilmByDate(d);
        List<FilmTimeDTO> filmTimeDTOS = new ArrayList<>();
        for (Film film : films) {
            FilmTimeDTO filmTimeDTO = new FilmTimeDTO();
            filmTimeDTO.setFilm(filmFilmDTOConverter.convert(film));
            List<LocalDateTime> timeList = showTimeFilmRepository.findTimeFromNow(film.getId(), d);
            filmTimeDTO.setTime(timeList);
            filmTimeDTOS.add(filmTimeDTO);
        }

        return filmTimeDTOS;
    }

    @Override
    public ShowTimeFilm getOneByFilmAndTime(int filmId, LocalDateTime dateTime) {
        return showTimeFilmRepository.findOneByFilmAndTime(filmId, dateTime);
    }

    public List<String> getListSeats(Integer roomId) {
        Room room = roomRepository.findById(roomId).get();
        List<String> roomNames = Arrays.asList(room.getListSeats().split(" "));
        roomNames.forEach(System.out::println);
        return roomNames;
    }


}
