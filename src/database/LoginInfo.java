package database;

import game.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.SoundHandler;

/**
 * Created by joakim on 2015-12-08.
 */
public class LoginInfo {

    private String user;
    private String nick;
    private boolean isLoggedIn;

    private static LoginInfo instance;

    private LoginInfo(){
        isLoggedIn = false;
    }

    public static LoginInfo getInstance(){
        if(instance == null){
            instance = new LoginInfo();
        }

        return instance;
    }
    
    
    public boolean logIn(String userName, String password){
    	
    	Connection connection = DatabaseConfiguration.getConnection();
        
    	boolean loginSuccessfull = false;
    	
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM playerinfo");

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean foundUser = false;
            while(resultSet.next()){

                if(resultSet.getString(1).equals(userName)){
                    foundUser = true;

                    if(resultSet.getString(2).equals(password)){
                        
                        this.user = resultSet.getString(1);
                        this.nick = resultSet.getString(3);
                        isLoggedIn = true;

                        loginSuccessfull = true;
                    }else{
                        
                    }
                }
            }
            if(!foundUser){
                loginSuccessfull = false;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return loginSuccessfull;
    	
    }
    

    public boolean isLoggedIn(){
        return isLoggedIn;
    }

    public String getUserName(){
        if(isLoggedIn) {
            return user;
        }else{
            return "NOT LOGGED IN";
        }
    }

    public String getNick(){
        if(isLoggedIn) {
            return nick;
        }else{
            return "NOT LOGGED IN";
        }
    }

}
