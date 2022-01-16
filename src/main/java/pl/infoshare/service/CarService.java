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
        Optional<Car> car = carRepository.get(id);
        LocalDate dateOfFix = LocalDate.now();
        List<Car> toFix = carRepository.getAllToFix();
        List<Car> fixed = carRepository.getAllFixedFromDate(dateOfFix);

        if (car.isPresent()) {
            Car toUpdate = car.get();
            toFix.remove(toUpdate);
            toUpdate.setFixed(true);
            toUpdate.setDateOfFix(dateOfFix);
            fixed.add(toUpdate);
            carRepository.saveToFix(toFix);
            carRepository.saveFixed(fixed, dateOfFix);
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
