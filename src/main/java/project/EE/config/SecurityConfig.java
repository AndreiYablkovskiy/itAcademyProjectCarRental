package project.EE.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import project.EE.model.security.Encoder;
import project.EE.service.UserService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final Encoder encoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/users/login","/users/registration/").not().fullyAuthenticated()
//                .antMatchers("users/logout", "users/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/cars/**").authenticated()
                .antMatchers("/users/**").hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated();
               .and()
              .formLogin();
//                .defaultSuccessUrl("/users/success")
//                .loginPage("/users/login").permitAll();
//                .loginProcessingUrl("/users/login");
//               .and()
//                .logout().permitAll()
//                .logoutSuccessUrl("/");

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(encoder.bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
