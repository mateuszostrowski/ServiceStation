package pl.infoshare.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Car {
    private static final String EMPTY_ERROR = "Cannot be empty";
    private static final String WRONG_YEAR_ERROR = "Year cannot be from the future.";

    private long id;
    @NotBlank(message = EMPTY_ERROR)
    private String registrationNumber;
    @NotBlank(message = EMPTY_ERROR)
    private String name;
    private boolean isFixed;
    private Color color;
    @JsonFormat(pattern = "yyyy")
    @NotNull(message = EMPTY_ERROR)
    @PastOrPresent(message = WRONG_YEAR_ERROR)
    private Year yearOfProduction;
    private LocalDate inServiceSince;
    private LocalDate dateOfFix;
}
