package db;

@SuppressWarnings("serial")
public class Resources implements java.io.Serializable {

    private int resourceNumber;
    private String resourceName;
    private String resourceType;
    private byte[] resource;
    private String username;

    public Resources() {
    }

    public Resources(String resourceName, String resourceType, byte[] resource, String username) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
        this.resource = resource;
        this.username = username;
    }

    public int getResourceNumber() {
        return this.resourceNumber;
    }

    public void setResourceNumber(int resourceNumber) {
        this.resourceNumber = resourceNumber;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public byte[] getResource() {
        return this.resource;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
