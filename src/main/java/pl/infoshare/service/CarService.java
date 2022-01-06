package pl.infoshare.service;

import org.springframework.stereotype.Service;
import pl.infoshare.model.Car;
import pl.infoshare.repository.CarRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepositoryImpl carRepository;

    public CarService(CarRepositoryImpl carRepository) {
        this.carRepository = carRepository;
    }

    public void saveNew(Car car) {
        List<Car> cars = carRepository.getAllToFix();
        long newId = System.currentTimeMillis();
        car.setId(newId);
        car.setInServiceSince(LocalDate.now());
        cars.add(car);
        carRepository.saveToFix(cars);
    }

    public List<Car> getToFix() {
        return carRepository.getAllToFix();
    }

    public void fixCarWithId(long id) {
        Optional<Car> car = carRepository.get(id);
        List<Car> toFix = carRepository.getAllToFix();
        List<Car> fixed = carRepository.getAllFixed();

        if (car.isPresent()) {
            Car toUpdate = car.get();
            toFix.remove(toUpdate);
            toUpdate.setFixed(true);
            fixed.add(toUpdate);
            carRepository.saveToFix(toFix);
            carRepository.saveFixed(fixed);
        }
    }
}
