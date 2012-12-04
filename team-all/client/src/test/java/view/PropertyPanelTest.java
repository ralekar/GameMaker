/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.junit.*;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Nikhil
 */
public class PropertyPanelTest {

    public PropertyPanelTest() {        
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
    public void testGetValueForProperty() {
        
        String property = "";
        PropertyPanel instance = new PropertyPanel();        
        String result = instance.getValueForProperty(property);
        assertNotNull(result);       
        
    }

    @Test
    public void testClearAll() {
        String failMessage = "Exception in clearAll()";
        PropertyPanel instance = new PropertyPanel();
        Logger log = Logger.getLogger(instance.getClass());
        
        try{
            instance.clearAll();
        }catch(Exception e){
            log.error(failMessage, e);
            fail(failMessage);
        }
    }
}
