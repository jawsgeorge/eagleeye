package com.eagleeye.restful.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eagleeye.restful.model.Employee;
import com.eagleeye.restful.model.Menu;
import com.eagleeye.restful.model.ResponseEagle;
import com.eagleeye.restful.model.Role;
import com.eagleeye.restful.service.AddMenu;
import com.eagleeye.restful.service.AddRole;
import com.eagleeye.restful.service.EmployeeService;

import com.eagleeye.restful.model.User;
import com.eagleeye.restful.service.UserService;
import com.eagleeye.restful.model.UserDAO;
/**
 * 
 * @author BytesTree
 *
 */

@RestController
@RequestMapping("/eagleeye")
@CrossOrigin(origins = "*")
public class EagleEyeController {

	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService empService;
	
	 @Autowired
	 private AddMenu menuservice;
	  
	 @Autowired
	 private AddRole roleservice;
	
	@Autowired
	UserService userService;
	
	// Private fields
	  
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private UserDAO userDao;
	  
	 
	
	//User Controller

	@RequestMapping(value="/adduser",method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		//To do change for hardcode value
		user.setPassword("eagleeye");
		userService.save(user);
		logger.debug("Added User sucessfully:: " + user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}


	@RequestMapping(value="/loginuser",method = RequestMethod.POST)
	public ResponseEntity<Object> loginUser(@RequestBody User user) {
		logger.debug("User Name received : "+user.getUserName());
		User userObj = userDao.getByUserName(user);
		logger.debug("Added User sucessfully:: " + userObj);
		if (userObj == null) {
			logger.debug("User with name " + user.getUserName() + "  does not exists");
			ResponseEagle res = new ResponseEagle();
			res.setResponseCode("100");
			res.setResponseMessage("User name does not exist");
			
			
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		}
		logger.debug("Found User:: " + userObj);
		
		return new ResponseEntity<Object>(userObj.getRole().getMenu(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getusers",method = RequestMethod.GET)
	public ResponseEntity<Set<Menu>> getAllUsers() {
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			logger.debug("users does not exists");
			//return new ResponseEntity<Menu>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + users.size() + " Employees");
		logger.debug(Arrays.toString(users.toArray()));
		User u = users.get(0);
		logger.debug(u.getRole().getRoleName());
		logger.debug(u.getRole().getMenu());
		List ob = new ArrayList();
		
		//Set s =u.getRole().getMenu();
		ob.addAll(u.getRole().getMenu());
		//logger.debug(users.);
		return new ResponseEntity<Set<Menu>>(u.getRole().getMenu(), HttpStatus.OK);
	}
	
	//Menu controller
	
	@RequestMapping(value="/addMenu",method = RequestMethod.POST)
	public ResponseEntity<Menu> addMenu(@RequestBody Menu menu) {
		menuservice.save(menu);
		logger.debug("Added:: " + menu);
		return new ResponseEntity<Menu>(menu, HttpStatus.CREATED);
	}
	@RequestMapping(value="/updateMenu",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMenu(@RequestBody Menu menu) {
		Menu existingMenu = menuservice.getById(menu.getMenu_id());
		if (existingMenu == null) {
			logger.debug("Menu with id " + menu.getMenu_id() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			menuservice.save(menu);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "getMenu/{id}", method = RequestMethod.GET)
	public ResponseEntity<Menu> getMenu(@PathVariable("id") Long id) {
		Menu menu = menuservice.getById(id);
		if (menu == null) {
			logger.debug("Menu with id " + id + "  does not exists");
			return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Menu:: " + menu);
		return new ResponseEntity<Menu>(menu, HttpStatus.OK);
	}

	

	@RequestMapping(value="/getMenu",method = RequestMethod.GET)
	public ResponseEntity<List<Menu>> getAllMenu() {
		List<Menu> menuList = menuservice.getAll();
		if (menuList.isEmpty()) {
			logger.debug("Menu does not exists");
			return new ResponseEntity<List<Menu>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + menuList.size() + " Menu");
		logger.debug(Arrays.toString(menuList.toArray()));
		return new ResponseEntity<List<Menu>>(menuList, HttpStatus.OK);
	}


	@RequestMapping(value = "deleteMenu/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long id) {
		Menu menu = menuservice.getById(id);
		if (menu == null) {
			logger.debug("Menu with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			menuservice.delete(id);
			logger.debug("Menu with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

	// Role Controller
	@RequestMapping(value="/addRole",method = RequestMethod.POST)
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		roleservice.save(role);
		logger.debug("Added:: " + role);
		return new ResponseEntity<Role>(role, HttpStatus.CREATED);
	}
	@RequestMapping(value="/updateRole",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateRole(@RequestBody Role role) {
		Role existingRole = roleservice.getById(role.getRole_id());
		if (existingRole == null) {
			logger.debug("Role with id " + role.getRole_id() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			roleservice.save(role);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "getRole/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
		Role role = roleservice.getById(id);
		if (role == null) {
			logger.debug("Role with id " + id + "  does not exists");
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Role:: " + role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	

	@RequestMapping(value="/getRoles",method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roleList = roleservice.getAll();
		if (roleList.isEmpty()) {
			logger.debug("Role does not exists");
			return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + roleList.size() + " Role");
		logger.debug(Arrays.toString(roleList.toArray()));
		return new ResponseEntity<List<Role>>(roleList, HttpStatus.OK);
	}


	@RequestMapping(value = "deleteRole/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
		Role role = roleservice.getById(id);
		if (role == null) {
			logger.debug("Role with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			roleservice.delete(id);
			logger.debug("Role with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}
