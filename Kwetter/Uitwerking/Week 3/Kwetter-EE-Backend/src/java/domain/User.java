package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private static final long serialVersionUID = 1L;

    @XmlElement(required = true)
    private Long id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String web;
    @XmlElement(required = true)
    private String image = "http://i.imgur.com/SxKJEWo.png";
    @XmlElement(required = true)
    private String bio;

    private Collection<Long> following = new ArrayList();
    private Collection<Long> followers = new ArrayList();
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

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Long> getFollowing() {
        return Collections.unmodifiableCollection(following);
    }

    public void setFollowing(Collection<Long> following) {
        this.following = following;
    }

    public Collection<Tweet> getTweets() {
        return Collections.unmodifiableCollection(tweets);
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
