package com.mycrm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mycrm.InvalidCredentailsException;
import com.mycrm.UserNotFoundException;
import com.mycrm.dao.CrmDao;
import com.mycrm.model.StatusMsg;
import com.mycrm.model.User;

@Service
public class MyCrmServiceImpl implements MyCrmService {

	private final CrmDao crmDao;
	private Map<String, User> sessions = new HashMap<String, User>();

	public MyCrmServiceImpl(CrmDao crmDao) {
		super();
		this.crmDao = crmDao;
	}

	public String authenticate(String username, String password) {

		User userInfo = crmDao.getUser(username).orElseThrow(() -> new UserNotFoundException());

		if (!userInfo.getPassword().equals(password)) {
			throw new InvalidCredentailsException();
		}

		String sessionId = UUID.randomUUID().toString();
		sessions.put(sessionId, userInfo);
		return sessionId;

	}

	public StatusMsg checkLoginSessionStatus(String sessionId) {

		StatusMsg statusMsg = new StatusMsg();

		if (!sessions.containsKey(sessionId)) {
			statusMsg.setMessage("No login Session Exist for to logout");
			return statusMsg;
		}

		sessions.remove(sessionId);
		statusMsg.setMessage("you have been successfully logged out");
		return statusMsg;

	}

}
