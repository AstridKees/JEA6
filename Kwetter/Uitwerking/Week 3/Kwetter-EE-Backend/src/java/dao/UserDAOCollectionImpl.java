package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import domain.Tweet;
import domain.User;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;

@ApplicationScoped
@Stateless
public class UserDAOCollectionImpl implements UserDAO {

    private final List<User> users = new ArrayList();

    public UserDAOCollectionImpl() {
        initUsers();
    }

    private void initUsers() {
        User u1 = new User("Hans", "http", "geboren 1", 1L);
        User u2 = new User("Frank", "httpF", "geboren 2", 2L);
        User u3 = new User("Tom", "httpT", "geboren 3", 3L);
        User u4 = new User("Sjaak", "httpS", "geboren 4", 4L);
        addFollower(u1, u2);
        addFollower(u1, u3);
        addFollower(u1, u4);
        addFollower(u2, u1);
        addFollower(u3, u1);
        addFollower(u4, u1);

        Tweet t1 = new Tweet("Hallo", new Date(), "PC", 1L);
        Tweet t2 = new Tweet("Hallo again", new Date(), "PC", 2L);
        Tweet t3 = new Tweet("Hallo where are you", new Date(), "PC", 3L);
        u1.addTweet(t1);
        u1.addTweet(t2);
        u1.addTweet(t3);

        this.create(u1);
        this.create(u2);
        this.create(u3);
        this.create(u4);
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
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

    @Override
    public User find(Long id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public Long nextTweetID() {
        Long nextID = 0L;
        for (User u : users) {
            for (Tweet t : u.getTweets()) {
                if (t.getId() >= nextID) {
                    nextID = t.getId() + 1;
                }
            }
        }
        return nextID;
    }
    
    public void addFollower(User userToFollow, User follower){
        userToFollow.addFollower(follower.getId());
        follower.addFollowing(userToFollow.getId());
    }
}
