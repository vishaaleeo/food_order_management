package swiggy.controller;

//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.json.Json;
import swiggy.domain.AuthUser;
import swiggy.domain.User;
import swiggy.services.UserDAOImplementaion;

import java.security.Principal;
import java.util.Date;


/*
 *To control and regulate the paths
 */
@CrossOrigin(origins="192.168.0.105:3000")
@Controller
public class UserController {

    @Autowired
    UserDAOImplementaion userDAOImplementaion;



  /*  @GetMapping("/signup")
    public ModelAndView getSignup() {
        return new ModelAndView("signup");
    }*/

    @PostMapping("/signup")
   // @SendTo("/topic/user")
    public ResponseEntity createUser(@RequestBody User user) {

        String result=userDAOImplementaion.createUser(user);
        if(result.equals("success")) {

            return new ResponseEntity("User created successfully",HttpStatus.valueOf(201));
        }

        else {
            return new ResponseEntity(result,HttpStatus.valueOf(400));
        }
    }


    @PostMapping(value="/login" , produces = MediaType.APPLICATION_JSON_VALUE)
   // @SendTo("/topic/user")
    public ResponseEntity login(@RequestBody User user) {

        System.out.println(user.toString());
        User loginUser = userDAOImplementaion.loginUser(user);
        if(loginUser!=null) {

            Claims claims = Jwts.claims()
                    .setSubject(loginUser.getUserIdentifier().toString());

            String jwtToken=Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256,"secret")
                    .compact();
            //String jwtToken = Jwts.builder().setSubject(loginUser.getUserIdentifier().toString()).claim("roles", "user").setIssuedAt(new Date())
               //     .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            String jsonString="{ \"token\" : \""+jwtToken+"\"}";
            //JSONObject jsonObject = new JSONObject();
            //jsonObject.put("token",jwtToken);
            //System.out.println(jsonObject.toString());

            return new ResponseEntity(jsonString, HttpStatus.valueOf(200));
            //System.out.println(loginUser.toString());
            //return new Response(loginUser.toString());
        }
        else {
            //return new Response("invalid user login");
            return new ResponseEntity("invalid user login",HttpStatus.valueOf(200));
        }
    }

   /* @PostMapping("/user/login")
    public ResponseEntity postlogin(@RequestBody User user){

        System.out.println("login");
        AuthUser principal=(AuthUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity("login "+principal.getUserIdentifier(),HttpStatus.OK);
    }
*/
    @PostMapping ("/update")
    public ResponseEntity updateUser(@RequestBody User user) {

    String result=userDAOImplementaion.updateUser(user);
    return new ResponseEntity(result,HttpStatus.valueOf(200));
    }




    @PostMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody User user) {

       String result= userDAOImplementaion.deleteUser(user);

       return new ResponseEntity(result,HttpStatus.valueOf(200));
    }



}
