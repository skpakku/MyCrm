package com.mycrm.restapi;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycrm.model.LoginResponse;
import com.mycrm.model.StatusMsg;
import com.mycrm.model.User;
import com.mycrm.service.MyCrmService;

@RestController
public class MyCrmRestApi {

	private final MyCrmService myCrmService;

	public MyCrmRestApi(MyCrmService myCrmService) {
		super();
		this.myCrmService = myCrmService;
	}

	@RequestMapping("/login")
	public LoginResponse login(@RequestBody User user) {

		String username = user.getUsername();
		String password = user.getPassword();
		String sessionId = myCrmService.authenticate(username, password);

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSessionId(sessionId);

		return loginResponse;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public StatusMsg logout(@RequestHeader(value = "Authorization", required = false) String sessionIdWithHeader) {

		String[] sessionIds = sessionIdWithHeader.split("\\s+");
		StatusMsg sessionIdStatus = myCrmService.checkLoginSessionStatus(sessionIds[1]);
		return sessionIdStatus;

	}

}
