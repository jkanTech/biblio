<?php

if($_SERVER['REQUEST_METHOD']=='POST' /*and $_SERVER['KEY']=='12345'*/){

    //Getting the requested id
    $id =  $_SERVER['id'];
    //$json =$_POST['BOOK_ID'];
    //isset($_POST['BOOK_ID']);
    //$obj = json_decode($_POST,true);
   // $id=$_POST["id"];
   // $id =10;
    // $obj;

    //Importing database
    require_once('database/database.php');

    //Creating sql query with where clause to get an specific employee
    $sql = "SELECT * FROM biblio_app WHERE id=$id";

    //getting result
    $r = mysqli_query($con,$sql);

    //pushing result to an array
    $result = array();
    $row = mysqli_fetch_array($r);
    array_push($result,array(
        "id"=>$row['id'],
        "title"=>$row['title'],
        "category"=>$row['category'],
        "short_description"=>$row['short_description'],
        "description"=>$row['description'],
        "image"=>$row['image'],
        "url"=>$row['url'],
    ));

    //displaying in json format
    echo json_encode($result);

    mysqli_close($con);

}else{
    echo "Accès non autorisé.";
    echo date('Y-m-d H:i:s');
}