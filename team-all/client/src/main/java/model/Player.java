package model;
import utility.Constants;

public class Player {

    private String username, password, usertype;
    private boolean facebookLogin;
    private static Player player;
    private String AvatarURL;

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
            player.setAvatarURL(Constants.DefaultAvatar); 
            player.setFacebookLogin(false);
        }
        return player;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public static Player setInstanceNull() {
        player = null;
        return player;
    }

    /**
     * @return the facebookLogin
     */
    public boolean isFacebookLogin() {
        return facebookLogin;
    }

    /**
     * @param facebookLogin the facebookLogin to set
     */
    public void setFacebookLogin(boolean facebookLogin) {
        this.facebookLogin = facebookLogin;
    }

    /**
     * @return the AvatarURL
     */
    public String getAvatarURL() {
        return AvatarURL;
    }

    /**
     * @param AvatarURL the AvatarURL to set
     */
    public void setAvatarURL(String AvatarURL) {
        this.AvatarURL = AvatarURL;
    }
}
