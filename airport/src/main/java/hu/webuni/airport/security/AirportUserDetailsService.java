//package hu.webuni.airport.security;
//
//
//import hu.webuni.airport.model.AirportUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
//@Service
//public class AirportUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserReposirtory userReposirtory;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AirportUser airportUser = userReposirtory.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
//
//        return new User(username, airportUser.getPassword(), airportUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//    }
//}
