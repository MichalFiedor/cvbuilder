package pl.michalfiedor.cvbuilder.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/dashboard/*").hasRole("USER")
                .antMatchers("/basicdata/*").hasRole("USER")
                .antMatchers("/aboutme/*").hasRole("USER")
                .antMatchers("/experience/*").hasRole("USER")
                .antMatchers("/education/*").hasRole("USER")
                .antMatchers("/image/*").hasRole("USER")
                .antMatchers("/pdf/*").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard/show", true)
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
