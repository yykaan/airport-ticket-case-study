package com.kaan.finartz.airportticket.controller;

import com.kaan.finartz.airportticket.dto.customer.CustomerDto;
import com.kaan.finartz.airportticket.entity.Customer;
import com.kaan.finartz.airportticket.exception.ObjectNotFoundException;
import com.kaan.finartz.airportticket.mapper.CustomerMapper;
import com.kaan.finartz.airportticket.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController extends AbstractController{
    
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@RequestBody @Valid CustomerDto flightDto) {
        Customer savedCustomer = customerService.saveAndUpdate(customerMapper.toEntity(flightDto));
        return new ResponseEntity<>(customerMapper.toDto(savedCustomer), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerDto flightDto, @PathVariable Long id) throws ObjectNotFoundException {
        if (customerService.existsById(id)){
            Customer savedCustomer = customerService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Customer could not be found!"));
            if (customerMapper.toEntity(flightDto).equals(savedCustomer)){
                return new ResponseEntity<>(customerMapper.toDto(savedCustomer), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(customerMapper.toDto(customerService.saveAndUpdate(customerMapper.toEntity(flightDto))), HttpStatus.OK);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<CustomerDto>> findAll(){
        List<Customer> flightList = (List<Customer>) customerService.findAll();
        if (flightList.isEmpty()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
                flightList.stream()
                        .map(customerMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id){
        if (customerService.existsById(id)){
            return new ResponseEntity<>(customerMapper.toDto(customerService.findById(id).orElseThrow(() -> new ObjectNotFoundException("Customer could not be found!"))), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if (customerService.existsById(id)){
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
