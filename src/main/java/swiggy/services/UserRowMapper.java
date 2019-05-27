package swiggy.services;

import org.springframework.jdbc.core.RowMapper;
import swiggy.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowCount) throws SQLException {

        User user;
        user = new User();
        user.setUserName(resultSet.getString("user_name"));
        user.setUserType(resultSet.getString("user_type"));
        user.setCreatedTime(resultSet.getTimestamp("created_time"));
        user.setUpdatedTime(resultSet.getTimestamp("updated_time"));
        user.setUserIdentifier(resultSet.getInt("user_identifier"));
        user.setDeleteFlag(resultSet.getBoolean("delete_flag"));
        user.setEmail(resultSet.getString("email"));
        System.out.println(resultSet.getBoolean("delete_flag"));
        user.setIsVerified(resultSet.getBoolean("is_verified"));
        user.setUserPass(resultSet.getString("user_pass"));


        return user;

    }
}
