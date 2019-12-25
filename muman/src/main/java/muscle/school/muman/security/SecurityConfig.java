//package muscle.school.muman.security;
//
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.header.writers.StaticHeadersWriter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN");
//        http.formLogin();
//        http.exceptionHandling().accessDeniedPage("/accessDenied");
//        http.logout().logoutUrl("/logout").invalidateHttpSession(true);
//    }
//}