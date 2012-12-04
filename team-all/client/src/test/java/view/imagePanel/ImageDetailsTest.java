
package view.imagePanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class ImageDetailsTest {

    private String imagePath,imagePathMock,newImagePath;
    private String tag,tagMock,newTag;
    private ImageDetails imageDetailsMock;
    
    @Before
    public void setUp() {
        newImagePath="new path";
        imagePathMock = "mock path";
        imageDetailsMock = Mockito.mock(ImageDetails.class);    
    }
    
    @After
    public void tearDown() {
        imageDetailsMock = null;
    }

    @Test
    public void testGetImagePath() {
        Mockito.when(imageDetailsMock.getImagePath()).thenReturn(imagePathMock);
        assertEquals(imagePathMock,imageDetailsMock.getImagePath());
    }

    @Test
    public void testSetImagePath() {
        String imagePath = "";
        ImageDetails instance = new ImageDetails();
        instance.setImagePath(newImagePath);
        imagePath=instance.getImagePath();
        assertEquals(imagePath,newImagePath);
    }

    @Test
    public void testGetTag() {
        String testTag="";
        ImageDetails instance = new ImageDetails();
        testTag=instance.getTag();
        assertEquals(testTag,tag);
        
    }

    @Test
    public void testSetTag() {
        String tag = "";
        ImageDetails instance = new ImageDetails();
        instance.setTag(newTag);
        tag=instance.getTag();
        assertEquals(tag,newTag);
    }
}
