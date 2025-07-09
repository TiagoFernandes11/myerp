package br.myerp.store_backend.customeraccount.service;

import br.myerp.store_backend.client.ClientClient;
import br.myerp.store_backend.customeraccount.constants.Role;
import br.myerp.store_backend.customeraccount.dto.client.ClientCreateDTO;
import br.myerp.store_backend.customeraccount.dto.client.ClientResponseDTO;
import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerAccountCreateDTO;
import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerAccountResponseDTO;
import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerCreateDTO;
import br.myerp.store_backend.customeraccount.entity.CustomerAccount;
import br.myerp.store_backend.customeraccount.mapper.CustomerAccountMapper;
import br.myerp.store_backend.customeraccount.repository.CustomerAccountRepository;
import br.myerp.store_backend.customeraccount.specification.CustomerAccountSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAccountService {

    @Autowired
    private ClientClient clientClient;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private CustomerAccountMapper customerAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<CustomerAccount> findAll(String filter, String value, int pageNum, int pageSize) {
        return customerAccountRepository.findAll(CustomerAccountSpecification.genericFilter(filter, value), PageRequest.of(pageNum, pageSize)).toList();
    }

    public CustomerAccountResponseDTO findByUsername(String email) {
        CustomerAccount customerAccount = customerAccountRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Customer not found with email: " + email));
        return customerAccountMapper.toCustomerAccountDTO(customerAccount);
    }

    public int register(CustomerCreateDTO customerDTO) {
        ClientCreateDTO client = new ClientCreateDTO(customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail(),
                customerDTO.getDdd(), customerDTO.getCellphone(), customerDTO.getBirthday(), customerDTO.getCpf());
        ClientResponseDTO clientResponse = clientClient.create(client);

        if(clientResponse == null) return 403;

        CustomerAccountCreateDTO customerAccountCreateDTO = new CustomerAccountCreateDTO(clientResponse.getId(), customerDTO.getEmail(), passwordEncoder.encode(customerDTO.getPassword()));
        CustomerAccount customerAccount = customerAccountMapper.toCustomerAccount(customerAccountCreateDTO);
        customerAccount.setRole(Role.ROLE_CLIENT);
        customerAccountRepository.save(customerAccount);
        return 201;
    }

//    public void update()
}
