package Project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Hibernate implements Store {

    private Session session;
    private SessionFactory sessionFactory;

    @Override
    public void init() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }

    @Override
    public Item add(Item item) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        return item;
    }

    @Override
    public boolean delete(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        /*Item item = session.get(Item.class, id);
        if (item!=null) {
            session.remove(item);
            session.getTransaction().commit();
            return true;
        }
        session.getTransaction().commit();
        return false;*/

        Query<Item> query = session.createQuery("delete from Item where id =: id",Item.class);
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(long id, Item item) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
       /* Item item1 = session.get(Item.class, id);
        if (item1 != null) {
            item1.setName(item.getName());
            item1.setDescription(item.getDescription());
            item1.setDate(item.getDate());
            session.update(item1);
            session.getTransaction().commit();
            return true;
        } else {
            session.getTransaction().commit();
            return false;
        }*/

        Query<Item> query = session.createQuery("update Item set name =: name, description =: description, date =: date where id =:id",Item.class);
        query.setParameter("name", item.getName());
        query.setParameter("description", item.getDescription());
        query.setParameter("date", item.getDate());
        query.setParameter("id",id);

        query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Item> findAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Item> item = session.createQuery("from Item", Item.class).getResultList();
        session.getTransaction().commit();
        return item;
    }

    @Override
    public Optional<Item> findById(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        //Item item = session.get(Item.class, id);
        Query<Item> query = session.createQuery(" from Item where id =: id",Item.class);
        query.setParameter("id", id);
        List<Item> list = query.list();
        //exucuteUpdate ВСЕ КРОМЕ SELECTA!!!
        session.getTransaction().commit();
        return list.stream().findFirst();
    }

    @Override
    public List<Item> findByName(String name) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Item> query = session.createQuery("from Item where name =: name",Item.class);
        query.setParameter("name", name);
        List<Item> resultList = query.getResultList();
        session.getTransaction().commit();
        return resultList;
    }

    @Override
    public List<Item> findByIntervalDate(LocalDateTime start, LocalDateTime endd) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Item> query = session.createQuery("from Item where date between :start and :endd",Item.class);
        query.setParameter("start", start);
        query.setParameter("endd", endd);
        List<Item> resultList = query.getResultList();
        session.getTransaction().commit();
        return resultList;

    }
}

