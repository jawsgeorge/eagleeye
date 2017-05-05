package com.eagleeye.restful.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.eagleeye.restful.model.ConstructSlotAndGround;
import com.eagleeye.restful.model.Employee;
import com.eagleeye.restful.model.Ground;
import com.eagleeye.restful.model.GroundDao;
import com.eagleeye.restful.model.Menu;
import com.eagleeye.restful.model.ResponseEagle;
import com.eagleeye.restful.model.Role;
import com.eagleeye.restful.model.Slot;
import com.eagleeye.restful.service.AddMenu;
import com.eagleeye.restful.service.AddRole;
import com.eagleeye.restful.service.DAOService;
import com.eagleeye.restful.service.EmployeeService;
import com.eagleeye.restful.service.GroundService;
import com.eagleeye.restful.service.SlotService;
import com.eagleeye.restful.model.User;
import com.eagleeye.restful.service.UserService;
import com.eagleeye.restful.web.SlotRequest;
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
	 private AddMenu menuService;
	  
	 @Autowired
	 private AddRole roleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroundService groundService;
	
	@Autowired
	SlotService slotService;
	
	// Private fields
	  
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private UserDAO userDao;
	  
	 @Autowired
	 GroundDao groundDao;
	 
	 @Autowired
	 DAOService daoService;
	
	//User Controller

	@RequestMapping(value="/adduser",method = RequestMethod.POST)
	public ResponseEntity<List<User>> addUser(@RequestBody User user) {
		//To do change for hardcode value
		user.setPassword("eagleeye");
		userService.save(user);
		logger.debug("Added User sucessfully:: " + user);
		List<User> userList = userService.getAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.CREATED);
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
		menuService.save(menu);
		logger.debug("Added:: " + menu);
		return new ResponseEntity<Menu>(menu, HttpStatus.CREATED);
	}
	@RequestMapping(value="/updateMenu",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMenu(@RequestBody Menu menu) {
		Menu existingMenu = menuService.getById(menu.getMenu_id());
		if (existingMenu == null) {
			logger.debug("Menu with id " + menu.getMenu_id() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			menuService.save(menu);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "getMenu/{id}", method = RequestMethod.GET)
	public ResponseEntity<Menu> getMenu(@PathVariable("id") Long id) {
		Menu menu = menuService.getById(id);
		if (menu == null) {
			logger.debug("Menu with id " + id + "  does not exists");
			return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Menu:: " + menu);
		return new ResponseEntity<Menu>(menu, HttpStatus.OK);
	}

	

	@RequestMapping(value="/getMenu",method = RequestMethod.GET)
	public ResponseEntity<List<Menu>> getAllMenu() {
		List<Menu> menuList = menuService.getAll();
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
		Menu menu = menuService.getById(id);
		if (menu == null) {
			logger.debug("Menu with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			menuService.delete(id);
			logger.debug("Menu with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

	// Role Controller
	@RequestMapping(value="/addRole",method = RequestMethod.POST)
	public ResponseEntity<List<Role>> addRole(@RequestBody Role role) {
		roleService.save(role);
		logger.debug("Added:: " + role);
		List<Role> roleList = roleService.getAll();
		
		return new ResponseEntity<List<Role>>(roleList, HttpStatus.OK);
	}
	@RequestMapping(value="/updateRole",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateRole(@RequestBody Role role) {
		Role existingRole = roleService.getById(role.getRole_id());
		if (existingRole == null) {
			logger.debug("Role with id " + role.getRole_id() + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			roleService.save(role);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}


	@RequestMapping(value = "getRole/{id}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
		Role role = roleService.getById(id);
		if (role == null) {
			logger.debug("Role with id " + id + "  does not exists");
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}
		logger.debug("Found Role:: " + role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	

	@RequestMapping(value="/getRoles",method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roleList = roleService.getAll();
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
		Role role = roleService.getById(id);
		if (role == null) {
			logger.debug("Role with id " + id + " does not exists");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			roleService.delete(id);
			logger.debug("Role with id " + id + " deleted");
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
	//Ground Controller
	
	@RequestMapping(value="/addGround",method = RequestMethod.POST)
	public ResponseEntity<List<Ground>> addGround(@RequestBody Ground ground) {
		groundService.save(ground);
		logger.debug("Added:: " + ground);
		List<Ground> grounds = groundService.getAll();
		return new ResponseEntity<List<Ground>>(grounds, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/getGroundByPlace", method=RequestMethod.POST)
	public ResponseEntity<List<Ground>> getGroundByPlace(@RequestBody Ground ground){
		String city=ground.getCity();
		String place=ground.getPlace();
		
		List<Ground> grounds=groundDao.getGroundByCity(city, place);
		return new ResponseEntity<List<Ground>>(grounds, HttpStatus.OK);
		
	}
	
	//Method not used now.Hardcoded the list of cities in UI
	
//	@RequestMapping(value="/diplayAllCities",method=RequestMethod.GET)
//	public ResponseEntity< List<Ground>> bookSlot(){
//		 List<Ground> objs = new ArrayList() ;
//		
//		objs.addAll(groundDao.getLocationList());   
//		
//		 return new ResponseEntity<List<Ground>>(objs,HttpStatus.OK);
//		
//	}
	
	@RequestMapping(value="/getAllGrounds",method=RequestMethod.GET)
	public ResponseEntity<List<Ground>> getAllGrounds(){
		List<Ground> groundList=groundService.getAll();
		if (groundList.isEmpty()) {
			logger.debug("Ground does not exists");
			return new ResponseEntity<List<Ground>>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + groundList.size() + " Grounds");
		logger.debug(Arrays.toString(groundList.toArray()));
		return new ResponseEntity<List<Ground>>(groundList,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getSlotAndGround",method=RequestMethod.GET)
	public ResponseEntity<ConstructSlotAndGround> getAllSlotAndGround(){
		List<Ground> groundList=groundService.getAll();
		if (groundList.isEmpty()) {
			logger.debug("Ground does not exists");
			//return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		List<Slot> slotList=slotService.getAll();
		ConstructSlotAndGround slotGround = new ConstructSlotAndGround();
		slotGround.setGround(groundList);
		slotGround.setSlot(slotList);
		logger.debug("Found " + groundList.size() + " Grounds");
		logger.debug(Arrays.toString(groundList.toArray()));
		return new ResponseEntity<ConstructSlotAndGround>(slotGround,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getSlotBooking", method=RequestMethod.POST)
	public ResponseEntity<List> getSlotBooking(@RequestBody SlotRequest slotRequest){
		String date=slotRequest.getBookDate();
		
		List reponseList = daoService.getSlotBooking(date);
		return new ResponseEntity<List>( reponseList,HttpStatus.OK);
		
	}	
}
