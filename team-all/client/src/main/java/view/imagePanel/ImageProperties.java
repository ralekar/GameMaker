package view.imagePanel;

import java.awt.Image;

public class ImageProperties {

	private String imageKey;
	private String imageTag;
	private Image image;
	
	
	public ImageProperties(String imageKey, String imageTag, Image image) {
		super();
		this.imageKey = imageKey;
		this.imageTag = imageTag;
		this.image = image;
	}
	
	public String getImageKey() {
		return imageKey;
	}
	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}
	public String getImageTag() {
		return imageTag;
	}
	public void setImageTag(String imageTag) {
		this.imageTag = imageTag;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	
	

}
