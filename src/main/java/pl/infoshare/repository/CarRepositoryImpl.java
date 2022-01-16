package pl.infoshare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.infoshare.model.Car;
import pl.infoshare.util.JSONFileConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private static final String CARS_TO_FIX_FILEPATH_FILE = "src/main/resources/jsons/carsToFix.json";
    private static final String CARS_FIXED_FILEPATH_FOLDER = "src/main/resources/jsons/fixed";

    private final JSONFileConverter converter;

    @Autowired
    public CarRepositoryImpl(JSONFileConverter converter) {
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
        return converter.fileToObject(CARS_TO_FIX_FILEPATH_FILE);
    }

    @Override
    public List<Car> getAllFixed() {
        return converter.allFilesFromFolderToObject(CARS_FIXED_FILEPATH_FOLDER);
    }

    @Override
    public List<Car> getAllFixedFromDate(LocalDate date) {
        return converter.fileToObject(CARS_FIXED_FILEPATH_FOLDER + "/" + date + ".json");
    }

    @Override
    public void saveToFix(List<Car> cars) {
        converter.objectToJSON(cars, CARS_TO_FIX_FILEPATH_FILE);
    }

    @Override
    public void saveFixed(List<Car> cars, LocalDate date) {
        converter.objectToJSON(cars, CARS_FIXED_FILEPATH_FOLDER + "/" + date + ".json");
    }

    @Override
    public Optional<Car> get(long id) {
        return getAllToFix().stream().filter(i -> i.getId() == id).findFirst();
    }
}
