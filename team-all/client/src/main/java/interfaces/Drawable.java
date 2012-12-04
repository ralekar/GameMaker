package interfaces;

import java.awt.Graphics;

public interface Drawable {
    public void draw(Graphics g);
    public boolean isVisible();
    public String getLayer();
}