package kwetter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kwetter.domain.Tweet;
import kwetter.domain.User;

//@Stateless
public class UserDAOCollectionImpl implements UserDAO {

    private List<User> users = new ArrayList();

    public UserDAOCollectionImpl() {
        initUsers();
    }

    private void initUsers() {
        User u1 = new User("Hans", "http", "geboren 1");
        User u2 = new User("Frank", "httpF", "geboren 2");
        User u3 = new User("Tom", "httpT", "geboren 3");
        User u4 = new User("Sjaak", "httpS", "geboren 4");
        u1.addFollowing(u2);
        u1.addFollowing(u3);
        u1.addFollowing(u4);

        Tweet t1 = new Tweet("Hallo", new Date(), "PC");
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC");
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC");
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);

        this.create(u1);
        this.create(u2);
        this.create(u3);
        this.create(u4);
    }

    public int count() {
        return users.size();
    }

    public void create(User user) {
        users.add(user);
    }

    public void edit(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<User> findAll() {
        return new ArrayList(users);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public User find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
