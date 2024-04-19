package begin.flywayspringmaven.config.jwt;

import begin.flywayspringmaven.common.model.User;
import begin.flywayspringmaven.config.security.UserPrincipal;
import begin.flywayspringmaven.enums.TokenEnum;
import begin.flywayspringmaven.exception.BadRequestException;
import begin.flywayspringmaven.exception.NotFoundException;
import begin.flywayspringmaven.util.constants.APIConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public boolean validateJwtToken(String authToken) throws ServletException {
        try {
            if(isTokenExpired(authToken)) {
                throw new ServletException("Expired JWT token");
            }
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            throw new ServletException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new ServletException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new ServletException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new ServletException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new ServletException("JWT claims string is empty");
        }
    }

    /**
     * Is Token Expired
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    public AccessToken createAccessToken(Authentication authentication, boolean rememberMe, boolean isCheckLogin) {
        UserPrincipal principal = new UserPrincipal();
        if(isCheckLogin) {
            principal = (UserPrincipal) authentication.getPrincipal();
        } else {
            User user = (User) authentication.getPrincipal();
            principal = new UserPrincipal(user);
        }
        String name = String.valueOf(principal.getUserId());

        long now = (new Date()).getTime();
        long dateToMilliseconds = 24*60*60*1000;
        Date validity;
        Date refreshTokenExpiration = new Date(now + TokenEnum.REFRESH_TOKEN_EXPIRED.getValue() * dateToMilliseconds);
        if (rememberMe) {
            validity = new Date(now + TokenEnum.TOKEN_REMEMBER_ME_EXPIRED.getValue() * dateToMilliseconds);
        } else {
            validity = new Date(now + TokenEnum.TOKEN_JWT_EXPIRED.getValue() * dateToMilliseconds);
        }

        //Build access token
        String jwt = Jwts.builder().setSubject(name)
                .setClaims(buildClaims(principal))
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
        //Build refresh token
        String refreshToken = Jwts.builder().setSubject(name)
                .setExpiration(refreshTokenExpiration)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
                .compact();
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(jwt);
        accessToken.setExpired(validity);
        accessToken.setRefreshToken(refreshToken);

        return accessToken;
    }

    /**
     * Get Claim info from access token value.
     *
     * @param token the access token
     * @param claimKey Claim key
     * @return Claims info for claimKey
     * @throws NotFoundException
     */
    public Object getClaimInfo(String token, String claimKey) throws NotFoundException {
        Claims claims = Jwts.parser().
                setSigningKey(this.jwtSecret).
                parseClaimsJws(token).
                getBody();
        if (claims.get(claimKey) == null) {
            throw new NotFoundException(BadRequestException.ERROR_INVALID_TOKEN, APIConstants.TOKEN_INVALID_MSG);
        }
        return claims.get(claimKey);
    }

    /**
     * build Claims
     *
     * @param userPrincipal User Principal
     * @return
     */
    private Map<String, Object> buildClaims(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userId", userPrincipal.getUserId());
        return claims;
    }
}
