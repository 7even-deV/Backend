package com.bosonit.rs1.application.interfaces;

import com.bosonit.rs1.domain.User;

public interface IUserService {
    void setUser(User user);
    User getUser();
}
