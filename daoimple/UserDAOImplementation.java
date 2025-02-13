package com.myfoods.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myfoods.dao.UserDAO;
import com.myfoods.model.User;
import com.myfoods.utility.DBConnection;

public class UserDAOImplementation implements UserDAO {
    private static final String INSERT_USER_QUERY = "INSERT into `user` (`name`,`username`,`password`,`email`,`phone`,`address`,`role`) VALUES (?,?,?,?,?,?,?)";
    private static final String GET_USER_QUERY = "SELECT * from `user` WHERE `userId` =? ";
    private static final String UPDATE_USER_QUERY = "UPDATE `user` SET `name`= ?,`password`= ? , `phone`= ? , `address`= ? , `role`= ? WHERE `userId`=?";
    private static final String DELETE_USER_QUERY= "DELETE from `user` where `userId`= ? ";
    private static final String GET_ALL_USERS_QUERY = "SELECT * from `user`";
    
    private ResultSet resultSet;
    
    @Override
    public void addUser(User user) {
         try(Connection connection = DBConnection.Connection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);)
         {
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getUsername());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setString(6,user.getAddress());
            preparedStatement.setString(7,user.getRole());
            
            int res = preparedStatement.executeUpdate();
        } 
         
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int userId) {
        User user=null;
        try(Connection connection = DBConnection.Connection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);) 
        {
            
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUsers(resultSet); 
                }
         } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

     @Override
     public User getUserByEmail(String email) {
          User user = null;
          try (Connection connection = DBConnection.Connection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE email = ?")) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                
                if (rs.next()) {
                    user = new User(rs.getInt("userID"), rs.getString("name"), rs.getString("username"), rs.getString("password"),
                            rs.getString("email"), rs.getString("phone"), rs.getString("address"), null, null, null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
       }
     
    @Override
    public void updateUser(User user) {
        try(Connection connection = DBConnection.Connection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY);) 
        {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.setInt(6, user.getUserId());
            
            preparedStatement.executeUpdate();    
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        
        try(Connection connection = DBConnection.Connection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);) 
        {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        
        ArrayList<User> userList = new ArrayList<>();
        User user = null;
        
        try(Connection connection = DBConnection.Connection();
                 Statement statement = connection.createStatement();) 
        {
             resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            
            while(resultSet.next()) {
                user = extractUsers(resultSet);
                userList.add(user);
            }
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        
    return userList;
    }
    

    public User extractUsers(ResultSet resultSet) throws SQLException {
        int userID = resultSet.getInt("userId");
        String name = resultSet.getString("name");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String address = resultSet.getString("address");
        String role = resultSet.getString("role");
        Date createdDate = resultSet.getDate("createdDate");
        LocalDateTime lastLoginDate = resultSet.getObject("lastLoginDate", LocalDateTime.class);

        return new User(userID, name, username, password, email, phone, address, role, createdDate, lastLoginDate);
    }

    public void updateLastLoginDate(int userId, LocalDateTime now) {
        try (Connection connection = DBConnection.Connection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `user` SET last_login_date = ? WHERE userID = ?")) {
                preparedStatement.setObject(1, now);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
