package swiggy.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import swiggy.domain.AuthUser;
import swiggy.domain.AuthenticationToken;
import swiggy.domain.User;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {


        AuthenticationToken token=(AuthenticationToken) usernamePasswordAuthenticationToken;

        String userToken=token.getToken();

        User authuser=validate(userToken);


        if(authuser==null)
        {
            System.out.print("invalid token");
        }

        return new AuthUser(authuser.getUserIdentifier(),userToken);


    }

    public User validate(String token) {

        User user=null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();

            user=new User();
            user.setUserIdentifier(new Integer(body.getSubject()));
            //System.out.print(user.toString());
            return user;
        }
        catch(Exception e)
        {
            System.out.print(e);
            return user;
        }



    }


}
