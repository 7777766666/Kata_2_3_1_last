package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        User userForUpdate = entityManager.find(User.class, id);

        userForUpdate.setName(updatedUser.getName());
        userForUpdate.setLastName(updatedUser.getLastName());
        userForUpdate.setAge(updatedUser.getAge());
        entityManager.merge(userForUpdate);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
