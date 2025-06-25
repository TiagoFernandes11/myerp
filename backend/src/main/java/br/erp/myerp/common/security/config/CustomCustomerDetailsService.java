package br.erp.myerp.common.security.config;

import br.erp.myerp.common.security.client.CustomerAccountClient;
import br.erp.myerp.common.security.dto.customeraccount.CustomerAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomCustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerAccountClient customerAccountClient;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerAccountDTO customer = customerAccountClient.findByUsername(username);
        if(customer == null){
            throw new UsernameNotFoundException("User with username: " + username + " was not found");
        }
        String user = customer.getUsername();
        String password = customer.getPassword();
        List<GrantedAuthority> grantedAuthority = List.of(new SimpleGrantedAuthority(customer.getRole().toString()));
        return new User(user, password, grantedAuthority);
    }
}
