package pl.infoshare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.infoshare.model.Car;
import pl.infoshare.util.JSONFileConverter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryImpl implements CarRepository {

    @Value("${path.carsToFixFile}")
    private String CARS_TO_FIX_FILEPATH_FILE;
    @Value("${path.carsFixedFolder}")
    private String CARS_FIXED_FILEPATH_FOLDER;

    private final JSONFileConverter converter;

    @Autowired
    public CarRepositoryImpl(JSONFileConverter converter) {
        this.converter = converter;
    }

    @Override
    public List<Car> getAllToFix() {
        return converter.convertFileToObject(CARS_TO_FIX_FILEPATH_FILE);
    }

    @Override
    public List<Car> getAllFixed() {
        return converter.convertFilesFromFolderToObject(CARS_FIXED_FILEPATH_FOLDER);
    }

    @Override
    public List<Car> getAllFixedFromDate(LocalDate date) {
        return converter.convertFileToObject(CARS_FIXED_FILEPATH_FOLDER + "/" + date + ".json");
    }

    @Override
    public void saveToFix(List<Car> cars) {
        converter.convertObjectToJSON(cars, CARS_TO_FIX_FILEPATH_FILE);
    }

    @Override
    public void saveFixed(List<Car> cars, LocalDate date) {
        converter.convertObjectToJSON(cars, CARS_FIXED_FILEPATH_FOLDER + "/" + date + ".json");
    }

    @Override
    public Optional<Car> getById(long id) {
        return getAllToFix().stream().filter(i -> i.getId() == id).findFirst();
    }
}
