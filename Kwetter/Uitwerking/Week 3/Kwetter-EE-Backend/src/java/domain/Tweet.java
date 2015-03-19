package domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tweet {

    private static final long serialVersionUID = 1L;

    @XmlElement(required = true)
    private Long id;
    @XmlElement(required = true)
    private String tweet;
    @XmlElement(required = true)
    private Date date;
    @XmlElement(required = true)
    private String postedFrom;

    public Tweet() {
    }

    public Tweet(String tweet) {
        this.tweet = tweet;
    }

    public Tweet(String tweet, Date datum, String vanaf, Long id) {
        this.tweet = tweet;
        this.date = datum;
        this.postedFrom = vanaf;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getDatum() {
        return date;
    }

    public void setDatum(Date datum) {
        this.date = datum;
    }

    public String getVanaf() {
        return postedFrom;
    }

    public void setVanaf(String vanaf) {
        this.postedFrom = vanaf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tweet != null ? tweet.hashCode() + date.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tweet)) {
            return false;
        }
        Tweet other = (Tweet) object;
        return this.hashCode() == other.hashCode();
    }

    @Override
    public String toString() {
        return "twitter.domain.Tweet[id=" + date.toString() + "]";
    }

}
