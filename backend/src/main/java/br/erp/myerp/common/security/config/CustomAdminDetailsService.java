package br.erp.myerp.common.security.config;

import br.erp.myerp.common.security.client.AdminClient;
import br.erp.myerp.common.security.client.CustomerAccountClient;
import br.erp.myerp.common.security.dto.admin.AdminDTO;
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
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminClient adminClient;

    @Autowired
    private CustomerAccountClient customerAccountClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AdminDTO admin = adminClient.getAdmin(email);

        String user = admin.getUsername();
        String password = admin.getPassword();
        List<GrantedAuthority> grantedAuthority = List.of(new SimpleGrantedAuthority(admin.getRole().toString()));

        return new User(user, password, grantedAuthority);
    }
}
