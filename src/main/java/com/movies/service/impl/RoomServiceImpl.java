package com.movies.service.impl;

import com.movies.entity.dao.Room;
import com.movies.repository.RoomRepository;
import com.movies.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoomServiceImpl  implements RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Override
    @Transactional
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
