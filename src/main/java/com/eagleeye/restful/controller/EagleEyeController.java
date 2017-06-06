package com.eagleeye.restful.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.eagleeye.restful.model.SlotBooking;
import com.eagleeye.restful.service.AddMenu;
import com.eagleeye.restful.service.AddRole;
import com.eagleeye.restful.service.Customerservice;
import com.eagleeye.restful.service.DAOService;
import com.eagleeye.restful.service.EmployeeService;
import com.eagleeye.restful.service.GroundService;
import com.eagleeye.restful.service.MasterGroundService;
import com.eagleeye.restful.service.PaymentService;
import com.eagleeye.restful.service.SlotBookingService;
import com.eagleeye.restful.service.SlotService;
import com.eagleeye.restful.model.User;
import com.eagleeye.restful.service.UserService;
import com.eagleeye.restful.web.BookSlotStatus;
import com.eagleeye.restful.web.MasterGroundFinal;
import com.eagleeye.restful.web.PaymentConfirmation;
import com.eagleeye.restful.web.RoleFinal;
import com.eagleeye.restful.web.SlotFinalRequest;
import com.eagleeye.restful.web.SlotFinalRespnse;
import com.eagleeye.restful.web.SlotRequest;
import com.eagleeye.restful.web.UserFinal;
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
	
	@Autowired
	SlotBookingService slotBookingService;
	
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
	public ResponseEntity<UserFinal> addUser(@RequestBody User user) {
		//To do change for hardcode value
		user.setPassword("eagleeye");
		userService.save(user);
		logger.debug("Added User sucessfully:: " + user);
		UserFinal userList = new UserFinal();
		userList.setUserList(userService.getAll());
		return new ResponseEntity<UserFinal>(userList, HttpStatus.CREATED);
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
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			logger.debug("users does not exists");
			//return new ResponseEntity<Menu>(HttpStatus.NO_CONTENT);
		}
		logger.debug("Found " + users.size() + " Employees");
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
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
	public ResponseEntity<RoleFinal> addRole(@RequestBody Role role) {
		RoleFinal roleList = new RoleFinal();
		roleService.save(role);
		logger.debug("Added:: " + role);
		 roleList.setRoleList(roleService.getAll());
		
		
		return new ResponseEntity<RoleFinal>(roleList, HttpStatus.OK);
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
	public ResponseEntity<MasterGroundFinal> addMasterGround(@RequestBody MasterGround masterGround) {
		masterGroundService.save(masterGround);
		logger.debug("Added:: " + masterGround);
		Set<Ground> grounds=masterGround.getGrounds();
		for(Ground subGround : grounds){
			subGround.setMasterGround(masterGround);
			groundService.save(subGround);
		}
		MasterGroundFinal masterGroundList = new MasterGroundFinal();
		
		
		 masterGroundList.setMastergroundList(masterGroundService.getAll());
		return new ResponseEntity<MasterGroundFinal>(masterGroundList, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/getGroundByCity/{city}", method=RequestMethod.GET)
	public ResponseEntity<MasterGroundFinal> getGroundByPlace(@PathVariable("city") String city){
		
		
		MasterGroundFinal masterGroundList = new MasterGroundFinal();
		
		 masterGroundList.setMastergroundList(groundDao.getGroundByCity(city));
		
		
		return new ResponseEntity<MasterGroundFinal>(masterGroundList, HttpStatus.OK);
		
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
	public ResponseEntity<SlotFinalRespnse> getSlotBooking(@RequestBody SlotRequest slotRequest){
		//String date=slotRequest.getBookDate();
		
		SlotFinalRespnse finalResponse = daoService.getSlotBooking(slotRequest);
		return new ResponseEntity<SlotFinalRespnse>( finalResponse,HttpStatus.OK);
		
	}	
	
	//.........................Customer controller........................//
	
	@RequestMapping(value="/addCustomer",method=RequestMethod.POST)
	public ResponseEntity<PaymentConfirmation> addCustomer(@RequestBody CustomerBooking customer){
		
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
	CustomerBooking customerBooking = customerService.save(customer);
		logger.debug("Added:: " + customer);
		
		List<CustomerPayment> arr=customer.getCustomerPayment();
		
		for(CustomerPayment payment : arr){
			payment.setCustomerBooking(customer);
			paymentService.save(payment);
		}
		
		logger.debug("Retriving total booking count..");
		List<Integer> bookedIds= new ArrayList();
				String place=null;
				Date dateBook =null;
				int totalBookings=customer.getSlotBooking().size();
				List slotIds = new ArrayList();
				List groundNames = new ArrayList();
				for(int i=0;i<totalBookings;i++){
					bookedIds.add(customer.getSlotBooking().get(i).getBook_id());
					Ground ground = groundService.getById(new Long(customer.getSlotBooking().get(i).getGroundId()));
					groundNames.add(ground.getGroundName());
					slotIds.add(customer.getSlotBooking().get(i).getSlotId());
					if(i==0)
					{
						place = ground.getMasterGround().getMasterGroundName();
						 dateBook =customer.getSlotBooking().get(i).getDate();
					}
				}
		
	    logger.debug("confirming the slot for the bookings.. ");
		daoService.updateSlotStatus(bookedIds);
		PaymentConfirmation paymentConfirmation = new PaymentConfirmation();
		paymentConfirmation.setReferenceNumber("Eagleeye"+customerBooking.getBookingReference());
		paymentConfirmation.setPlace(place);
		paymentConfirmation.setGrounds(groundNames);
		paymentConfirmation.setSlots(slotIds);
		paymentConfirmation.setBookedDate(dateBook);
		return new ResponseEntity<PaymentConfirmation>(paymentConfirmation,HttpStatus.OK);
	}
	;
	
	
	
	@RequestMapping(value="/getBookedCustomer",method=RequestMethod.POST)
	public ResponseEntity<BookedCustomer> getBookedCustomer(@RequestBody CustomerBooking customerDetails){
		int bookingRef=0;
		BookedCustomer bookedCustomer = new BookedCustomer();
		CustomerBooking customer=new CustomerBooking();
		List<Slot> slots = new ArrayList();
		
		if(customerDetails.getBookingReference()!=0){
			bookingRef=customerDetails.getBookingReference();
			logger.debug("Customer booking id fetched" + bookingRef );
			 
		}
		
		if(customerDetails.getMobileNumber()!=0){
			bookingRef=daoService.getCustomerByMobile(customer.getMobileNumber());
			logger.debug("Customer booking id fetched" + bookingRef );
		}
		
		customer = customerService.getById(bookingRef);
		logger.debug("Customer object fetched" );
		 if (customer == null) {
				logger.debug("Customer with id " + bookingRef + "  does not exists");
				return new ResponseEntity<BookedCustomer>(HttpStatus.NOT_FOUND);
			}
		
		int totalBookings=customer.getSlotBooking().size();
		List<Integer> bookIds = new ArrayList();
		for(int i=0;i<totalBookings;i++){
			bookIds.add(customer.getSlotBooking().get(i).getBook_id());
		}
  
		
		List<SlotBooking> bookings = new ArrayList();
		logger.debug("inside slot booking loop" );
		for(int i=0;i<bookIds.size();i++){
		bookings.add(slotBookingService.getById(bookIds.get(i)));
		}
		logger.debug("after slot booking loop" );
		for(int j=0;j<bookings.size();j++){
			//slots.add(bookings.get(j).getSlot());
		}
			
		logger.debug("after slot  loop" );
		bookedCustomer.setBookingReference(bookingRef);
		bookedCustomer.setName(customer.getCustomerName());
		bookedCustomer.setMobileNo(customer.getMobileNumber());
		bookedCustomer.setPendingTransaction(customer.getPendingTransaction());
		bookedCustomer.setTransactionStatus(customer.getTransactionStatus());
		bookedCustomer.setBookings(bookings);
		//bookedCustomer.setSlots(slots);
		
		
		return new ResponseEntity<BookedCustomer>(bookedCustomer,HttpStatus.OK);
	}
	
	@RequestMapping(value="/bookSlots",method=RequestMethod.POST)
	public ResponseEntity<BookSlotStatus> bookSlots(@RequestBody SlotFinalRequest slotFinalRequest){
		logger.debug("Book Slots Entered..");
		List<SlotBooking> bookSlots=slotFinalRequest.getBookslots();
		List<SlotBooking> bookedSlots = new ArrayList<SlotBooking>();
		BookSlotStatus bookSlotStatus = new BookSlotStatus();
		try
		{
		
		for(SlotBooking slotBooking : bookSlots){
			
			slotBooking.setStatus("Partial");
			SlotBooking bookedSlot= slotBookingService.save(slotBooking);
			bookedSlots.add(bookedSlot);
		}
		bookSlotStatus.setBookedSlots(bookedSlots);
		bookSlotStatus.setBookStatus("Success");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			bookSlotStatus.setBookStatus("Failure");
			
		}
		return new ResponseEntity<BookSlotStatus>(bookSlotStatus,HttpStatus.OK);
	}
	}
	
	
	
	
	
	
	
	

