package br.erp.myerp.domain.customer.customeraccount.service;

import br.erp.myerp.domain.customer.client.dto.ClientCreateDTO;
import br.erp.myerp.domain.customer.client.dto.ClientResponseDTO;
import br.erp.myerp.domain.customer.client.entity.Client;
import br.erp.myerp.domain.customer.client.service.ClientService;
import br.erp.myerp.domain.customer.customeraccount.constants.Role;
import br.erp.myerp.domain.customer.customeraccount.dto.CustomerAccountCreateDTO;
import br.erp.myerp.domain.customer.customeraccount.dto.CustomerCreateDTO;
import br.erp.myerp.domain.customer.customeraccount.dto.CustomerAccountDTO;
import br.erp.myerp.domain.customer.customeraccount.entity.CustomerAccount;
import br.erp.myerp.domain.customer.customeraccount.mapper.CustomerAccountMapper;
import br.erp.myerp.domain.customer.customeraccount.repository.CustomerAccountRepository;
import br.erp.myerp.domain.customer.customeraccount.specification.CustomerAccountSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<CustomerAccount> findAll(String filter, String value, int pageNum, int pageSize) {
        return customerAccountRepository.findAll(CustomerAccountSpecification.genericFilter(filter, value), PageRequest.of(pageNum, pageSize)).toList();
    }

    public CustomerAccountDTO findByUsername(String username) {
        CustomerAccount customerAccount = customerAccountRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Customer not found with username: " + username));
        return customerAccountMapper.toCustomerAccountDTO(customerAccount);
    }

    public void register(CustomerCreateDTO customerDTO) {
        ClientCreateDTO client = new ClientCreateDTO(customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(),
                customerDTO.getDdd(), customerDTO.getCellphone(), customerDTO.getBirthday(), customerDTO.getCpf());
        Client clientResponse = clientService.create(client);

        CustomerAccountCreateDTO customerAccountCreateDTO = new CustomerAccountCreateDTO(clientResponse.getId(), customerDTO.getEmail(), passwordEncoder.encode(customerDTO.getPassword()));
        CustomerAccount customerAccount = customerAccountMapper.toCustomerAccount(customerAccountCreateDTO);
        customerAccount.setRole(Role.ROLE_CLIENT);
        customerAccountRepository.save(customerAccount);
    }
}
