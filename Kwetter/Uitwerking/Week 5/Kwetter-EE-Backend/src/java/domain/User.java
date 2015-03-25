package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private String web;
    private String image = "http://i.imgur.com/SxKJEWo.png";
    private String bio;
    private String location = "Nederland";

    private Collection<Long> following = new ArrayList();
    private Collection<Long> followers = new ArrayList();
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Tweet> tweets = new ArrayList();

    public User() {
    }

    public User(String naam) {
        this.name = naam;
    }

    public User(String naam, String web, String bio, Long id) {
        this.name = naam;
        this.web = web;
        this.bio = bio;
        this.id = id;
    }

    @XmlElement(required = true)
    public Long getId() {
        return id;
    }

    @XmlElement(required = true)
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(required = true)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @XmlElement(required = true)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlElement(required = true)
    public Collection<Long> getFollowing() {
        return Collections.unmodifiableCollection(following);
    }

    @XmlElement(required = true)
    public Collection<Long> getFollowers() {
        return Collections.unmodifiableCollection(followers);
    }

    public void setFollowing(Collection<Long> following) {
        this.following = following;
    }

    @XmlElement(required = true)
    public Collection<Tweet> getTweets() {
        return Collections.unmodifiableCollection(tweets);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Boolean addFollowing(Long following) {
        return this.following.add(following);
    }

    public Boolean addFollower(Long follower) {
        return this.followers.add(follower);
    }

    public Boolean addTweet(Tweet tweet) {
        return this.tweets.add(tweet);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() + bio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the name fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.User[naam=" + name + "]";
    }

}
