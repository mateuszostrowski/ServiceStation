package pl.infoshare.service;

import org.springframework.stereotype.Service;
import pl.infoshare.model.Menu;

import java.util.List;

@Service
public class MenuService {

    public List<Menu> get() {
        return List.of(
                new Menu(1, "Cars to fix", "cars-to-fix"),
                new Menu(2, "Add new car", "/"),
                new Menu(3, "Fix car", "fix-car"),
                new Menu(4, "Fixed cars", "fixed-cars")
        );
    }
}
