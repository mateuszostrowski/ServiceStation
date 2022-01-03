package pl.infoshare.repository;

import pl.infoshare.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> getAll();
    List<Car> getAllToFix();
    List<Car> getAllFixed();
    void save(Car car);
    Optional<Car> get(int id);
}
