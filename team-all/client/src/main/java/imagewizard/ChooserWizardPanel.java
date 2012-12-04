package imagewizard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;


import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;
import net.miginfocom.swing.MigLayout;

public class ChooserWizardPanel extends JWizardPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton browse;
	private JFileChooser jFileChooser;
	private PreviewPanel previewPanel;
	private File file = null;
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(JWizardPanel.class);

	public ChooserWizardPanel(JWizardComponents wizardComponents) {
		super(wizardComponents, "Choose an image");
		init();
	}
	private void init() {
		jFileChooser = new JFileChooser();
	    jFileChooser.setFileFilter(new MyFilter());
		browse = new JButton("Browse...");
		previewPanel = new PreviewPanel(500,300);
		previewPanel.setLayout(new MigLayout());
		this.add(browse);
		this.add(previewPanel);
		

		browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int returnVal = jFileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = jFileChooser.getSelectedFile();
					try {
						previewPanel.setImage(ImageIO.read(file));
					} catch (IOException e1) {
						LOG.error("Unable to read the file");
					}
					previewPanel.repaint();
				}
				update();
			}
		});
	}

	public void update() {
		setNextButtonEnabled(file != null);
		setFinishButtonEnabled(false);
		setBackButtonEnabled(false); // there is no way back
	}
        
	public void next() {
		switchPanel(Wizard.PANEL_TAG);
	}
	public void back() {
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

}