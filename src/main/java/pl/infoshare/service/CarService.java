package pl.infoshare.service;

import org.springframework.stereotype.Service;
import pl.infoshare.model.Car;
import pl.infoshare.repository.CarRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepositoryImpl carRepository;

    public CarService(CarRepositoryImpl carRepository) {
        this.carRepository = carRepository;
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<Car> getToFix() {
        return carRepository.getAllToFix();
    }

    public void fix(Car car) {
        car.setFixed(true);
    }

    public Optional<Car> get(long id) {
        return carRepository.get(id);
    }

}
