package pl.infoshare.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    WHITE("White"),
    BLACK("Black"),
    BLUE("Blue"),
    RED("Red"),
    GRAY("Gray"),
    OTHER("Other");

    private final String description;

}
