/*import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import swiggy.Application;
import swiggy.domain.User;
import swiggy.services.UserDAOImplementaion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)

public class ApplicationTest {




    @Autowired
    UserDAOImplementaion userDAOImplementaion;

    User user;
    @Before
    public void createTestUser()
    {
        user=new User();
        user.setUserName("xxx");
        user.setUserPass("xxxx");
        user.setUserType("admin");
        user.setEmail("xx@gmail.com");
    }

    @Test
    public void testCreateAndLogin() {

        //String result="";
        //User user1=null;
        //String result="";
       // when(userDAOImplementaion.createUser(user)).thenReturn(result);
        //when(userDAOImplementaion.loginUser(user)).thenReturn(user1);

        System.out.println(userDAOImplementaion.createUser(user));
        User user1=userDAOImplementaion.loginUser(user);
        assertEquals(user.getEmail(),user1.getEmail());
    }

    @Test
    public void updateTest() {


        user.setUserType("normal");
        System.out.print(userDAOImplementaion.updateUser(user));
        User user1=userDAOImplementaion.loginUser(user);
        System.out.print(user1.toString());
        assertEquals(user1.getUserType(),user.getUserType());
    }

    @Test
    public void deleteTest() {

        userDAOImplementaion.deleteUser(user);
        assertEquals(null,userDAOImplementaion.loginUser(user));
    }
}
*/