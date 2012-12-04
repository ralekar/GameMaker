package utility;

import org.apache.commons.configuration.*;

public class GetConfig {
	private static final org.apache.log4j.Logger LOG = 
			org.apache.log4j.Logger.getLogger(GetConfig.class);
	
	public String getData(String xmlTag) throws ConfigurationException {
        String value = "";
        try {
            String path = getClass().getResource("/").getPath();
            path = path.replace("%20", " ");
            LOG.debug(path);
            XMLConfiguration xpl =  new XMLConfiguration(path + "config.xml");
            value = xpl.getString(xmlTag);
        } catch (Exception ex) {
            LOG.debug(ex);
        } 
        return value;
    }
}
