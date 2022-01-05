package pl.infoshare.service;

import org.springframework.stereotype.Service;
import pl.infoshare.model.Car;
import pl.infoshare.repository.CarRepositoryImpl;

import java.util.List;

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

}
