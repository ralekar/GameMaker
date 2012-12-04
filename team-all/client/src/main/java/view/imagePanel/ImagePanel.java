package view.imagePanel;

import imagewizard.MyFilter;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Resources;
import net.miginfocom.swing.MigLayout;
import utility.Constants;
import utility.Util;
import view.communication.ClientHandler;

public class ImagePanel implements ActionListener, ChangeListener {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
            .getLogger(ImagePanel.class);
    private JPanel imagePanel;
    private JPanel imageTiles;
    private JPanel paginationPanel;
    private static final int imagesPerPage = 14;
    private List<ImageProperties> allImages;
    private int presentPage = 1;
    private int imageSize = 50;
    private JScrollPane imageTilesScrollPane;
    private JComboBox imageTags;
    private ImageActionListener imageActionListener;
    private int totalImages;
    private JPanel propertiesPanel;

    public ImagePanel(ImageActionListener imageActionListener) throws Exception {
        this.imageActionListener = imageActionListener;
        propertiesPanel = createPropertiesPanel();
        getImages(); // change this
        totalImages = ClientHandler.countTag(null, Constants.HOST,
                Constants.PATH + "/countTag");
        int lastIndex = allImages.size() < imagesPerPage ? allImages.size()
                : imagesPerPage;
        allImages.subList(0, lastIndex);
        imageTiles = new JPanel(new GridLayout(7, 2));
        populateImageTiles();
        imageTilesScrollPane = new JScrollPane(imageTiles,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        paginationPanel = new JPanel(new FlowLayout());
        populatePaginationPanel();
        imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));

