package begin.flywayspringmaven.config.jwt;

import begin.flywayspringmaven.config.security.UserPrincipal;
import begin.flywayspringmaven.util.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        UserDetails userDetails = new UserPrincipal();
        try {
            String jwt = getJwtToken(request);
            if (jwt != null && this.tokenProvider.validateJwtToken(jwt)){
                String username = String.valueOf(this.tokenProvider.getClaimInfo(jwt, "userId"));
                userDetails = this.userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            return;
        }
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;
        response.setBufferSize(64*1024);
        filterChain.doFilter(request, response);
    }

    private String getJwtToken(HttpServletRequest request) {
        String authHeader = request.getHeader(Constants.AUTHORIZATION_HEADER);
        if (authHeader != null && authHeader.startsWith(Constants.JWT_TOKEN_TYPE)) {
            return authHeader.replace(Constants.JWT_TOKEN_TYPE, "");
        }
        return null;
    }

}
