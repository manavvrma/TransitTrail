/**
 * 
 */
function addLocation() {
	var fieldsets = document.getElementsByClassName("locations");
	
	var fsClone = fieldsets[0].cloneNode(true);
	fsClone.id = '';
	
	var cloneChild = fsClone.childNodes;
	
	for (let i = 0; i < cloneChild.length; i++){
		cloneChild[i].value = '';
	}
	
	document.getElementById("location-fields").appendChild(fsClone);
}


function removeLocation() {
	var fieldset = document.getElementsByClassName("locations");
	
	if (fieldset.length > 2){
		document.getElementById("location-fields").removeChild(fieldset[fieldset.length - 1]);
	}
}

function startError() {
	var error = document.createElement("div");
	error.classList.add("error-message");
	error.textContent = ("Starting location must be part of locations entered");
	document.getElementById("start-location").prepend(error);
}

function locationsError() {
	var error = document.createElement("div");
	error.classList.add("error-message");
	error.textContent = ("Invalid location(s)");
	document.getElementById("location-fields").prepend(error);
}

function noValidRouteError() {
	let results = document.getElementById("results");
	let div = document.createElement("div");
	div.id = "results-list";
	
	let title = document.createElement("h3");
	title.innerHTML = "There are no valid routes.";
	
	div.appendChild(title);
	
	let button = document.createElement("button");
	button.classList.add("button");
	button.onclick = navHome;
	button.innerHTML = "Reset Locations"

	div.appendChild(button);
	
	results.appendChild(div);
}

function navHome() {
	window.location.href = "/quickroute";
}

function showResults(path, transportMethod, pathCosts, totalCost) {
	let results = document.getElementById("results");
	let div = document.createElement("div");
	div.id = "results-list";
	
	let title = document.createElement("h3");
	title.innerHTML = "Most Efficient Route by " + transportMethod + ":";
	
	div.appendChild(title);
	
	let ul = document.createElement("ul");
	ul.classList.add("results");
	
	for (let i = 0; i < path.length; i++) {
		let li = document.createElement("li");
		li.innerHTML = (i + 1) + ". " + path[i];
		ul.appendChild(li);
		
		if (i < path.length - 1) {
			li = document.createElement("li");
			li.innerHTML = pathCosts[i];
			ul.appendChild(li);
		}
	}
	
	div.appendChild(ul);
	
	let total = document.createElement("p");
	total.innerHTML = totalCost;
	
	div.appendChild(total);
	
	let button = document.createElement("button");
	button.classList.add("button");
	button.onclick = navHome;
	button.innerHTML = "Reset Locations"

	div.appendChild(button);
	
	results.appendChild(div);
}

