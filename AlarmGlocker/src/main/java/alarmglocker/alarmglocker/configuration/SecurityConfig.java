package alarmglocker.alarmglocker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/alarmGlocker/user/create").permitAll() // Erlaube Zugriff auf den Create-Endpunkt
//                        .requestMatchers("/alarmGlocker/user").permitAll() // Erlaube Zugriff auf den User-Endpunkt
//                        .anyRequest().authenticated() // Alle anderen Endpunkte erfordern Authentifizierung
//                )
//                .formLogin(Customizer.withDefaults()) // Verwende die Standard-Formular-Anmeldung
//                .logout(Customizer.withDefaults()); // Verwende die Standard-Logout-Einstellungen
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll() // Erlaubt den Zugriff auf **alle** Endpunkte ohne Authentifizierung
                )
                .formLogin(Customizer.withDefaults()) // Optional: Standard-Login-Seite (wird nicht benötigt, wenn alles öffentlich ist)
                .logout(Customizer.withDefaults());   // Optional: Standard-Logout-Verhalten
        return http.build();
    }

}
