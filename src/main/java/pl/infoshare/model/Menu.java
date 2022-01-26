package pl.infoshare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private int id;
    private String name;
    private String landingPage;
}
