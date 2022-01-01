package pl.infoshare.repository;

import pl.infoshare.model.Car;

import java.util.List;

public interface CarRepository {
    List<Car> getAllToFix();
    List<Car> getAllFixed();
    Car get(int id);
    void add(Car car);
}
