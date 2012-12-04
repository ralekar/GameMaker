
package view;

import java.awt.event.ActionEvent;
import org.junit.*;
import static org.junit.Assert.*;

public class OptionsFrameTest {
    
    public OptionsFrameTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    } 

    @Test
    public void testGetOptionFrame() {       
//        OptionsFrame expResult = null;
        OptionsFrame result = OptionsFrame.getOptionFrame();
        assertNotNull(result);        
    }

    @Test
    public void testSetVisible() {
        
        boolean visibility = false;
        OptionsFrame instance = OptionsFrame.getOptionFrame();
        instance.setVisible(visibility);        
    }
}
