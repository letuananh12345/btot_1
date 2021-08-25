package service;

import model.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IClassRoomRepo;

import java.util.ArrayList;

//@Service
public class ClassRoomService implements IClassRoomService{
    @Autowired
    IClassRoomRepo iClassRoomRepo;

    @Override
    public ClassRoom findById(long id) {
        return iClassRoomRepo.findById(id).get();
    }
    @Override
    public ArrayList<ClassRoom> findAll() {
        return (ArrayList<ClassRoom>) iClassRoomRepo.findAll();
    }
}
