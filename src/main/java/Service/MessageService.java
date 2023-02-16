package Service;


import Model.Account;
import Model.Message;
import DAO.AccountDAO;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
     MessageDAO messageDAO;
  
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    
    public Message addMessage(Message message) {
        // var accountId = message.getPosted_by();
        // if (new AccountService().getAccountById(accountId) == null) {
        //     return null;
        // }
        if( message.getMessage_text() == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255) {
              return null;
        }
        else{
            return messageDAO.insertMessage(message);
    }
}
public Message getMessagebyID(int message_id) {
    return messageDAO.getMessageByID(message_id);

}
public Message deleteMessagebyID(int message_id) {
    return messageDAO.deleteMessageByID(message_id);

}
   
    public List<Message> getAllMessages() {
       return messageDAO.getAllMessages();
    
    }


    // public Object getAllMessages() {
    //     return null;
    // }
}