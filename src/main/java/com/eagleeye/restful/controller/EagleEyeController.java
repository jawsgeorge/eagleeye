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

import com.eagleeye.restful.model.BookedCustomer;
import com.eagleeye.restful.model.ConstructSlotAndGround;
import com.eagleeye.restful.model.CustomerBooking;
import com.eagleeye.restful.model.CustomerPayment;
import com.eagleeye.restful.model.Employee;
import com.eagleeye.restful.model.Ground;
import com.eagleeye.restful.model.GroundDao;
import com.eagleeye.restful.model.MasterGround;
import com.eagleeye.restful.model.Menu;
import com.eagleeye.restful.model.ResponseEagle;
import com.eagleeye.restful.model.Role;
import com.eagleeye.restful.model.Slot;
import com.eagleeye.restful.service.AddMenu;
import com.eagleeye.restful.service.AddRole;
import com.eagleeye.restful.service.Customerservice;
import com.eagleeye.restful.service.DAOService;
import com.eagleeye.restful.service.EmployeeService;
import com.eagleeye.restful.service.GroundService;
import com.eagleeye.restful.service.MasterGroundService;
import com.eagleeye.restful.service.PaymentService;
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
	
	@Autowired
	Customerservice customerService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	MasterGroundService masterGroundService;
	
	// Private fields
	  
	  // Wire the UserDao used inside this controller.
	  @Autowired
	  private UserDAO userDao;
	  
	 @Autowired
	 GroundDao groundDao;
	 
	 @Autowired
	 DAOService daoService;
	
	//.............................User Controller.........................//

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
		ResponseEagle res = new ResponseEagle();
		logger.debug("Added User sucessfully:: " + userObj);
		if (userObj == null) {
			logger.debug("User with name " + user.getUserName() + "  does not exists");
			
			res.setResponseCode("100");
			res.setResponseMessage("User name does not exist");
			
			
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		}
		logger.debug("Found User:: " + userObj);
		
		Object obj=userObj.getRole().getMenu();
		res.setObject(obj);
		res.setResponseCode("200");
		res.setResponseMessage("login success");
		return new ResponseEntity<Object>(res, HttpStatus.OK);
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
	
	//.........................Menu controller.....................//
	
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

	//............................. Role Controller...............//
	
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
	
	//...........................Ground Controller.....................//
	
	@RequestMapping(value="/addGround",method = RequestMethod.POST)
	public ResponseEntity<List<Ground>> addGround(@RequestBody Ground ground) {
		groundService.save(ground);
		logger.debug("Added:: " + ground);
		List<Ground> grounds = groundService.getAll();
		return new ResponseEntity<List<Ground>>(grounds, HttpStatus.CREATED);
	}
	
	
	
	
	
	
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
	
	//........................MasterGroundController..............//
	
	@RequestMapping(value="/addMasterGround",method = RequestMethod.POST)
	public ResponseEntity<List<MasterGround>> addMasterGround(@RequestBody MasterGround masterGround) {
		masterGroundService.save(masterGround);
		logger.debug("Added:: " + masterGround);
		Set<Ground> grounds=masterGround.getGrounds();
		for(Ground subGround : grounds){
			subGround.setMasterGround(masterGround);
			groundService.save(subGround);
		}
		List<MasterGround> masterGroundList = masterGroundService.getAll();
		return new ResponseEntity<List<MasterGround>>(masterGroundList, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getGroundByCity/{city}", method=RequestMethod.GET)
	public ResponseEntity<List<MasterGround>> getGroundByPlace(@PathVariable("city") String city){
		
		List<Object> objArr = new ArrayList();
		List<MasterGround> grounds=groundDao.getGroundByCity(city);
		
		
		return new ResponseEntity<List<MasterGround>>(grounds, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//........................Slot Controller.....................//
	
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
	
	//.........................Customer controller........................//
	
	@RequestMapping(value="/addCustomer",method=RequestMethod.POST)
	public void addCustomer(@RequestBody CustomerBooking customer){
		
	int paidAmount=0;	
	int totalCharge=0;
	int pendingAmount=0;
	int playerSize=	customer.getCustomerPayment().size();
	logger.debug("Total players added in the team is "+playerSize);
	
	for(int i =0;i<playerSize;i++){
		paidAmount=paidAmount+customer.getCustomerPayment().get(i).getAmount();
	}
	logger.debug("Amount paid : "+paidAmount);
	
	totalCharge=customer.getAmount();
	logger.debug("Total charge for booking  "+totalCharge);
	
	pendingAmount=totalCharge-paidAmount;
	logger.debug("Pending payment : "+pendingAmount);
	
	logger.debug("updating transaction status.. ");
	if(pendingAmount==0){
		customer.setPendingTransaction(0);
		customer.setTransactionStatus("cleared");
	}else{
		customer.setPendingTransaction(pendingAmount);
		customer.setTransactionStatus("pending");
	}
		customerService.save(customer);
		logger.debug("Added:: " + customer);
		
		List<CustomerPayment> arr=customer.getCustomerPayment();
		
		for(CustomerPayment payment : arr){
			payment.setCustomerBooking(customer);
			paymentService.save(payment);
		}
		
		logger.debug("Retriving total booking count..");
		List<Integer> bookedIds= new ArrayList();
				
				int totalBookings=customer.getSlotBooking().size();
				
				for(int i=0;i<totalBookings;i++){
					bookedIds.add(customer.getSlotBooking().get(i).getBook_id());
				}
		
	    logger.debug("confirming the slot for the bookings.. ");
		daoService.updateSlotStatus(bookedIds);
		
		
	}
	
	
	
	
//	@RequestMapping(value="/getBookedCustomer/{id}",method=RequestMethod.GET)
//	public ResponseEntity<BookedCustomer> getBookedCustomer(@PathVariable("id") int id){
//		BookedCustomer bookedCustomer = new BookedCustomer();
//		List<Integer> bookIds = new ArrayList();
//		List<Integer> slotIds = new ArrayList();
//		CustomerBooking customer = customerService.getById(id);
//		if (customer == null) {
//			logger.debug("Customer with id " + id + "  does not exists");
//			return new ResponseEntity<BookedCustomer>(HttpStatus.NOT_FOUND);
//		}
//		int totalBookings=customer.getSlotBooking().size();
//		for(int i=0;i<totalBookings;i++){
//			bookIds.add(customer.getSlotBooking().get(i).getBook_id());
//		}
//		for(int i:bookIds){
//			slotService.getById(i);
//		}
//		return new ResponseEntity<BookedCustomer>(bookedCustomer,HttpStatus.OK);
//	}
	
	}
	
	
	
	
	
	
	
	

