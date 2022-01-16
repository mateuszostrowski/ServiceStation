package pl.infoshare.util;

import com.google.gson.Gson;
import pl.infoshare.model.Car;

import java.util.List;

public interface JSONFileConverter {
    List<Car> fileToObject(String filePath);
    List<Car> allFilesFromFolderToObject(String folderPath);
    Gson objectToJSON(List<Car> cars, String filePath);
}
