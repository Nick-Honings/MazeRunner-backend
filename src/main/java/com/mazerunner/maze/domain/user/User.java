package com.mazerunner.maze.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@DynamicUpdate
public class User implements IUser, Player {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @Column(unique = true)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String confirmPassword;


    @JsonIgnore
    @Transient
    private int coinsCollected;

    @JsonIgnore
    @Transient
    private int[] spawnPosition;

    public User(String id, String name){
        this.id = id;
        this.name = name;
    }

    public User() {

    }

    // Update user to database.
    public void update(){
        throw new UnsupportedOperationException();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public void incrementCoinsCollected() {
        this.coinsCollected++;
    }

    public int[] getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(int[] spawnPosition) {
        this.spawnPosition = spawnPosition;
    }
}
