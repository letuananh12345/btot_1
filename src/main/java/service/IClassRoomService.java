package service;

import model.ClassRoom;

import java.util.ArrayList;

public interface IClassRoomService {
    public ClassRoom findById(long id);

    public ArrayList<ClassRoom> findAll();
}
