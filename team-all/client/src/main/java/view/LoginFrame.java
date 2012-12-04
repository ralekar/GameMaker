package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Player;
import org.apache.log4j.Logger;
import utility.Constants;
import view.communication.ClientHandler;

public class LoginFrame extends JFrame {

    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton login;
    private JPanel loginPanel;
    private JLabel errorLabel;

    public LoginFrame() {
        this.usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 100, 30);

        this.username = new JTextField(10);
        username.setBounds(120, 20, 100, 30);

        this.password = new JPasswordField(10);
        password.setBounds(120, 60, 100, 30);

        this.passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 60, 100, 30);

        this.login = new JButton("Login");
        login.setBounds(70, 120, 100, 30);

        this.errorLabel = new JLabel();
        errorLabel.setBounds(220, 20, 100, 30);


        this.login.addActionListener(new LoginFrame.LoginActionListener());
        this.loginPanel = new JPanel();

        loginPanel.setLayout(null);

        this.loginPanel.add(usernameLabel);
        this.loginPanel.add(username);
        this.loginPanel.add(passwordLabel);
        this.loginPanel.add(password);
        this.loginPanel.add(login);
        this.loginPanel.add(errorLabel);
        this.setLocationRelativeTo(null);

        this.add(loginPanel);
        this.setVisible(true);
        this.setSize(240, 200);

    }

    /**
     * ***GETTERS AND SETTERS ****
     */
    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JButton getLogin() {
        return login;
    }

    public void setLogin(JButton login) {
        this.login = login;
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LoginFrame.LoginActionListener.class);
            String result = null;
            if (e.getSource() == login) {
                if (username.getText().equalsIgnoreCase("") || password.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(loginPanel, "UserName and Password can not be empty");
                } else {
                    setVisible(false);
                    String user_name = username.getText();
                    String pass_word = password.getText();

                    boolean loginok;
                    try {
                        loginok = ClientHandler.userLogin(user_name, pass_word, Constants.HOST, Constants.PATH + "/loginUser");
                    } catch (Exception ex) {
                        LOG.error(ex);
                        loginok = false;
                    }

                    if (loginok) {
                       // d.getButtonPanel().getUserName().setText("Welcome " + user_name);
                        Player.getInstance().setUsername(user_name);
                        Player.getInstance().setPassword(pass_word);
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,
                                "login successful");
                        setOnlineChatUsers();
                    } else {
                       // d.getButtonPanel().getUserName().setText("invalid username and password");
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,
                                "login invalid");
                        Player.setInstanceNull();
                    }
                }
            }
        }
    }
    
    private void setOnlineChatUsers() {
		try {
			List<String> users = ClientHandler.getActiveUsers(Constants.HOST,
					Constants.PATH+"/getActiveUsers");
			ChatViewPanel.setOnlineUsersList(users);
		} catch (Exception e) {
			LOG.error(e);
		}
		
	}
    private static final Logger LOG = Logger.getLogger(LoginFrame.class.getName());
}
