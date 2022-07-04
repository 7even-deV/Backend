package com.bosonit.rs1.application.impls;

import com.bosonit.rs1.application.interfaces.IUserService;
import com.bosonit.rs1.domain.User;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    User user;

    public void setUser(User newUser) {
        this.user = newUser;
    }

    @Override
    public User getUser() {
        return this.user;
    }
}
