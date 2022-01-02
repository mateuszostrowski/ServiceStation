package pl.infoshare.repository;

import pl.infoshare.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> getAll();
    List<Car> getAllToFix();
    List<Car> getAllFixed();
    void save(List<Car> cars, String filePath);
    Optional<Car> get(int id);
}
