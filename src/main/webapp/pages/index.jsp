<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Public Transportation Enhancement System</title>
<link rel="icon" type="image/x-icon" href="/img/favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<link href="/css/style.css" rel="stylesheet" type="text/css">
<script src="../main.js"></script>
</head>
<body onload="${func.error}">
	<!-- Navbar -->
	<header>
		<div class="navbar">
			<div class="container flex">
				<h1 id="logo"><a href="/quickroute">Public Transportation Enhancement System</a></h1>
			</div>
		</div>
	</header>
	<main>
		<!-- Main -->
		<div class="main">
			<div class="container flex">
				<div id="app-name">
					<h1>Multi-stop route finder</h1>
					<h4>Generates the most efficient route to visit each location</h4>
				</div>
				<form action="/quickroute/findroute" id="form">
					<div id="form-inner">
						<div id="location-fields">
							<fieldset id="locations-template" class="locations">
								<legend>Enter location:</legend>
								<input type = "text" class = "tb" name = "location[]" autocomplete = "off" placeholder = "Location name">
								<input required type = "text" class = "tb" name = "city[]" autocomplete = "off" placeholder = "City*">
								<input required type = "text" class = "tb" name = "country[]" autocomplete = "off" placeholder = "Country*">
							</fieldset>
							<fieldset class="locations">
								<legend>Enter location:</legend>
								<input type = "text" class = "tb" name = "location[]" autocomplete = "off" placeholder = "Location name">
								<input required type = "text" class = "tb" name = "city[]" autocomplete = "off" placeholder = "City*">
						    	<input required type = "text" class = "tb" name = "country[]" autocomplete = "off" placeholder = "Country*">
							</fieldset>
						</div>
						<div>
							<input class="button" type="button" onclick="addLocation()" value="Add location">
							<input class="button" type="button" onclick="removeLocation()" value="Remove location">
						</div>
						<div id="start-location">
							<fieldset class="start-location">
									<legend>Starting location (optional):</legend>
									<input type = "text" class = "tb" name = "startLocation" autocomplete = "off" placeholder = "Location name">
									<input type = "text" class = "tb" name = "startCity" autocomplete = "off" placeholder = "City*">
									<input type = "text" class = "tb" name = "startCountry" autocomplete = "off" placeholder = "Country*">
							</fieldset>
						</div>
						<div class="db">
							<div>
								<label for="TransportMethod">Transport method: </label><br>
								<select class = "dropbox" name="TransportMethod">
									<option value="DRIVING">Driving</option>
									<option value="TRANSIT">Public Transport</option>
									<option value="WALKING">Walking</option>
								</select>
							</div>
							<div>
								<label for="WeightMethod">Calculate trip by: </label><br>
								<select class = "dropbox" name="WeightMethod">
									<option value="DURATION">Duration</option>
									<option value="DISTANCE">Distance</option>
								</select>
							</div>
						</div>
						<input class="button" type="submit" value="Generate Route">
					</div>
				</form>
			</div>
		</div>
	</main>
	<!-- Footer -->
	<div class="footer">
		<small>minor project 2023</small>
	</div>
</body>
</html>