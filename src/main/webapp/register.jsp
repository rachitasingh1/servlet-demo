<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
</head>
<body style="background-color:coral;">
<div >
<form action="register" method="post">
 	Name: <input type="text" name="name" placeholder="Enter name "><br><br>
 	Email: <input type="text" name="email" placeholder="Enter email "><br><br>
 	Password: <input type="password" name="password" placeholder="Enter password "><br><br>
 	Notifications: <input type="radio" name="yes" value="Yes"/>Yes <input type="radio" name="no" value="No"/>No<br><br>
 	Region: <select name="region">
 			<option>Dwarka</option>
 			<option>Chanakyapuri</option>
 			<option>Dhaula Kuan</option>
 			<option>Subroto Park</option>	
 			<option>Vasant Vihar</option>
 			<option>Punjabi Bagh</option>
 		  </select><br><br>
 	<input type="submit" name="register" value="Register"> 
 </form></div>

</body>
</html>