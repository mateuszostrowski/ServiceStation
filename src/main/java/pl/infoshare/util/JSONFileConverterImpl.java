package pl.infoshare.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Component;
import pl.infoshare.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JSONFileConverterImpl implements JSONFileConverter {

    @Override
    public List<Car> convertFileToObject(String filePath) {
        Gson gson = new Gson();
        List<Car> cars = new ArrayList<>();
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            cars = gson.fromJson(reader, new TypeToken<List<Car>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("File " + filePath + " not found. New file created.");
        }
        return cars;
    }

    @Override
    public List<Car> convertFilesFromFolderToObject(String folderPath) {
        List<Car> allCars = new ArrayList<>();
        File dir = new File(folderPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                String filePath = file.getPath();
                List<Car> cars = convertFileToObject(filePath);
                allCars.addAll(cars);
            }
        }
        return allCars;
    }

    @Override
    public void convertObjectToJSON(List<Car> cars, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(cars, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}