//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class YourUserDetailsService implements UserDetailsService {
//
//    // Your implementation of loadUserByUsername that retrieves user details from your database
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Retrieve user details including ID from your database
//        // ...
//
//        // Create YourUserDetails instance
//        YourUserDetails userDetails = new YourUserDetails(username, user.getPassword(), user.getAuthorities(), user.getId());
//
//        // Set it as the principal in the Authentication object
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return userDetails;
//    }
//}
