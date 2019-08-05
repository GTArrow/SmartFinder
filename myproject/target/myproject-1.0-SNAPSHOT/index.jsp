<!DOCTYPE html>

<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart Finder</title>
        <style>
          body {background-color: powderblue;}
          h1 {color: red;}
          p {color: blue;}
        </style>
    </head>
    <body>
    	<center>
        <h1>Smart Finder:</h1>
        <form method="post" action="FindPlace" target="_blank">
            <br>
            choose a type:<br>
            <select name="Type" size="1">
                <option>airport</option>
                <option>atm</option>
                <option>bank</option>
                <option>bar</option>
                <option>book_store</option>
                <option>bus_station</option>
                <option>cafe</option>
                <option>gym</option>
                <option>hair_care</option>
                <option>hospital</option>
                <option>laundry</option>
                <option>liquor_store</option>
                <option>movie_theater</option>
                <option>museum</option>
                <option>park</option>
                <option>restaurant</option>
                <option>school</option>
                <option>store</option>
            </select>
        	<br><br>
        	input your address:<br>
        	<input type="text" name="location"><br><br><br>
        	<input type="submit" name="Submit">
        </form>
    	</center>
    </body>
</html>

