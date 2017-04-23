package com.eagleeye.restful.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eagleeye.restful.model.Employee;
import com.eagleeye.restful.model.Menu;
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
public class EagleEyeController {

	final static Logger logger = Logger.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService empService;
	
	@Autowired
	UserService userService;
	
	// Private fields
	  
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private UserDAO userDao;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		empService.save(employee);
		logger.debug("Added:: " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@RequestMapping(value="/adduser",method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		userService.save(user);
		logger.debug("Added User sucessfully:: " + user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}


	@RequestMapping(value="/loginuser",method = RequestMethod.POST)
	public ResponseEntity<Set<Menu>> loginUser(@RequestBody User user) {
		logger.debug("User Name received : "+user.getUserName());
		User userObj = userDao.getByUserName(user);
		logger.debug("Added User sucessfully:: " + user);
		if (userObj == null) {
			logger.debug("User with name " + user.getUserName() + "  does not exists");
			return new ResponseEntity<Set<Menu>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found User:: " + userObj);
		return new ResponseEntity<Set<Menu>>(userObj.getRole().getMenu(), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		Employee existingEmp = empService.getById(employee.getId());
		if (existingEmp == null) {
			logger.debug("Employee with id " + employee.getId() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.save(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + "  does not exists");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Employee:: " + employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = empService.getAll();
		if (employees.isEmpty()) {
			logger.debug("Employees does not exists");
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + employees.size() + " Employees");
		logger.debug(Arrays.toString(employees.toArray()));
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = empService.getById(id);
		if (employee == null) {
			logger.debug("Employee with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			empService.delete(id);
			logger.debug("Employee with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
