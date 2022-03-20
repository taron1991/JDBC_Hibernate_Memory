package Project;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Pair<S,T> {
    private S firstParameter;
    private T secondParameter;

    @Override
    public String toString() {
        return "Pair{" +
                "firstParameter=" + firstParameter +
                ", secondParameter=" + secondParameter +
                '}';
    }
}
