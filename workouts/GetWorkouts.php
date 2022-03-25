<?php
include "connection.php";

	      $response = array(); 
	$query="SELECT * FROM workouts ";
        $result = $conn->query($query);
     if ($result->num_rows > 0) { 
	      while($row = $result->fetch_assoc()) { 
		array_push($response, array(
			'id' => $row['id'], 
			'name' => $row['name'],
			 'duration' => $row['duration'], 
			'views' => $row['views'], 
			'tags' => $row['tags'], 
			'description' => $row['description'], 
			'type' => $row['type'], 
			'trainer' => $row['trainer'], 
			'difficulty' => $row['difficulty'],
			'video_url' => $row['video_url'],
			 'category' => $row['category']));
		}
	 } else {
   	    array_push($response, array('response' => 'invalid'));		
		die(json_encode($response));		   
	   }
  		echo json_encode($response);			

 ?>
	