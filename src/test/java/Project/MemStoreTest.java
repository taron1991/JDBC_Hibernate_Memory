package Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.testng.Assert.*;

public class MemStoreTest {

    @Test
    public void testAdd() {
        MemStore memStore = new MemStore();
        Item item = new Item("oleg", "person");
        Item itemwithid = memStore.add(item);
        List<Item> all = memStore.findAll();
        assertEquals(1, all.size());
    }

    @Test
    public void testDelete() {
        MemStore memStore = new MemStore();
        Item createItem = new Item("Pavel", "person");
        Item add = memStore.add(createItem);
        long id = add.getId();
        boolean delete = memStore.delete(id);
        assertTrue(delete);
    }

    @Test
    public void testUpdate() {
        MemStore memStore = new MemStore();
        Item createItem = new Item("Ivan", "person");
        Item add = memStore.add(createItem);
        long id = add.getId();
        Item item2 = new Item("Anna", "person");
        boolean update = memStore.update(id, item2);
        assertTrue(update);
    }

    @Test
    public void testFindAll() {
        MemStore memStore = new MemStore();
        Item createItem = new Item("Ivan", "person");
        Item createItem2 = new Item("Anna", "person");
        Item item = memStore.add(createItem);
        Item item1 = memStore.add(createItem2);
        List<Item> result = memStore.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        MemStore memStore = new MemStore();
        Item createItem = new Item("Ivan", "person");
        Item item = memStore.add(createItem);
        long id = item.getId();
        Optional<Item> byId = memStore.findById(id);
        assertTrue(byId.isPresent());
    }

    @Test
    public void testFindByName() {
        MemStore memStore = new MemStore();
        Item createItem = new Item("Ivan", "person");
        Item item = memStore.add(createItem);
        List<Item> byName = memStore.findByName(item.getName());
        for (Item item1 : byName) {
            if (item1.getName().equals(createItem.getName())) {
                assertEquals(createItem.getName(), item1.getName());
            }
        }
    }

    @Test
    public void testFindByIntervalDate() {
        MemStore memStore = new MemStore();
        Item createItem = new Item("Ivan", "person");
        memStore.add(createItem);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minusSeconds = now.minusSeconds(1);
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime plusSeconds = now1.plusSeconds(1);
        List<Item> result = memStore.findByIntervalDate(minusSeconds, plusSeconds);

        for (Item item1 : result) {
            LocalDateTime date1 = item1.getDate();
            assertTrue(date1.isAfter(minusSeconds) && date1.isBefore(plusSeconds));
        }
    }
}