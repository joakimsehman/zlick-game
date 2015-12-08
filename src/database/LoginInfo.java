package database;

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

    public void logIn(String user, String nick){
        isLoggedIn = true;
        this.user = user;
        this.nick = nick;
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
