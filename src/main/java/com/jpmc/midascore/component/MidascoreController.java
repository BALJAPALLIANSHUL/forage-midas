package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MidascoreController {

	private final UserRepository userRepository;

	@Autowired
	public MidascoreController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/balance")
	public Balance getBalance(@RequestParam("userId") long userId) {
		UserRecord user = userRepository.findById(userId).orElse(null);
		float amount = (user != null) ? user.getBalance() : 0f;
		return new Balance(amount);
	}
}
