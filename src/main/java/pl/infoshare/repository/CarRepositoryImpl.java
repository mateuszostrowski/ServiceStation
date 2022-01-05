package pl.infoshare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.infoshare.model.Car;
import pl.infoshare.util.JSONFileConverterImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private static final String CARS_TO_FIX_FILEPATH = "src/main/resources/jsons/carsToFix.json";
    private static final String CARS_FIXED_FILEPATH = "src/main/resources/jsons/carsFixed.json";

    private final JSONFileConverterImpl converter;

    @Autowired
    public CarRepositoryImpl(JSONFileConverterImpl converter) {
        this.converter = converter;
    }

    @Override
    public List<Car> getAll() {
        return Stream.of(getAllToFix(), getAllFixed())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getAllToFix() {
        return converter.fileToObject(CARS_TO_FIX_FILEPATH);
    }

    @Override
    public List<Car> getAllFixed() {
        return converter.fileToObject(CARS_FIXED_FILEPATH);
    }

    @Override
    public void save(Car car) {
        List<Car> cars = getAllToFix();
        long newId = System.currentTimeMillis();
        car.setId(newId);
        car.setInServiceSince(LocalDate.now());
        cars.add(car);
        converter.objectToJSON(cars, CARS_TO_FIX_FILEPATH);
    }

    @Override
    public Optional<Car> get(int id) {
        return getAll().stream().filter(i -> i.getId() == id).findFirst();
    }
}
