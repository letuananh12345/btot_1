package repository;

import model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IStudentRepo extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findAll(Pageable pageable);

}
