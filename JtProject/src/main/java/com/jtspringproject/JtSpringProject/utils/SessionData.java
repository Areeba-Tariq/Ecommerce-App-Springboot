package com.jtspringproject.JtSpringProject.utils;

import com.jtspringproject.JtSpringProject.models.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SessionData implements Serializable {
    private User currentUser;

    public SessionData() {
    }

    public SessionData(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
