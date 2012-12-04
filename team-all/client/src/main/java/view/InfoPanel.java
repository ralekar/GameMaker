package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import model.Resources;
import org.apache.log4j.Logger;
import utility.Constants;
import utility.Helper;
import utility.Util;
import view.communication.ClientHandler;

public class InfoPanel extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int originalX;
    private int originalY;
    
    public InfoPanel(String message) {

        //setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());
        
        
        Resources resource = null;
        try {
            resource = ClientHandler.loadResource(String.valueOf(44),
                    Constants.HOST, Constants.PATH + "/loadResource");
        } catch (Exception ex) {
            LOG.error(ex);
            return;
        }
        Image image = Util.convertByteArraytoImage(resource.getResource(),
                "jpg");
        
        Rectangle imageRect = new Rectangle(0, 0, image.getWidth(null), image.getHeight(null));
        setBounds(0, 0, image.getWidth(null), image.getHeight(null) + 40);
        
        JLayeredPane centerPanel = new JLayeredPane();
        
        JLabel imageLabel = new JLabel();
        imageLabel.setOpaque(false);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setBounds(imageRect);
        centerPanel.add(imageLabel);
        
        JLabel textLabel = new JLabel();
        textLabel.setOpaque(false);
        Rectangle textRect = new Rectangle(imageRect.x + 10, imageRect.y, imageRect.width - 20, imageRect.height - 20);
        textLabel.setBounds(textRect);
        textLabel.setText(message);
        
        centerPanel.add(textLabel);
        
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setOpaque(false);
        
        JButton backButton = new JButton("<");
        backButton.addActionListener(this);
        controlPanel.add(backButton);
        
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(this);
        controlPanel.add(closeButton);
        
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(this);
        controlPanel.add(nextButton);
        
        add(controlPanel, BorderLayout.NORTH);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Rectangle currentBounds = getBounds();
        int x = currentBounds.x + currentBounds.width;
        int y = currentBounds.y + currentBounds.height;
        
        GameMakerView gameMakerView = Helper.getsharedHelper().getGameMakerView();
        gameMakerView.removeInfoPanel();
        
        JButton button = (JButton) arg0.getSource();
        int currentMessageNumber = Helper.getsharedHelper().getCurrentMessageNumber();
        if (currentMessageNumber == 1) {
            Helper.getsharedHelper().setOriginalPopupRect(new Rectangle(x, y, 0, 0));
        }
        
        if (button.getText().equalsIgnoreCase("<")) {
            if (currentMessageNumber <= 1) {
                currentMessageNumber = 7;
            }
            --currentMessageNumber;
        } else if (button.getText().equalsIgnoreCase(">")) {
            if (currentMessageNumber >= 6) {
                currentMessageNumber = 0;
            }
            ++currentMessageNumber;
        }
        
        if (currentMessageNumber > 4 && currentMessageNumber < 7) {
            JPanel rightPanel = gameMakerView.getRightPanel();
            Rectangle rightPanelBounds = rightPanel.getBounds();
            
            JPanel subPanel = null;
            if (currentMessageNumber == 5) {
                subPanel = gameMakerView.getPropertyPanel();
            } else if (currentMessageNumber == 6) {
                subPanel = gameMakerView.getActionEventPanel().getPanel();
            }
            
            Rectangle subPanelBounds = subPanel.getBounds();
            
            x = rightPanelBounds.x + subPanelBounds.x;
            y = rightPanelBounds.y + subPanelBounds.y + (int) (subPanelBounds.height * 0.75);
        } else {
            x = Helper.getsharedHelper().getOriginalPopupRect().x;
            y = Helper.getsharedHelper().getOriginalPopupRect().y;
        }
        
        
        if (!button.getText().equalsIgnoreCase("X")) {
            gameMakerView.showInfoPanel(Helper.getsharedHelper().getMessage(currentMessageNumber), x, y);
        }
        Helper.getsharedHelper().setCurrentMessageNumber(currentMessageNumber);
    }
    private static final Logger LOG = Logger.getLogger(InfoPanel.class.getName());
}
