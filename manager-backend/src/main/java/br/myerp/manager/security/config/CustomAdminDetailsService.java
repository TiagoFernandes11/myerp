package br.myerp.manager.security.config;

import br.myerp.manager.security.client.AdminClient;
import br.myerp.manager.security.client.CustomerAccountClient;
import br.myerp.manager.security.dto.admin.AdminDTO;
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
