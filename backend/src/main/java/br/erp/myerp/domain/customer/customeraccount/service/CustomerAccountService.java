package br.erp.myerp.domain.customer.customeraccount.service;

import br.erp.myerp.domain.customer.customeraccount.dto.CustomerAccountDTO;
import br.erp.myerp.domain.customer.customeraccount.entity.CustomerAccount;
import br.erp.myerp.domain.customer.customeraccount.mapper.CustomerAccountMapper;
import br.erp.myerp.domain.customer.customeraccount.repository.CustomerAccountRepository;
import br.erp.myerp.domain.customer.customeraccount.specification.CustomerAccountSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    public List<CustomerAccount> findAll(String filter, String value, int pageNum, int pageSize){
        return customerAccountRepository.findAll(CustomerAccountSpecification.genericFilter(filter, value), PageRequest.of(pageNum, pageSize)).toList();
    }

    public CustomerAccountDTO findByUsername(String username){
        CustomerAccount customerAccount =  customerAccountRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Customer not found with username: " + username));
        return customerAccountMapper.toCustomerAccountDTO(customerAccount);
    }
}
