package repository;

import model.ClassRoom;
import org.springframework.data.repository.CrudRepository;

public interface IClassRoomRepo extends CrudRepository<ClassRoom,Long> {
}
