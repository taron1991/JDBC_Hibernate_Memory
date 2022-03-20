package Project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

//sql injection допроверка и логика если есть параметры
public class JdbcStore implements Store {

    private static final Logger log = LoggerFactory.getLogger(JdbcStore.class);

    private Connection connection;
    private String INSERT_SQL = "INSERT INTO items(name,description,createtime) VALUES(?,?,?)";
    private String DELETE_SQl = "DELETE FROM items WHERE items.id = ?";
    private String UPDATE_SQl = "UPDATE items SET name = ? , description = ? WHERE items.id = ?";
    private String FINDALL_SQl = "SELECT*FROM items";
    private String FIND_BY_ID = "SELECT*FROM items WHERE id=?";
    private String FIND_BY_NAME = "SELECT*FROM items WHERE name=?";
    private String Find_BY_Interval = "SELECT *FROM items where createtime BETWEEN ? AND ?";


    public JdbcStore(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void init() {
        try (InputStream inputStream = JdbcStore.class
                .getClassLoader() //загрузчик класса,определяет файловую систему,знает о всех файлах
                .getResourceAsStream("app.properties") /*создает поток данных из файлов*/) {
            Properties properties = new Properties(); // конфиг клас который хранит в себе параметры в виде клбч значение
            properties.load(inputStream);  //есть мапа где ключ url а значения то что идет после знака равно
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));

        } catch (Exception e) {
            throw new IllegalArgumentException(e);

        }
    }

    @Override
    public Item add(Item item) {
        /*PreaparedStatemnet между бд и jdbc создан для того чтобы избежать иньекций*/
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setTimestamp(3, TrackerUtil.parserTime(item.getDate()));
            preparedStatement.executeUpdate();

            item.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            log.warn("error has happened while adding");

        }
        return item;
    }

    @Override
    public boolean delete(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQl)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(); //ddl operations
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException exception) {
            log.warn("error remove has been failed with id {}", id);
        }
        return false;
    }

    @Override
    public boolean update(long id, Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQl)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setLong(3, id);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException sqlException) {
            log.warn("id not found");
        }
        return false;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FINDALL_SQl);
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setDate(resultSet.getTimestamp("createtime").toLocalDateTime());
                result.add(item);
            }

        } catch (SQLException sqlException) {
            log.warn("error in findall");
        }

        return result;
    }

    @Override
    public Optional<Item> findById(long id) {
        List<Item> items = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setDate(resultSet.getTimestamp("createtime").toLocalDateTime());
                items.add(item);
            }

        } catch (SQLException e) {
            log.warn("id not found");
        }

        return items.stream().findFirst();
    }

    @Override
    public List<Item> findByName(String name) {
        List<Item> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setDate(resultSet.getTimestamp("createtime").toLocalDateTime());
                list.add(item);
            }

        } catch (SQLException e) {
            log.warn("name not found");
        }
        return list;
    }

    @Override
    public List<Item> findByIntervalDate(String intervaldate) {
        List<Item> list = new ArrayList<>();
        Item item = new Item();
        Pair<LocalDateTime, LocalDateTime> localDateTimeLocalDateTimePair = TrackerUtil.transformationStringToLocalDateTime(intervaldate);
        LocalDateTime firstParameter = localDateTimeLocalDateTimePair.getFirstParameter();
        LocalDateTime secondParameter = localDateTimeLocalDateTimePair.getSecondParameter();

        try(PreparedStatement preparedStatement = connection.prepareStatement(Find_BY_Interval)){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(firstParameter));
            preparedStatement.setTimestamp(2,Timestamp.valueOf(secondParameter));
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString(2);
                String descripion = resultSet.getString(3);
                Timestamp time = resultSet.getTimestamp(4);
                item.setName(name);
                item.setDescription(descripion);
                item.setDate(time.toLocalDateTime());
                list.add(item);
            }
        }
        catch (SQLException exception){
            log.error("interval not found");
        }
        return list;
    }
}
