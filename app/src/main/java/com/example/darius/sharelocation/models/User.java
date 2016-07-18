package com.example.darius.sharelocation.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 7/18/16.
 */
public class User {
    String name;
    String email;
    List<String> playlistIds = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, List<String> playlistIds) {
        this.name = name;
        this.email = email;
        this.playlistIds = playlistIds;

    }

    public String getName() {
        return name;
    }


    public List<String> getPlaylistIds() {
        return playlistIds;
    }
    public void addPlaylistId(String playlistId) {
        this.playlistIds.add(playlistId);
    }
    public void setPlaylistIds(List<String> playlistIds) {
        this.playlistIds = playlistIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
