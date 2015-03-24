package service;

import java.util.List;
import javax.ejb.Stateless;
import dao.UserDAO;
import domain.User;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

@Stateless
@ApplicationScoped
public class KwetterService {

    @Inject
    private UserDAO userDAO;

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
        return userDAO.find((Long)id);
    }

    public int count() {
        return userDAO.count();
    }
    
    public Long nextTweetID(){
        return userDAO.nextTweetID();
    }

}