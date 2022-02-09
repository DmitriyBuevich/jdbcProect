package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id int NOT NULL AUTO_INCREMENT, name varchar(50), lastName varchar(50), " +
                    "age int, PRIMARY KEY (id))").addEntity(User.class).executeUpdate();

            session.getTransaction().commit();
            System.out.println("Таблица создана");
        } catch (Exception e) {
            if (Util.getSessionFactory() != null) {
                Util.getSessionFactory().close();
            }
        }
    }


    @Override
    public void dropUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();

            System.out.println("Таблица удалена");
        } catch (Exception e) {
            if (Util.getSessionFactory() != null) {
                Util.getSessionFactory().close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            session.getTransaction().commit();

        } catch (Exception e) {
            if (Util.getSessionFactory() != null) {
                Util.getSessionFactory().close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = id").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            if (Util.getSessionFactory() != null) {
                Util.getSessionFactory().close();
            }
        }


    }

    @Override
    public List<User> getAllUsers()  {

        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            users = session.createQuery("FROM User").list();
            session.getTransaction().commit();
            System.out.println("Получить пользователей");
        } catch (Exception e) {
            if (Util.getSessionFactory() != null) {
                Util.getSessionFactory().close();
            }
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Очистить таблицу пользователей");
        } catch (Exception e) {
            if (Util.getSessionFactory() != null) {
                Util.getSessionFactory().close();
            }
        }
    }
}
