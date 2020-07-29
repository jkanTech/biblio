<?php

	//Defining Constants
	define('HOST','localhost');
	define('USER','root');
	define('PASS','root');
	define('DB','ispt_db');
	
	//Connecting to Database
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Probleme de connexion a la base de données.');
	
?>