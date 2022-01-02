package pl.infoshare.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;
import pl.infoshare.model.Car;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JSONFileConverterImpl implements JSONFileConverter {

    @Override
    public List<Car> fileToObject(String filePath) {
        Gson gson = new Gson();
        List<Car> cars = new ArrayList<>();
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            cars = gson.fromJson(reader, Car.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Gson objectToJSON(List<Car> cars, String filePath) {
        Gson gson = new Gson();
        try {
            gson.toJson(cars, new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson;
    }
}
