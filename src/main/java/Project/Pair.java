package Project;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Pair<T,S>{
    private T firstParametr;
    private S SecondParametr;

    @Override
    public String toString() {
        return "Project.Pair{" +
                "firstParametr=" + firstParametr +
                ", SecondParametr=" + SecondParametr +
                '}';
    }
}
