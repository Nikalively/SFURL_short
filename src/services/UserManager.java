package services;

import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, User> users = new HashMap<>();

    public User createUser() {
        User user = new User();
        users.put(user.getUuid(), user);
        return user;
    }

    public User getUser(String uuid) {
        return users.get(uuid);
    }
}