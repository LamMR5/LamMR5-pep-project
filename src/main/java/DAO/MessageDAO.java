package DAO;


import Util.ConnectionUtil;
import Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;



public class MessageDAO {
    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            

            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int generated_message_id = pkeyResultSet.getInt(1);
                message.setMessage_id(generated_message_id);
                return message;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")) ;
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    public Message getMessageByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setInt(1, message_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                
                return new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")) ;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message deleteMessageByID(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "DELETE * FROM message WHERE message_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setInt method here.
            preparedStatement.setInt(1, message_id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                
                return new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch")) ;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

//     public List<Message> getMessagesByAccountId(int account_id){
//         Connection connection = ConnectionUtil.getConnection();
//         try {
//             //Write SQL logic here
//             String sql = "SELECT * FROM message WHERE =?";
//             PreparedStatement preparedStatement = connection.prepareStatement(sql);
// d
//             //write preparedStatement's setInt method here.
//             preparedStatement.setInt(1, message_id);
//             ResultSet rs = preparedStatement.executeQuery();
//             if(rs.next()){
                
//                 return new Message(
//                         rs.getInt("message_id"),
//                         rs.getInt("posted_by"),
//                         rs.getString("message_text"),
//                         rs.getLong("time_posted_epoch")) ;
//             }
//         }catch(SQLException e){
//             System.out.println(e.getMessage());
//         }
//         return null;
//     }









    // public List<Message> getAllMessage(){
    //     Connection connection = ConnectionUtil.getConnection();
    //     List<> flights = new ArrayList<>();
    //     try {
    //         //Write SQL logic here
    //         String sql = "SELECT flight_id, departure_city, arrival_city FROM flight;";

    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);
    //         ResultSet rs = preparedStatement.executeQuery();
    //         while(rs.next()){
    //             Flight flight = new Flight(rs.getInt("flight_id"), rs.getString("departure_city"),
    //                     rs.getString("arrival_city"));
    //             flights.add(flight);
    //         }
    //     }catch(SQLException e){
    //         System.out.println(e.getMessage());
    //     }
    //     return flights;
    // }

}
