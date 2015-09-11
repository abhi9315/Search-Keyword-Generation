package database;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by rajarshee on 11/9/15.
 */
@NodeEntity
public class Account {

    @GraphId
    Long id;
    private String username;
    private String name;
    private String password;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return id+":"+name+":"+username+":"+password;
    }
}
