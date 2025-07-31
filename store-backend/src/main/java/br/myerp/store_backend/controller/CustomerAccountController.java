package br.myerp.store_backend.controller;


import br.myerp.store_backend.dto.customeraccount.CustomerAccountResponseDTO;
import br.myerp.store_backend.dto.customeraccount.CustomerCreateDTO;
import br.myerp.store_backend.entity.CustomerAccount;
import br.myerp.store_backend.service.CustomerAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/customer-account")
public class CustomerAccountController {

    private final int PAGE_SIZE = 20;

    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping
    public ResponseEntity<List<CustomerAccount>> findAll(@RequestParam(name = "filter", required = false, defaultValue = "") String filter,
                                                         @RequestParam(name = "value", required = false, defaultValue = "") String value,
                                                         @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
        return ResponseEntity.status(HttpStatus.OK).body(customerAccountService.findAll(filter, value, pageNum, PAGE_SIZE));
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<CustomerAccountResponseDTO> findById(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(customerAccountService.findByUsername(email));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@Valid @RequestBody CustomerCreateDTO customer){
        int code = customerAccountService.register(customer);
        if(code == 403){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PutMapping("{id}")
//    public ResponseEntity<String> updateAccount(@Valid @RequestBody CustomerUpdateDTO customerUpdateDTO){
//        customerAccountService.update(customerUpdateDTO);
//        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
//    }
}
