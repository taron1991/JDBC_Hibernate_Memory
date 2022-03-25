package Project;

import java.time.LocalDateTime;

public class Item {
    private long id;
    private String name;
    private String description;
    private LocalDateTime date = LocalDateTime.now();



    public Item() {

    }


    public Item(long id, String name,String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
