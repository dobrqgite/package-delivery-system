package com.example.package_delivery_system.common;

import com.example.package_delivery_system.data.repositories.AddressRepository;
import com.example.package_delivery_system.data.repositories.RoleRepository;
import com.example.package_delivery_system.data.repositories.UserRepository;
import com.example.package_delivery_system.services.AddressService;
import com.example.package_delivery_system.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;

    @Autowired
    public WebSecurityConfig(UserRepository userRepository, PasswordEncoder passwordEncoder,
                             RoleRepository roleRepository, AddressService addressService, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.addressService = addressService;

        this.addressRepository = addressRepository;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl(userRepository, passwordEncoder, roleRepository, addressService, addressRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO: ADD ANT MATCHER TO HANDLE ADMIN VIEW(ADMIN SHOULD SEE EVERYTHING)
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/").permitAll()
                .antMatchers("/user/signup", "/user/login").anonymous()
                .antMatchers("/user/edit-profile", "/user/user-index", "/user/profile", "/user/gateway").hasAnyAuthority("CUSTOMER", "ADMIN")
                .antMatchers("/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/gateway")
                .and()
                .logout().logoutUrl("/user/logout").logoutSuccessUrl("/").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403.html");
    }
}