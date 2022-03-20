package Project;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MemStore implements Store {
    public final Logger LOG = LoggerFactory.getLogger(MemStore.class);//Cоздали лог для контроля каких-то событый

    Map<Long, Item> collections = new ConcurrentHashMap<>(); //COncurrent


    @Override
    public void init() {

    }

    @Override
    public Item add(Item item) {
        long id = TrackerUtil.generateId();
        System.out.println("ID"+id);
        collections.putIfAbsent(id, item);
        item.setId(id);
        return item;
    }

    @Override
    public boolean delete(long id) {
        if (collections.containsKey(id)) {
            collections.remove(id);
            LOG.info("item successfully deleted with id: {} ", id);
            return true;
        } else {
            LOG.warn("item with id not found: {} ", id);
            return false;
        }
    }

    @Override
    public boolean update(long id, Item item) {
        if (collections.containsKey(id)) {
            Item result = collections.get(id);
            result.setDescription(item.getDescription());
            result.setName(item.getName());
            collections.put(id,result);
            return true;
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        return collections
                .values()
                .stream()
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Item> findById(long id) {
        return collections
                .values()
                .stream()
                .filter(x->x.getId()==id)
                .findFirst();
    }

    @Override
    public List<Item> findByName(String name) {
        return collections
                .values()
                .stream()
                .filter(item->item.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findByIntervalDate(String intervaldate) {
        Pair<LocalDateTime, LocalDateTime> localDateTimeLocalDateTimePair = TrackerUtil.transformationStringToLocalDateTime(intervaldate);
        List<Item> list = new ArrayList<>();

        LocalDateTime firstParameter = localDateTimeLocalDateTimePair.getFirstParameter(); //13:13
        LocalDateTime secondParameter = localDateTimeLocalDateTimePair.getSecondParameter();//13:45

        List<LocalDateTime> localDateTimes = new ArrayList<>();//когда пользаки добавились
        for (Item item : collections.values()) {
            localDateTimes.add(item.getDate());
            list.add(item);
        }

        List<Item> list1 = new ArrayList<>();
        for (int i = 0; i <list.size(); i++) {
            if(localDateTimes.get(i).isAfter(firstParameter) && localDateTimes.get(i).isBefore(secondParameter)){
                list1.add(list.get(i));
            }
        }
        return list1;
    }
}
