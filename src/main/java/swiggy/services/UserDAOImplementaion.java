package swiggy.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import swiggy.domain.User;
import swiggy.domain.UserDAO;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;



/*
 * crud applications of user
 */


@Service
public class UserDAOImplementaion implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public String createUser(User user) {

        if (verifyUser(user.getUserName())) {
            String sql = "insert into user(user_name,user_pass,email,created_time,updated_time,delete_flag,user_type) values(?,?,?,?,?,?,?)";

            if( user.getUserPass()==null || user.getUserType()==null || user.getEmail()==null|| !(user.getUserType().equals("admin") || user.getUserType().equals("normal") || user.getUserType().equals("delivery_member")||
                    user.getUserType().equals("premium")) ) {

                return "invalid";
            }

            KeyHolder keyholder = new GeneratedKeyHolder();

            user.setDeleteFlag(false);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getUserPass());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setObject(4, timestamp);
                preparedStatement.setObject(5, timestamp);
                preparedStatement.setBoolean(6, user.getDeleteFlag());
                preparedStatement.setString(7, user.getUserType());

                return preparedStatement;

            }, keyholder);

            Integer key = keyholder.getKey().intValue();
            return "success";


        } else {
            return "existing";
        }
    }



    @Override
    public String updateUser(User user) {

        if(user.getUserName()==null)
            return "Give user name";
        if(!verifyUser(user.getUserName())) {
            if (user.getUserPass() != null) {

                if (!updatePass(user))
                    return "password cannot be set";
            }

            if (user.getUserType() != null) {

                if (!updateUserType(user))
                    return "user type cannot be updated";
            }

            if (user.getEmail() != null) {

                if (!updateEmail(user))
                    return "email cannot be updated";
            }
            updateTime(user);
            return "All updated";
        }
        else {
            return "invalid user";
        }
    }



    @Override
    public String deleteUser(User user) {

        try{
            jdbcTemplate.update("update user set delete_flag = true where user_name=?",user.getUserName());
            updateTime(user);
            return "deleted";
        }
        catch(Exception e) {
            return "exception in delete"+e;
        }
    }



    @Override
    public User loginUser(User user) {
        try {
            String sql = "select * from user where user_name=? and user_pass=?";
            User loginUser = jdbcTemplate.queryForObject(sql, new Object[]{user.getUserName(), user.getUserPass()}, new UserRowMapper());
            if(!loginUser.getDeleteFlag()) {
            return loginUser;
            }
            else {
                return null;
            }
        }
        catch(Exception e) {
            return null;
        }

    }



    public boolean verifyUser(String userName) {

        int count=jdbcTemplate.queryForObject("select count(*) from user where user_name = ?",new Object[] {userName},Integer.class);

        if(count>0)
            return false;
        else
            return true;
    }



    public boolean updatePass(User user) {
        try {

            jdbcTemplate.update("update user set user_pass=? where user_identifier=?", user.getUserPass(), user.getUserName());
            return true;
        } catch (Exception e) {
            return false;
        }

    }



    public boolean updateUserType(User user) {

        try {
            jdbcTemplate.update("update user set user_type=? where user_identifier=?",user.getUserType(),user.getUserName());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }



    public boolean updateEmail(User user) {

        try {
            jdbcTemplate.update("update user set email=? where user_name=?",user.getEmail(),user.getUserName());
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }



    public void updateTime(User user) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            jdbcTemplate.update("update user set updated_time=? where user_name=?",timestamp,user.getUserName());
        }
        catch(Exception e) {
            System.out.print(e);
        }
    }

}
