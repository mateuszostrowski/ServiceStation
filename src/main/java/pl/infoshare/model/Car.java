package pl.infoshare.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {
    private static final String EMPTY_ERROR = "Cannot be empty";
    private static final String WRONG_YEAR_ERROR = "Must contain 4 digits";

    private long id;
    @NotEmpty(message = EMPTY_ERROR)
    private String registrationNumber;
    @NotEmpty(message = EMPTY_ERROR)
    private String name;
    private boolean isFixed;
    private Color color;
    @NotEmpty(message = EMPTY_ERROR)
    @Size(min = 4, max = 4, message = WRONG_YEAR_ERROR)
    private String yearOfProduction;
    private LocalDate inServiceSince;
}
