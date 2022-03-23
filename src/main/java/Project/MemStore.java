package Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemStore implements Store{
    public final Logger LOG = LoggerFactory.getLogger(MemStore.class);

    Map<Long, Item> collections = new ConcurrentHashMap<>();


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
        return new ArrayList<>(collections
                .values());

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
    public List<Item> findByIntervalDate(LocalDateTime start, LocalDateTime end) {
        return collections.values()
                .stream()
                .filter(item -> item.getDate().isBefore(end) && item.getDate().isAfter(start))
                .collect(Collectors.toList());
    }

}
