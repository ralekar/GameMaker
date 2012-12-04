package view;

import interfaces.Drawable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import model.Resources;
import model.SpriteModel;
import utility.Constants;
import utility.ResizeHelper;
import utility.Util;
import view.communication.ClientHandler;

public class SpriteView implements Drawable {

    private static final org.apache.log4j.Logger LOG =
            org.apache.log4j.Logger.getLogger(SpriteView.class);
    private BufferedImage image;
    private SpriteModel model;

    public SpriteView(SpriteModel model) {
        try {
            this.model = model;
            image = (BufferedImage) getImageFromDb(model.getImageId());
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int x = (int) (model.getPosX() * ResizeHelper.getInstance()
                .getxFactor());
        int y = (int) (model.getPosY() * ResizeHelper.getInstance()
                .getyFactor());
        int width = (int) (model.getWidth() * ResizeHelper.getInstance()
                .getxFactor());
        int height = (int) (model.getHeight() * ResizeHelper.getInstance()
                .getyFactor());

        g2.rotate(Math.toRadians(model.getHeading()), x + width / 2, y + height
                / 2);
        g2.drawImage(image, x, y, width, height, null);
        g2.rotate(Math.toRadians(-model.getHeading()), x + width / 2, y
                + height / 2);
    }

    @Override
    public boolean isVisible() {
        if (model != null) {
            return model.isVisible();
        } else {
            return false;
        }
    }

    public SpriteModel getModel() {
        return model;
    }

    public void setModel(SpriteModel model) {
        this.model = model;
    }

    @Override
    public String getLayer() {
        return this.model.getLayer();
    }

    public Image getImageFromDb(int id) {
        Resources resource = null;
        try {
            resource = ClientHandler.loadResource(String.valueOf(id),
                    Constants.HOST, Constants.PATH + "/loadResource");
        } catch (Exception ex) {
            LOG.error(ex);
            return null;
        }
        Image image = Util.convertByteArraytoImage(resource.getResource(),
                "jpg");
        return image;
    }
}
