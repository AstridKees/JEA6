/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Luc
 */
@Alternative
@Stateless
public class UserDAO_JPAImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    public UserDAO_JPAImpl() {
    }

    @PostConstruct
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
        Query q = em.createQuery("select count(user) from User user");
        return (int) q.getSingleResult();
    }

    @Override
    public void create(User user) {
        em.persist(user);
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }

    @Override
    public List<User> findAll() {
        Query q = em.createQuery("select user from User user");
        return q.getResultList();
    }

    @Override
    public User find(Long id) {
        Query q = em.createQuery("select User from User user where user.id =:id");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }

    @Override
    public void remove(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFollower(User userToFollow, User follower) {
        userToFollow.addFollower(follower.getId());
        follower.addFollowing(userToFollow.getId());
    }

    @Override
    public Long nextTweetID() {
        //Beter zou het gebruik van @GeneratedValue bij id hier zijn
        List<User> users = findAll();
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
    
   
}
