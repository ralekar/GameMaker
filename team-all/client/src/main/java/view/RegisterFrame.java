package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Player;
import utility.Constants;
import utility.Helper;
import view.communication.ClientHandler;

public class RegisterFrame extends LoginFrame {

    @SuppressWarnings("deprecation")
    public RegisterFrame() {
        super();

        getLogin().addActionListener(new RegisterButtonListner());
        getLogin().setLabel("Register");
    }

    class RegisterButtonListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RegisterButtonListner.class);

            if (e.getSource() == getLogin()) {
                if (getUsername().getText().equalsIgnoreCase("") || getPassword().getText().equalsIgnoreCase("")) {
                    getLoginPanel();
                } else {
                    setVisible(false);

                    String user_name = getUsername().getText();
                    @SuppressWarnings("deprecation")
                    String pass_word = getPassword().getText();

                    boolean registerok = false;
                    try {
                        registerok = ClientHandler.userRegister(user_name, pass_word, Constants.HOST, Constants.PATH + "/registerUser");
                    } catch (Exception ex) {
                        LOG.error(ex);
                        registerok = false;
                    }


                    GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
                    if (registerok) {
                        Player.getInstance().setUsername(user_name);
                        Player.getInstance().setPassword(pass_word);

                        gameMakerView.getButtonPanel().getUserName().setText("Welcome " + user_name);
                    } else {
                        JOptionPane.showMessageDialog(gameMakerView.getBaseFrame(), "Registration Failed");
                        Player.setInstanceNull();
                    }
                }
            }
        }
    }
}
