package com.mycrm.dao;

import java.util.Optional;

import com.mycrm.model.User;

public interface CrmDao {
	Optional<User> getUser(String userName);
}
