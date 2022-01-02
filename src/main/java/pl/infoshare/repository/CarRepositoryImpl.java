package pl.infoshare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.infoshare.model.Car;
import pl.infoshare.util.JSONFileConverterImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private static final String CARS_TO_FIX_FILEPATH = "temp";
    private static final String CARS_FIXED_FILEPATH = "temp";

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
    public void save(List<Car> cars, String filePath) {
        converter.objectToJSON(cars, filePath);
    }

    @Override
    public Optional<Car> get(int id) {
        return getAll().stream().filter(i -> i.getId() == id).findFirst();
    }
}
