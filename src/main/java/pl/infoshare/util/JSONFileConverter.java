package pl.infoshare.util;

import pl.infoshare.model.Car;

import java.util.List;

public interface JSONFileConverter {
    List<Car> convertFileToObject(String filePath);

    List<Car> convertFilesFromFolderToObject(String folderPath);

    void convertObjectToJSON(List<Car> cars, String filePath);
}
