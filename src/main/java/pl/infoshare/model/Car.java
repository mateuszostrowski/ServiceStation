package pl.infoshare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private int id;
    private String registrationNumber;
    private String name;
    private boolean isFixed;
    private Color color;
    private int yearOfProduction;
}
