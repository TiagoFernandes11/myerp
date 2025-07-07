package br.myerp.store_backend.security.config;

import br.myerp.store_backend.customeraccount.dto.customeraccount.CustomerAccountResponseDTO;
import br.myerp.store_backend.customeraccount.service.CustomerAccountService;
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
public class CustomAdminDetailsService implements UserDetailsService {


    @Autowired
    private CustomerAccountService customerAccountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerAccountResponseDTO customer = customerAccountService.findByUsername(email);

        String user = customer.getEmail();
        String password = customer.getPassword();
        List<GrantedAuthority> grantedAuthority = List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));


        return new User(user, password, grantedAuthority);
    }
}
