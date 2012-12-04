<?php
require 'fbdata/fbmain.php';
$user = $facebook->getUser();
$queueName=$_GET['q'];
$msg=$_GET['msg'];
$action=$_GET['action'];
if ($user) {
    $logoutUrl = $facebook->getLogoutUrl();
} else {
    $loginUrl = $facebook->getLoginUrl(
        array(
            'canvas'=> 1,
            'fbconnect' => 0,
            'req_perms' => 'publish_stream, offline_access',
            'scope'         => 'publish_stream, offline_access'
            //				'req_perms' => 'user_photos, user_status, user_videos, publish_stream, offline_access',
            //				'scope'         => 'user_photos,user_status,user_videos, publish_stream, offline_access'
            //Add permissions as needed
            //Add permissions as needed
        )
    );
}



?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FB Likes</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>


<?php if(!$user) { ?>
<h1>Redirecting for Facebook authorization</h1>

<?php  header( 'Location: '.$loginUrl) ; 
//Redirecting to Facebook Login
?>

<?php echo 'this: '.$_SERVER["REQUEST_URI"] ; ?>
//<a href="<?php echo $loginUrl; ?>">Login with Facebook</a>
<?php } 
else { ?>
<?php
    if(!($action=="post")) {
        $host  = $_SERVER['HTTP_HOST'];
        $uri  = rtrim(dirname($_SERVER['PHP_SELF']), '/\\');
        $extra = 'confirmation';
        header("Location: http://$host$uri/$extra?q=$queueName");
    }
    else
    {

        if ($user) { 
            $msg="msg";
            $name="Link";
            $caption="caption";
            $description="description";
            if(isset($_GET['message']))
                $msg=$_GET['message'];
            if(isset($_GET['name']))
                $name=$_GET['name'];
            if(isset($_GET['caption']))
                $caption=$_GET['caption'];
            if(isset($_GET['description']))
                $description=$_GET['description'];
            $attachment = array('message' => $msg,
                'name' => $name,
                'caption' => $caption,
                'link' => 'http://fluency.knownspace.org/student-files/fall2012/a10/team-all/server/facebook/welcome',
                'description' => $description,
                'picture' => 'http://fluency.knownspace.org/student-files/fall2012/a10/team-all/server/facebook/gm.png',
                'actions' => array(array('name' => 'Goto Game Maker Website',
                'link' => 'http://fluency.knownspace.org/student-files/fall2012/a10/team-all/server/facebook/welcome'))
            );
            $result = $facebook->api('/me/feed/',
                'post',
                $attachment);
?>
            <script type="text/javascript">
            <!--
                window.location = "http://www.facebook.com/<?php echo $user; ?>"
                //-->
                </script>
<?php
        }
    }
//Redirecting to Confirmation URL
exit;
?>
<!--<div id="logout">
<a href="javascript:logout()">Logout</a>-->
</div>

        <!--<a href="javascript:void(0)"><div id="tag">Tagged Photos Likes<br/></a>-->    	
    <div id="result" style="margin-left:auto; margin-right:auto; text-align:center;"></div>
<?php } ?>


</body>
</html>
