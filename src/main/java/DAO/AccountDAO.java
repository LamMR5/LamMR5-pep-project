package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class AccountDAO {
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
//       
            String sql =  "INSERT INTO account (username, password) VALUES ( ?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString method here.
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(),account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }


    public Account loginAccount(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql =  "SELECT * FROM account WHERE username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
    
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int account_id = resultSet.getInt("account_id");
                Account account = new Account(
                            account_id,
                        resultSet.getString("username"),
                        resultSet.getString("password")
                    );
                return account;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
        return null;
    }

    
    public Account getAccountByID(int account_id){
        Connection connection = ConnectionUtil.getConnection();

        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE account_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return new Account(
                        rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password")
                        ) ;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    }
