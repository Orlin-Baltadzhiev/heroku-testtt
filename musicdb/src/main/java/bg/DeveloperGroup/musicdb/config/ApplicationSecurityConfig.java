package bg.DeveloperGroup.musicdb.config;

import bg.DeveloperGroup.musicdb.Service.impl.MusicDbUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MusicDbUserService musicDbUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(MusicDbUserService musicDbUserService, PasswordEncoder passwordEncoder){
        this.musicDbUserService = musicDbUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                    .authorizeRequests ()
                    .requestMatchers (PathRequest.toStaticResources ().atCommonLocations ()).permitAll ()
                    //.antMatchers ("/js/**", "/css/**", "/images/**").permitAll ()
                    .antMatchers ("/", "/users/login", "/users/register").permitAll ()
                    .antMatchers("/articles/add").hasRole("ADMIN")
                    .antMatchers ("/**").authenticated ()
                .and ()
                    .formLogin ()
                    .loginPage ("/users/login").permitAll ()
                    .usernameParameter (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    .passwordParameter (UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                    .defaultSuccessUrl ("/home")
                    .failureForwardUrl ("/users/login-error")
                .and ()
                    .logout ()
                    .logoutUrl ("/logout")
                    .logoutSuccessUrl ("/")
                    .invalidateHttpSession (true) //remove the session from the server
                    .deleteCookies ("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService (musicDbUserService)
                .passwordEncoder (passwordEncoder);

    }
}
