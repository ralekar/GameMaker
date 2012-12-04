package twitter;
/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public  class UpdateStatus {
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UpdateStatus.class);

    public boolean execute(String statusMessage) throws IOException {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            try {
                // get request token.
                // this will throw IllegalStateException if access token is already available
            	
                RequestToken requestToken = twitter.getOAuthRequestToken();
                LOG.info("Got request token.");
                LOG.info("Request token: " + requestToken.getToken());
                LOG.info("Request token secret: " + requestToken.getTokenSecret());
                AccessToken accessToken = null;

                while (null == accessToken) {
    				try {
    					URI uri=new URI(requestToken.getAuthorizationURL());
    					Desktop.getDesktop().browse(uri);
    				} catch (IOException e) {
    					LOG.error(e);
    				} catch (URISyntaxException e) {
    					LOG.error(e);
    				}

                    JFrame frame = new JFrame("Twitter PIN verification");

                    // prompt the user to enter their name
                    String pin = JOptionPane.showInputDialog(frame, "Enter the PIN");

					if (pin == null)
						return false;
                    
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            LOG.error("Unable to get the access token.");
                        } else {
                        	LOG.error(te);
                        }
                    }
                }
                LOG.info("Got access token.");
                LOG.info("Access token: " + accessToken.getToken());
                LOG.info("Access token secret: " + accessToken.getTokenSecret());
            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                	return false;
                }
            }
            twitter.updateStatus(statusMessage);
            
        } catch (TwitterException te) {
        	LOG.error(te);
        	return false;
        }
        return true;
    }
    
}
