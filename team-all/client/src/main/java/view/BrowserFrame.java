package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class BrowserFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BrowserFrame.class);
	
	private JTextField addressBar;
	private JEditorPane display;

	public BrowserFrame() {
		super("Share with Facebook");
		addressBar = new JTextField("http://facebook.com");
		addressBar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				loadAddress(event.getActionCommand());
			}

		});

		add(addressBar, BorderLayout.NORTH);

		display = new JEditorPane();
		display.setEditable(false);
		display.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
					loadAddress(event.getURL().toString());
			}

		});
		add(new JScrollPane(display), BorderLayout.CENTER);
		setSize(500, 300);
		setVisible(true);

	}

	private void loadAddress(String address) {
		try {
			display.setPage(address);
			addressBar.setText(address);
		} catch (Exception e) {
			LOG.error(e);
		}

	}

}