        imagePanel.add(propertiesPanel);
        imagePanel.add(imageTilesScrollPane);
        imagePanel.add(paginationPanel);
        JSlider imageResizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
        imageResizeSlider.addChangeListener(this);
        imagePanel.add(imageResizeSlider);
    }

    private JPanel createPropertiesPanel() throws Exception {
        JPanel propPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JLabel tags = new JLabel("Image Tags");
        propPanel.add(tags);
        String[] tagNames = getImageTags();
        if (tagNames == null) {
            tagNames = new String[]{"no tags"};
        }
        imageTags = new JComboBox(tagNames);
        imageTags.addActionListener(this);
        propPanel.add(imageTags);

        JButton upload = new JButton("Upload Images");
        upload.addActionListener(this);
        propPanel.add(upload);


        return propPanel;
    }

    private String[] getImageTags() throws Exception {
        String tags[] = ClientHandler.listTags(Constants.HOST, Constants.PATH
                + "/getAllTags");

        LOG.debug(tags.length);
        String[] finalTags = new String[tags.length + 1];
        finalTags[0] = new String("All");
        for (int i = 1; i < tags.length; i++) {
            finalTags[i] = new String(tags[i]);
        }
        return finalTags;
    }

    private void populatePaginationPanel() {
        paginationPanel.removeAll();

        JButton first = new JButton("<<");
        first.addActionListener(this);
        JButton prev = new JButton("<");
        prev.addActionListener(this);
        JButton next = new JButton(">");
        next.addActionListener(this);
        JButton last = new JButton(">>");
        last.addActionListener(this);
        paginationPanel.add(first);
        paginationPanel.add(prev);
        paginationPanel.add(next);
        paginationPanel.add(last);

    }

    private void populateImageTiles() {
        String tag = (String) imageTags.getSelectedItem();
        if (tag.equalsIgnoreCase("All")) {
            tag = null;
        }
        imageTiles.removeAll();

        for (int i = 0; i < allImages.size(); i++) {
            Image image = allImages.get(i).getImage();
            ImageIcon icon = new ImageIcon(image.getScaledInstance(imageSize,
                    imageSize, 1));
            JButton button = new JButton(icon);
            button.setName(allImages.get(i).getImageKey());
            button.addActionListener(imageActionListener);
            imageTiles.add(button);

        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame();
        ImagePanel panel = new ImagePanel(null);
        f.setContentPane(panel.getImagePanel());
        f.setVisible(true);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public List<ImageProperties> getImages() throws Exception {
        String tag = (String) imageTags.getSelectedItem();
        if (tag.equalsIgnoreCase("all")) {
            tag = null;
        }
        allImages = new ArrayList<ImageProperties>();

        Resources[] images = ClientHandler.listPageResources(String.valueOf(presentPage), String.valueOf(imagesPerPage),
                tag, Constants.HOST, Constants.PATH + "/listPageResources");
        for (int i = 0; i < images.length; i++) {
            Image image = Util.convertByteArraytoImage(images[i].getResource(), "jpg");
            ImageProperties im = new ImageProperties(String.valueOf(images[i].getReourceNumber()),
                    images[i].getResourceName(), image);
            allImages.add(im);
        }
        return allImages;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().contentEquals("<<")) {
                if (presentPage == 1) {
                    return;
                }
                presentPage = 1;
                getImages();
                updateImageTiles();
                return;
            } else if (e.getActionCommand().contentEquals("<")) {
                if (presentPage == 1) {
                    return;
                }
                presentPage--;
                getImages();
                updateImageTiles();
                return;
            } else if (e.getActionCommand().contentEquals(">")) {
                if (presentPage == Math.ceil((double) totalImages
                        / (double) imagesPerPage)) {
                    return;
                }
                presentPage++;
                getImages();
                updateImageTiles();
                return;
            } else if (e.getActionCommand().contentEquals(">>")) {
                if (presentPage == Math.ceil((double) totalImages
                        / (double) imagesPerPage)) {
                    return;
                }
                presentPage = (int) Math.ceil((double) totalImages
                        / (double) imagesPerPage);
                getImages();
                updateImageTiles();
                return;
            } else if (e.getActionCommand().equalsIgnoreCase("Upload Images")) {
                handleUpload();
                getImages();
                propertiesPanel=createPropertiesPanel();
                updateImageTiles();
                populatePaginationPanel();
                paginationPanel.revalidate();
                paginationPanel.repaint();
            } else if (e.getSource() == imageTags) {
                presentPage = 1;
                imageSize = 50;
                String tag = (String) imageTags.getSelectedItem();
                totalImages = ClientHandler.countTag(tag, Constants.HOST,
                        Constants.PATH + "/countTag");
                getImages();
                updateImageTiles();
                populatePaginationPanel();
                paginationPanel.revalidate();
                paginationPanel.repaint();

            } else {
                int num = Integer.parseInt(e.getActionCommand());
                presentPage = num;
                getImages();
                updateImageTiles();
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }

    }

    private void handleUpload() throws Exception {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new MyFilter());
        File file = null;
        JPanel previewPanel = new JPanel(new MigLayout());
        int returnVal = jFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser.getSelectedFile();
            try {
                Image image = ImageIO.read(file);
                String fileName = file.getName();
                int dotposition = fileName.lastIndexOf(".");

                String imagetype = fileName.substring(dotposition + 1,
                        fileName.length());
                ImageIcon icon = new ImageIcon(image.getScaledInstance(150,
                        150, 1));
                JButton button = new JButton(icon);
                previewPanel.add(button, "wrap");
                JLabel tag = new JLabel("Enter tag name:");
                JTextField tagInput = new JTextField(10);
                previewPanel.add(tag);
                previewPanel.add(tagInput, "wrap");
                int ret = JOptionPane.showConfirmDialog(null, previewPanel,
                        "Preview", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (ret == JOptionPane.OK_OPTION) {
                    uploadImageToDb(image, tagInput.getText(), imagetype);
                }
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Image Upload successful");
            } catch (IOException e1) {
            	JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
                        "Image Uploading Failed");
				LOG.error("Unable to read the file");
                
            }
        }
    }

    private void uploadImageToDb(Image image, String tag, String imagetype) throws Exception {
        Resources resources = new Resources();
        resources.setResourceType("image");
        // TAG: replace the tag with a filename later
        resources.setResourceName(tag);
        // TAG: allow for the logged in user to pass on the name later
        resources.setUsername("admin");
        resources.setResource(Util.convertImagetoByteArray(image, imagetype));
        ClientHandler.saveResource(resources, Constants.HOST, Constants.PATH
                + "/saveResource");
    }

    public JPanel getImagePanel() {
        return this.imagePanel;
    }

    private void updateImageTiles() {
        populateImageTiles();
        imageTiles.revalidate();
        imagePanel.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider s = (JSlider) e.getSource();
        imageSize = s.getValue();
        updateImageTiles();

    }
}
