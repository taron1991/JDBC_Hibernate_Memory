package Project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Item /*Person*/{
    private long id;
    private String name;
    private String description;
    private LocalDateTime date = LocalDateTime.now(); //инициализация по текущей дате и времени,дата создания обьекта(человека)



    public Item() {

    }


    public Item(long id, String name,String d) {
        this.id = id;
        this.name = name;
        this.description = d;
    }


    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s",name,description,date);
    }

}
