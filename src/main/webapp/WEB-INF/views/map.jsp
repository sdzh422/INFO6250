<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=HtHsmL5N3GQGwqVVFlUCvGQ0ktMGEuOb"></script>
	<title>LocatePackage</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(-71.062,42.348);
	map.centerAndZoom(point,12);
	var myGeo = new BMap.Geocoder();
	myGeo.getPoint("波士顿公园", function(point){
		if (point) {
			map.centerAndZoom(point, 16);
			map.addOverlay(new BMap.Marker(point));
		}else{
			alert("no result");
		}
	}, "波士顿");
</script>