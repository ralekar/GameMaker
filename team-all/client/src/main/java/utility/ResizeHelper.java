package utility;

public class ResizeHelper {
	
	private static ResizeHelper sharedHelper;
	private double xFactor;
	private double yFactor;
	
	public static ResizeHelper getInstance(){
		if(sharedHelper == null){
			sharedHelper = new ResizeHelper();
		}
		return sharedHelper;
	}
	
	protected ResizeHelper(){
		this.setxFactor(1.0);
		this.setyFactor(1.0);
	}

	public double getxFactor() {
		return xFactor;
	}

	public void setxFactor(double xFactor) {
		this.xFactor = xFactor;
	}

	public double getyFactor() {
		return yFactor;
	}

	public void setyFactor(double yFactor) {
		this.yFactor = yFactor;
	}
	
	public void reset()
	{
		this.setxFactor(1.0);
		this.setyFactor(1.0);
	}
}
