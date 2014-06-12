<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  
        <!-- Placez ce script JavaScript asynchrone juste devant votre balise </body> -->
	    <script type="text/javascript">
	      (function() {
	       var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
	       po.src = 'https://apis.google.com/js/client:plusone.js';
	       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
	     })();
	    </script>
	    
	    <script>
        function signinCallback(authResult) {
      	  if (authResult['access_token']) {
      		alert("You'r logged.");
      	    
      	  } else if (authResult['error']) {
        	document.getElementById('signinButton').setAttribute('style', 'display: none');
      		alert("Error, not logged.");
      		window.location = "login.jsp";
      	  }
      	}  
	    </script>
 
    
    <!-- Google charts </body> -->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2013',  1000,      400],
          ['2014',  1170,      460],
          ['2015',  660,       1120],
          ['2016',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0},
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div1'));
        chart.draw(data, options);
      }
    </script>
    
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Director (Year)',  'Rotten Tomatoes', 'IMDB'],
          ['Alfred Hitchcock (1935)', 8.4,         7.9],
          ['Ralph Thomas (1959)',     6.9,         6.5],
          ['Don Sharp (1978)',        6.5,         6.4],
          ['James Hawes (2008)',      4.4,         6.2]
        ]);

        var options = {
          title: 'The decline of \'The 39 Steps\'',
          vAxis: {title: 'Accumulated Rating'},
          isStacked: true
        };

        var chart = new google.visualization.SteppedAreaChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
    </script>
    
      <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  1000,      400],
          ['2005',  1170,      460],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          hAxis: {title: 'Year', titleTextStyle: {color: 'red'}}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div3'));
        chart.draw(data, options);
      }
    </script>
    

    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  1000,      400],
          ['2005',  1170,      460],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div4'));
        chart.draw(data, options);
      }
    </script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="bootstrap-3.1.1/docs/assets/ico/favicon.ico">

    <title>HexoSkin-TB</title>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="bootstrap-3.1.1/dist/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="bootstrap-3.1.1/dist/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  
                   
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp">HexoSkin</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">About</a></li>
          </ul>
          <form class="navbar-form navbar-right">

          </form>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview</a></li>
            <li><a href="compare.jsp">Comparer S�ances</a></li>
            <li><a href="#">Analytics</a></li>
            <li><a href="#">Export</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="">Nav item</a></li>
            <li><a href="">Nav item again</a></li>
            <li><a href="">One more nav</a></li>
            <li><a href="">Another nav item</a></li>
            <li><a href="">More navigation</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Dashboard</h1>
           <h3>Derni�re s�ance : 28.04.2014</h3>

<br>
          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              <div id="chart_div1" style="width: 300px; height: 250px;"></div>
              <h4>Performance</h4>
              <span class="text-muted">ECG / Respiration</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
			<div id="chart_div2" style="width: 300px; height: 250px;"></div>

              <h4>Acc�l�ration</h4>
              <span class="text-muted"></span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <div id="chart_div3" style="width: 300px; height: 250px;"></div>
              <h4>Vitesse moyenne</h4>
              <span class="text-muted">m/min</span>
            </div>
			<div class="col-xs-6 col-sm-3 placeholder">
              <div id="chart_div4" style="width: 300px; height: 250px;"></div>
              <h4>D�nivel�</h4>
              <span class="text-muted">% pente</span>
            </div>
          </div>

          <h2 class="sub-header">Historiques S�ances</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Distance</th>
                  <th>Time</th>
                  <th>Calories burned</th>
                  <th>Avg m/min</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>12.05.2014</td>
                  <td>1200 m</td>
                  <td>10:01</td>
                  <td>600 ca</td>
                  <td>50 m/min</td>
                </tr>
                <tr>
                  <td>20.05.2014</td>
                  <td>2000 m</td>
                  <td>45:01</td>
                  <td>800 ca</td>
                  <td>100 m/min</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    
    
			<span id="signinButton" style="display:none">
			  <span
			    class="g-signin"
			    data-callback="signinCallback"
			    data-clientid="799362622292-cisd7bgllvoo1pckcsm38smvl9ec1m60.apps.googleusercontent.com"
			    data-cookiepolicy="single_host_origin"
			    data-requestvisibleactions="http://schemas.google.com/AddActivity"
			    data-scope="https://www.googleapis.com/auth/plus.login">
			  </span>
			</span>
    
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="bootstrap-3.1.1/dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.1.1/docs/assets/js/docs.min.js"></script>
    
	<script type="text/javascript">
     signinCallback(authResult);
    </script>
    
  </body>
</html>