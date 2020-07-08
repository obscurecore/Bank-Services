package bank.service.security.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The type Spring security config.
 *
 * @author Ruslan Potapov
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("UserDetailServiceImpl")
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        /* Simple Hash-Based token
        In addition, TokenBasedRememberMeServices requires
         A UserDetailsService from which it can retrieve the username and password for signature comparison purposes,
            and generate the RememberMeAuthenticationToken to contain the correct GrantedAuthority[]s
        */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().rememberMe()
                .rememberMeParameter("remember-me")
                .key("secretkey")
                .tokenValiditySeconds(86400);

        http.authorizeRequests()
                .antMatchers("/signUp", "/activate/*").permitAll()
                .antMatchers("/users").authenticated()
                .antMatchers("/").authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/signUp")
                .usernameParameter("email")
                .failureUrl("/login?error")
                .permitAll();

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signIn")
                .deleteCookies("remember-me");
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
