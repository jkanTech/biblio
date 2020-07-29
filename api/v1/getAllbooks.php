<?php 
	//echo ($_SERVER['HTTP_ID']);

	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Importing Database Script 
		require_once('database/database.php');
		
		//Creating sql query
		$sql = "SELECT * FROM biblio_app";
		
		//getting result 
		$r = mysqli_query($con,$sql);
		
		//creating a blank array 
		$result = array();
		
		//looping through all the records fetched
		while($row = mysqli_fetch_array($r)){
			
			//Pushing name and id in the blank array created 
			array_push($result,array(
			"id"=>$row['id'],
			"title"=>$row['title'],
			"category"=>$row['category'],
			"short_description"=>$row['short_description'],
			"description"=>$row['description'],
			"image"=>$row['image'],
			"url"=>$row['url'],
			"format"=>$row['format'],

			));
		}
		
		//Displaying the array in json format 
		echo json_encode($result);
		
		
		mysqli_close($con);

	}else{
	   echo "Accès non autorisé.";
		echo date('Y-m-d H:i:s');
	}

?>