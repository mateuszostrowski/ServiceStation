package pl.infoshare.repository;

import pl.infoshare.model.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRepository {
    List<Car> getAll();
    List<Car> getAllToFix();
    List<Car> getAllFixed();
    List<Car> getAllFixedFromDate(LocalDate date);
    void saveToFix(List<Car> cars);
    void saveFixed(List<Car> cars, LocalDate date);
    Optional<Car> get(long id);
}
