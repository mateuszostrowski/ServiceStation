package pl.infoshare.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {
    private int id;
    private String registrationNumber;
    private String name;
    private boolean isFixed;
    private Color color;
    private int yearOfProduction;
}
