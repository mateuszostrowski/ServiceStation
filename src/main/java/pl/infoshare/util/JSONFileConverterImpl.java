package pl.infoshare.util;

import com.google.gson.Gson;
import pl.infoshare.model.Car;

import java.util.List;

public class JSONFileConverterImpl implements JSONFileConverter{
    @Override
    public List<Car> fileToObject(String filePath) {
        return null;
    }

    @Override
    public Gson objectToJSON(List<Car> cars) {
        return null;
    }
}
