package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerHandler);
        app.post("/login", this::loginHandler);
        app.get("/messages/{message_id}", this::getMessageByIDhandler);
        app.delete("/messages/{message_id}", this::deletMessageByIDhandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::getAllMessageHandler);
        app.get("/accounts/{account_id}", this::getAccountByIdHandler);
        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    
    private void registerHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount==null){
            ctx.status(400);
        }else{
            ctx.json(mapper.writeValueAsString(addedAccount));
        }
    }
    
    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account loginAccount = accountService.Loginaccount(account.getUsername(), account.getPassword());
        if(loginAccount == null){
            ctx.status(401);
        } else {
            ctx.json(mapper.writeValueAsString(loginAccount));
        }
    }
    
    
        private void createMessageHandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(ctx.body(), Message.class);
            Message addedMessage = messageService.addMessage(message);
            
            if(addedMessage!=null){
                ctx.json(mapper.writeValueAsString(addedMessage));
            }
            else{
                 ctx.status(400);
            }
        }

        private void getAllMessageHandler(Context ctx){
            ctx.json(messageService.getAllMessages());
        }
        private void getMessageByIDhandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            // Message message = mapper.readValue(ctx.body(), Message.class);
            int message_id = Integer.parseInt(ctx.pathParam("message_id"));
            ctx.json(messageService.getMessagebyID(message_id));
        }
        private void deletMessageByIDhandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            // Message message = mapper.readValue(ctx.body(), Message.class);
            int message_id = Integer.parseInt(ctx.pathParam("message_id"));
            ctx.json(messageService.deleteMessagebyID(message_id));
        }
        private void getAccountByIdHandler(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            ctx.json(accountService.getAccountById(account.getAccount_id()));
        }
}