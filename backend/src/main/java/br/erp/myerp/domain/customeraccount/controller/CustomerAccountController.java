package br.erp.myerp.domain.customeraccount.controller;


import br.erp.myerp.domain.customeraccount.dto.CustomerAccountDTO;
import br.erp.myerp.domain.customeraccount.entity.CustomerAccount;
import br.erp.myerp.domain.customeraccount.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/{username}")
    public ResponseEntity<CustomerAccountDTO> findById(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(customerAccountService.findByUsername(username));
    }
}
