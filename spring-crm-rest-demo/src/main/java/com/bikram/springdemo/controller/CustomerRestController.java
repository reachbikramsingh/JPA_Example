package com.bikram.springdemo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bikram.springdemo.service.CustomerService;
import com.bikram.springdemo.entity.Customer;
import com.bikram.springdemo.exception.CustomerNotFoundException;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();

	}

	@GetMapping("/customers/{customerId}")
	public Customer getStudent(@PathVariable int customerId) throws Exception {
		Customer theCustomer = customerService.getCustomer(customerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("customer id not found::" + customerId);
		}
		return theCustomer;
	}

	@PostMapping("customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);

		return theCustomer;

	}

	@PutMapping("customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return theCustomer;

	}

	@DeleteMapping("customers/{customerID}")
	public String deleteCustomer(@PathVariable int customerID) {
		customerService.deleteCustomer(customerID);

		Customer tempCustomer = customerService.getCustomer(customerID);
		
		if (tempCustomer == null) {
			try {
				throw new CustomerNotFoundException("invalid customerId::" + customerID);
			} catch (CustomerNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		customerService.deleteCustomer(customerID);
		return "deleted id is::" + customerID;

	}

	// @DeleteMapping("customers/{customerID}")
	// public int deleteCustomer(@PathVariable int customerID) throws
	// CustomerNotFoundException {
	// customerService.deleteCustomer(customerID);
	
	// return customerID;
	//
	// }

}
