package kwetter.service;

import java.util.List;
import javax.ejb.Stateless;
import kwetter.dao.UserDAO;
import kwetter.dao.UserDAOCollectionImpl;
import kwetter.domain.User;

@Stateless
public class KwetterService {

    private UserDAO userDAO = new UserDAOCollectionImpl();

    public KwetterService() {
    }

    public void create(User user) {
        userDAO.create(user);
    }

    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(User user) {
        userDAO.remove(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User find(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int count() {
        return userDAO.count();
    }

}