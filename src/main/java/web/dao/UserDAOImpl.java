package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> index() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User userToBeUpdated = entityManager.find(User.class, id);

        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(show(id));
    }
}
