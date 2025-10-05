package backend.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // kertoo, että käytetään bcryptpasswordencoderia
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //käytetään kun luodaan uusi käyttäjä ja luo salasanan
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/home", "/css/**", "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/books/**").hasAuthority("ADMIN") // Tämä myös tarkistaa Controllerin ja booklist-templaten lisäksi, että onko käyttäjällä oikeus. Voi tehdä saman tarkistuksen put, post ja edit -pyynnöille/endpointeille
                .requestMatchers(HttpMethod.POST, "/books/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/books/**").hasAuthority("ADMIN")
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()) // Tämän avulla postmanin puolella pystytään tekemään login ja pystytään testaamaan toimiiko rest-rajapinta
                .formLogin(formlogin -> formlogin
                        .defaultSuccessUrl("/booklist", true)
                        .permitAll())
                .logout(logout -> logout
                        .permitAll())
                .csrf(csrf -> csrf.disable()); // Tämän tekeminen on isompi homma, joten otettu vain pois päältä
        return http.build();
    }

    // Näitä ei tarvita enää ja koodi on ylempänä korjattu. 

    // private UserDetailsService userDetailsService;

    // public WebSecurityConfig(UserDetailsService userDetailsService) {
    //     this.userDetailsService = userDetailsService;
    // }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    // }
}
