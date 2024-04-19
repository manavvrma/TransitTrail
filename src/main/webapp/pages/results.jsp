<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Public Transportation Enhancement System - Results</title>
    <link rel="icon" type="image/x-icon" href="/img/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <script src="../main.js"></script>
    <script type="text/javascript">
        function processResults() {
            let path = new Array();
            let pathCosts = new Array();
            <c:forEach var="location" items="${path}" varStatus="count">
                path.push("${location}");
            </c:forEach>

            <c:forEach var="cost" items="${pathCosts}" varStatus="count">
                pathCosts.push("${cost}");
            </c:forEach>

            let transportMethod = "${transportMethod}";
            let totalCosts = "${totalCost}";

            showResults(path, transportMethod, pathCosts, totalCosts);
        }
    </script>
</head>
<body onload="${func.results}${func.error}">
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
                <div id="results">
                    <h2>Shortest Path</h2>
                    <p>Transport Method: ${transportMethod}</p>
                    <p>Total Cost: ${totalCosts}</p>

                    <ul>
                        <c:forEach var="i" begin="0" end="${path.length-1}">
                            <li>${path[i]} - ${pathCosts[i]}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </main>
    <!-- Footer -->
    <div class="footer">
        <small>minor project 2023</small>
    </div>
</body>
</html>
