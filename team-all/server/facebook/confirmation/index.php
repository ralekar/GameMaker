<?php
require '../fbdata/fbmain.php';
$queueName=$_GET['q'];
$user = $facebook->getUser();
if (!$user) {
$loginUrl = $facebook->getLoginUrl(
			array(
				'canvas'=> 1,
				'fbconnect' => 0,
//				'req_perms' => 'user_photos, user_status, user_videos',
//				'scope'         => 'user_photos,user_status,user_videos'
//Add permissions as needed
			     )
			);


	header( 'Location: '.$loginUrl) ; 
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Game Maker Facebook Login</title>
</head>

<body>
    <div id="page">
      <div id="pagetop">
		<h1 style="text-align:center">Game Maker</h1>
        <div class="links"></div>
     </div>
        
        <div id="header"> Facebook Authorization through Game Maker</div>
  
<div id="main">
	<div class="content">
        	<div class="main_top">
            	<h1 style="text-align:center">Facebook Login Successful</h1>
            </div>
            
   	  <div class="main_body">
        <p>&nbsp;</p>
        <p>&nbsp;</p>
<?php
$me = $facebook->api('/me/');
$perm = $facebook->api('/me/permissions/');
$exec="java -jar send.jar ".$queueName." ".$me[username];
//echo print_r($perm);
$output = shell_exec($exec);
//print_r($me[username]);
?>
        <p style="text-align:center"> <img src="https://graph.facebook.com/<?php echo $user; ?>/picture">Thank you <a href="http://www.facebook.com/<?php echo $user ?>"> <?php echo $me[first_name] ?></a> for logging in through Facebook</p>
        <p style="text-align:center">Please Visit back the Game Maker App for updated login</p>
          <p>&nbsp;</p>
           	</div>
	</div>
            <div class="clear">&nbsp;</div>
        </div>
<div id="footer">
        <p>
        <a href="#">Game Maker</a></p>
        </div>        
        
        
        
        </div>
</body>
</html>
