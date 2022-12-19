package ch.zhaw.application_crafty.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.application_crafty.model.Customer;
import ch.zhaw.application_crafty.model.CustomerCreateDTO;
import ch.zhaw.application_crafty.repository.CustomerRepository;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    
    
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer( 
        @RequestBody CustomerCreateDTO cDTO) {
            Customer cDAO = new Customer(cDTO.getEmail(), cDTO.getName());
            Customer c = customerRepository.save(cDAO);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }
        @GetMapping("")
    public ResponseEntity<Page<Customer>> getAllFreelancer(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 2;
        }
        Page<Customer> allFreelancers = customerRepository.findAll(PageRequest.of(page - 1, pageSize));

        return new ResponseEntity<>(allFreelancers, HttpStatus.OK);
    }
        @GetMapping("/{id}")
        public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
            Optional<Customer> optCustomer = customerRepository.findById(id);
            if (optCustomer.isPresent()){
                return new ResponseEntity<>(optCustomer.get(), HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    } 
