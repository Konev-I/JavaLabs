package main.repository;

import main.entity.Departments;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentsRepo extends CrudRepository<Departments, Integer> {
}
