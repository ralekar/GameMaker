package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;



import model.Player;
import chat.Sender;

public class ChatPanel {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ChatPanel.class);

	private JButton send;
	private JButton choose;
	private JButton chooseText;
	private JTextField textSend;
	private  JTextPane textPane;
	private  JScrollPane textScrollPane;
	private Color userColor;
	private Color textColor;
	private JPanel chatPanel;
	private JPanel sendPanel;
	private JPanel colorPanel;
	private  HTMLEditorKit kit;
    private  HTMLDocument doc;
    private Sender sender;
	
	public ChatPanel(Sender sender){
		this.sender=sender;
		chatPanel = new JPanel();
		sendPanel = new JPanel();
		colorPanel = new JPanel();
		
		userColor=Color.BLUE;
		textColor=Color.BLACK;	
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		kit = new HTMLEditorKit();
	    doc = new HTMLDocument();
	    textPane.setEditorKit(kit);
	    textPane.setDocument(doc);
		
		JScrollPane textScrollPane = new JScrollPane(textPane);

		final JLabel textLabel = new JLabel("Enter Your Text Here:");
		
		textSend = new JTextField();
		textSend.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if(textSend.getText().length()==0)
					send.setEnabled(false);
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				if(textSend.getText().length()>0)
					send.setEnabled(true);
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {				
			}
		});
		textSend.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
			     if (key == KeyEvent.VK_ENTER) {
			    	 if(textSend.getText().length()>0)
							sendChatMessage();
					}
			}
		});
		
		send = new JButton("Send");
		send.setEnabled(false);
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textSend.getText().length()>0)
					sendChatMessage();
			}
		});
		
		choose = new JButton("Username Color");
		choose.setForeground(userColor);
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color=JColorChooser.showDialog(textLabel, "Choose Username Color", userColor);
				if(color!=null) {
					userColor=color;
					choose.setForeground(userColor);
				}
			      //= JColorChooser.showDialog(this,"Choose Background Color",userColor);
			}
		});
		chooseText = new JButton("Text Color");
		chooseText.setForeground(textColor);
		chooseText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color=JColorChooser.showDialog(textLabel, "Choose text Color", textColor);
				if(color!=null) {
					textColor=color;
					chooseText.setForeground(textColor);
				}
			      //= JColorChooser.showDialog(this,"Choose Background Color",userColor);
			}
		});

		
		sendPanel.setLayout(new GridLayout(2,2));
		sendPanel.add(textSend);
		sendPanel.add(send);
		sendPanel.add(choose);
		sendPanel.add(chooseText);
		
//		colorPanel.setLayout(new GridLayout(1,2));
//		colorPanel.add(choose);
//		colorPanel.add(chooseText);
		
		chatPanel.setLayout(new BorderLayout());
		chatPanel.add(textScrollPane,BorderLayout.CENTER);
		chatPanel.add(sendPanel,BorderLayout.SOUTH);
		
	
		
//		chatPanel.setLayout(new BoxLayout(chatPanel,BoxLayout.Y_AXIS));
//		chatPanel.add(textScrollPane);
//		//chatPanel.add(textLabel);
//		chatPanel.add(sendPanel);
//		chatPanel.add(colorPanel);

	
	}
	
	public void updateChatWindow(String msg) {
		
				try {
				kit.insertHTML(doc, doc.getLength(),msg, 0, 0, null);
				}
				catch(IOException e) {
					LOG.error(e);
				} catch (BadLocationException e) {
					LOG.error(e);
				}
				textPane.setCaretPosition(textPane.getDocument().getLength());
				textScrollPane.getVerticalScrollBar().setValue(textScrollPane.getVerticalScrollBar().getMaximum());
				
			}
	public void sendChatMessage() {
		
		if(Player.getInstance().getUsername()!=null)
		{
			Integer userHashColorNumber=userColor.hashCode();
			String userColorHash=Integer.toHexString(userHashColorNumber);
			Integer textColorHashNumber=textColor.hashCode();
			String textColorHash=Integer.toHexString(textColorHashNumber);
			//ChatSender.sendMessage("<b style=\"color:#"+d.substring(2)+"\">"+Player.getInstance().getUsername()+"</b>: <a style=\"color:#"+d1.substring(2)+"\">"+textSend.getText()+"</a>");
			sender.sendMessage("<img src=\""+Player.getInstance().getAvatarURL()+"\" width=\"25\" height=\"25\"><b style=\"color:#"+userColorHash.substring(2)+"\">"+Player.getInstance().getUsername()+"</b>: <a style=\"color:#"+textColorHash.substring(2)+"\">"+textSend.getText()+"</a>");
			textSend.setText("");	
			send.setEnabled(false);
		}
		
		else
		{
			JFrame frame=new JFrame();
			if(Player.getInstance().getUsername()==null)
				JOptionPane.showMessageDialog(frame,"Please login");
			if(!checkText(textSend.getText()))
				JOptionPane.showMessageDialog(frame,"Please enter valid text.");
		}
		
	}
	public boolean checkText(String text){
		
		String[] temp= text.split(" ");
		boolean flag=false;
		
		for(int i=0;i < temp.length;i++){
		
			if(temp[i].matches("^[a-zA-Z0-9_]*$"))
				flag=true;
			else
				flag=false;
		}
		
		return flag;
	}
	
	public void setChatPanel(JPanel chatPanel){
		this.chatPanel = chatPanel;
	}
	
	public JPanel getChatPanel(){
		return chatPanel;
	}

}
