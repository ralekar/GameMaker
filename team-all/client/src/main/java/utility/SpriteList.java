package utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import model.SpriteModel;

import view.GamePanel;

public class SpriteList implements Serializable {

    private static final long serialVersionUID = 1L;
    private static SpriteList sharedSpriteList = null;
    private Collection<SpriteModel> spriteList;
    private SpriteModel selectedSpriteModel;
    private List<SpriteModel> selectedSpriteModels;
    private List<SpriteModel> toBeRemovedSpriteModels;

    public void clear() {
        spriteList.clear();
        selectedSpriteModels.clear();
        toBeRemovedSpriteModels.clear();
    }

    public static SpriteList getInstance() {
        if (sharedSpriteList == null) {
            sharedSpriteList = new SpriteList();
        }
        return sharedSpriteList;
    }

    protected SpriteList() {
        this.spriteList = new ConcurrentLinkedQueue<SpriteModel>();
        this.toBeRemovedSpriteModels = new ArrayList<SpriteModel>();
        this.selectedSpriteModels = new ArrayList<SpriteModel>();
    }

    public void addSprite(SpriteModel sprite) {
        getSpriteList().add(sprite);
    }

    public void removeSprite(SpriteModel spriteModel) {
        getSpriteList().remove(spriteModel);
        GamePanel gamePanel = Helper.getsharedHelper().getGamePanel();
        gamePanel.unregisterModel(spriteModel);

    }

    /*
     * GETTERS AND SETTERS
     */
    public Collection<SpriteModel> getSpriteList() {
        return spriteList;
    }

    public SpriteModel getSelectedSpriteModel() {
        return selectedSpriteModel;
    }

    public List<SpriteModel> getSelectedSpriteModels() {
        return selectedSpriteModels;
    }

    public void setSelectedSpriteModel(SpriteModel selectedSpriteModel) {
        this.selectedSpriteModel = selectedSpriteModel;
    }

    public void setSelectedSpriteModels(List<SpriteModel> selectedSpriteModels) {
        this.selectedSpriteModels = selectedSpriteModels;
    }

    public void setToBeRemovedSpriteModels(
            List<SpriteModel> toBeRemovedSpriteModels) {
        this.toBeRemovedSpriteModels = toBeRemovedSpriteModels;
    }

    public List<SpriteModel> getToBeRemovedSpriteModels() {
        return toBeRemovedSpriteModels;
    }

    public void setSpriteList(List<SpriteModel> spriteList) {
        this.spriteList = spriteList;
    }
}
