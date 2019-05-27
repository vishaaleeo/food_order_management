package swiggy.domain;

/*
 *Interface for crud operations of user
 */
public interface UserDAO {


        public String createUser(User user);

        public String updateUser(User user);

        public String deleteUser(User user);

        public User loginUser(User user);



}
