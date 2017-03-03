package com.mycrm.service;

import com.mycrm.model.StatusMsg;

public interface MyCrmService {

	String authenticate(String username, String password);

	StatusMsg checkLoginSessionStatus(String sessionId);

}
