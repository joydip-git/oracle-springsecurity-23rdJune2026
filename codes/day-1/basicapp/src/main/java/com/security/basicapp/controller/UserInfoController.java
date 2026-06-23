package com.security.basicapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.basicapp.models.UserInfo;

@RestController
public class UserInfoController {

	private List<UserInfo> users = new ArrayList<>(
			List.of(new UserInfo(100, "anik", "anik@123"), new UserInfo(101, "joy", "joy@123")));

	@GetMapping("/all")
	public Collection<UserInfo> getUsers() {
		return users;
	}

	@PostMapping("/add")
	public UserInfo addUser(@RequestBody UserInfo userInfo) {
		users.add(userInfo);
		return userInfo;
	}

	@DeleteMapping("/delete/{id}")
	public UserInfo deleteUser(@RequestParam int id) {
		Optional<UserInfo> found = users.stream().filter(u->u.getId()==id).findFirst();
		if(!found.isEmpty()) {
			UserInfo userToDelete = found.get();
			users.remove(userToDelete);
			return userToDelete;
		}else
			return null;
	}
}
