package pl.infoshare.service;

import org.springframework.stereotype.Service;
import pl.infoshare.model.Car;
import pl.infoshare.repository.CarRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
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
        return carRepository.getAllToFix().stream()
                .sorted(Comparator.comparing(Car::getInServiceSince))
                .collect(Collectors.toList());
    }

    public void fixCarWithId(long id) {
        Optional<Car> car = carRepository.getById(id);
        LocalDate dateOfFix = LocalDate.now();
        List<Car> carsToFix = carRepository.getAllToFix();
        List<Car> carsFixed = carRepository.getAllFixedFromDate(dateOfFix);

        if (car.isPresent()) {
            Car carToUpdate = car.get();
            carsToFix.remove(carToUpdate);
            carToUpdate.setFixed(true);
            carToUpdate.setDateOfFix(dateOfFix);
            carsFixed.add(carToUpdate);
            carRepository.saveToFix(carsToFix);
            carRepository.saveFixed(carsFixed, dateOfFix);
        }
    }

    public List<Car> getFixed() {
        return carRepository.getAllFixed();
    }

    public List<Car> findMatch(String carDescription) {
        List<Car> allToFix = carRepository.getAllToFix();
        return allToFix.stream()
                .filter(c -> c.toString().toLowerCase(Locale.ROOT).contains(carDescription.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
