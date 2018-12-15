package org.sehati.service.impl;

import org.sehati.domain.User;
import org.sehati.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired @Qualifier("mongoTemplate") private MongoOperations mongoOperations;
	public User getUser(String username, String password) {
		Query query = new Query(Criteria.where("username").is(username).andOperator(Criteria.where("password").is(password)));
		User user = mongoOperations.findOne(query, User.class);
		return user;
	}

	public void addUser(User user) {
		mongoOperations.save(user);
	}
	
}
