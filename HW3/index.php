<?php
$response = "";
$response1 = "";
$flag = 0;
$city = "";
if ($_SERVER["REQUEST_METHOD"] == "POST" || $_SERVER["REQUEST_METHOD"] == "GET"){

  if(isset($_POST["weather_street"]) || isset($_POST['weather_location']) || isset($_POST["time"])) {
    $flag = 0;
    $lat = 0;
    $lon = 0;
    if(isset($_POST["weather_street"])){
    $street_name = $_POST["weather_street"];
    $street_name = urlencode($street_name);

    $city_name = $_POST["weather_city"];
    $city_name = urlencode($city_name);

    $state_list = $_POST["state_list"];

    $flag = 1;
  }

    if($flag == 0){
    $current_location = "true";
    }
    else{
      $current_location = "false";
    }


    if($current_location == "false"){
      $url = "https://maps.googleapis.com/maps/api/geocode/xml?address=".$street_name."%2C%20".$city_name."%2C%20".$state_list."&key=AIzaSyBPAY2kuASyh31SAOWb8z6pmXgDjKr02jA";
     $xml = simplexml_load_file($url) or die("URL Not Found");

     $lat = $xml->result->geometry->location->lat;
     $lon = $xml->result->geometry->location->lng;

     $url2 = "https://api.forecast.io/forecast/882e09751dc983842f7405e28e658fea/".$lat."%2C%20".$lon."?exclude=minutely,hourly,alerts,flags";


      }
    else if($current_location == "true"){
    global $data;

  if(isset($_POST["current_lat"]) && isset($_POST["current_lon"]) && isset($_POST["current_city"])){
     $lat = $_POST["current_lat"];
     $lon = $_POST["current_lon"];
     $data = $_POST["current_city"];
  }
  else{

   $url = "http://ip-api.com/json";
  $json = file_get_contents($url);


    $data = json_decode($json, true);
    $lat =  $data['lat'];
    $lon =  $data['lon'];
}

#   echo '<script type="text/javascript">', 'get_json();', '</script>';

  # if(isset($_POST["lat"])){
#   $lat = $_POST["lat"];
#   }
#   if(isset($_POST["lon"])){
#     $lon = $_POST["lon"];
#  }

  #  $json = file_get_contents('php://input');

  #   $data = json_decode($json, true);

  #   $lat =  $data['lat'];
  #   $lon =  $data['lon'];

    $url2 = "https://api.forecast.io/forecast/882e09751dc983842f7405e28e658fea/".$lat."%2C%20".$lon."?exclude=minutely,hourly,alerts,flags";
  #  $url2 = urlencode($url2);
      }

 $response = file_get_contents($url2);

 if(isset($_POST["time"])){
  $time = $_POST["time"];

  $url3 = "https://api.darksky.net/forecast/882e09751dc983842f7405e28e658fea/".$lat.",".$lon.",".$time."?exclude=minutely";
 global $response1;
  $response1 = file_get_contents($url3);
 echo $response1;
 }


  }


}

?>
<html>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<title>HW6</title>
<style type="text/css">
#weatherbox_container {
    width: 630px;
    height: 220px;
    position: absolute;
    left: 26%;
    top: 40px;
    border-radius: 10px;
    background-color: Green;
}
#state_list {
  width: 170px;
  border-radius: 6px;
  height: 19px;
}
.vertical_line {
  position:absolute;
  top:52px;
  left:350px;
  border-left: 4px solid white;
  border-radius: 6px;
  height:110px;
}
#result{
    top: 120px;
    position: relative;
}
#table_result {
  top: 440px;
  position: relative;
  margin-left: 0px;
}
#detail_weather {
  top: 85px;
  position: relative;

}
#graph{
  top:490px;
  position: relative;
  margin-left: -110px;
}
#current_weather_container {
  width: 434px;
  height: 290px;
  position: absolute;
  left: 0px;
  top: 0px;
  border-radius: 10px;
  background-color: #40E0D0;
}
#detail_weather_container{
  width: 420px;
  height: 363px;
  position: absolute;
  left: 7px;
  top: 62px;
  border-radius: 10px;
  background-color: #ADD8E6;
}
.search_details table{
    border: 2px solid #1E90FF;
}
td {
  border: 2px solid #1E90FF;
  color:white;
  font-weight: bold;
  text-align:center;
}
th{
  color:white;
}
.contrast { filter: contrast(160%); }
.grayscale {-webkit-filter: grayscale(300%); filter: grayscale(300%);}
.brightness {-webkit-filter: brightness(0.25); filter: brightness(0.25);}


</style>



</head>
<body>

<div id="weatherbox_container">
   <p style="margin-top:2px;margin-left:200px;color:white;font-size:230%;"><i>Weather Search </i></p>

   <form name="weatherbox_form" id="weatherbox" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>" method="post" onsubmit="return validateForm()">
     <input type="hidden" id="current_lat" name="current_lat" value="">
    <input type="hidden" id="current_lon" name="current_lon" value="">
 <input type="hidden" id="current_city" name="current_city" value="">


      <div>
    <span style="color:white; position:absolute; top:55px; left: 440px;">
    <input type="checkbox" name="weather_location" id="weather_location" class="container" onclick="check_func(this.checked)" value="current" <?php if(isset($_POST['weather_location']) && $_POST['weather_location']=="current") echo "checked"; ?>><b> Current Location </b>
  </span>
        </div>
        <div class="vertical_line"> </div>

         <span style="color:white; margin-left:36px; padding-right:7px; position:absolute; top:55px;"><b>Street</b></span>
         <span style="position:absolute; top:55px; left:83px"><input type="text" name="weather_street" id="weather_street" size="15" value="<?php echo isset($_POST['weather_street']) ? $_POST['weather_street'] : '' ;?>"></span>
       <br>
        <span style="color:white; margin-left:36px; padding-right:19px; position:absolute; top:85px;"><b>City</b></span>
        <span style="position:absolute; top:85px; left:83px"><input type="text" name="weather_city" id="weather_city" size="15" value="<?php echo isset($_POST['weather_city']) ? $_POST['weather_city'] : '' ;?>"></span>
      <br>
        <span style="color:white; margin-left:36px; padding-right:13px; position:absolute; top:115px;"><b>State</b></span>
        <span style="position:absolute; top:115px; left:76px"><select name="state_list" id="state_list"></span>

            <option value="States">State</option>
            <option disabled="disabled">---------------------------------</option>
            <option value="AL" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="AL") echo "selected";?>>Alabama</option>
            <option value="AK" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="AK") echo "selected";?>>Alaska</option>
            <option value="AZ" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="AZ") echo "selected";?>>Arizona</option>
            <option value="AR" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="AR") echo "selected";?>>Arkansas</option>
          <option value="CA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="CA") echo "selected";?>>California</option>
            <option value="CO" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="CO") echo "selected";?>>Colorado</option>
           <option value="CT" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="CT") echo "selected";?>>Connecticut</option>
            <option value="DE" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="DE") echo "selected";?>>Delaware</option>
           <option value="DC" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="DC") echo "selected";?>>District Of Columbia</option>
           <option value="FL" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="FL") echo "selected";?>>Florida</option>
          <option value="GA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="GA") echo "selected";?>>Georgia</option>
          <option value="HI" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="HI") echo "selected";?>>Hawaii</option>
         <option value="ID" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="ID") echo "selected";?>>Idaho</option>
         <option value="IL" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="IL") echo "selected";?>>Illinois</option>
         <option value="IN" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="IN") echo "selected";?>>Indiana</option>
         <option value="IA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="IA") echo "selected";?>>Iowa</option>
         <option value="KS" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="KS") echo "selected";?>>Kansas</option>
         <option value="KY" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="KY") echo "selected";?>>Kentucky</option>
         <option value="LA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="LA") echo "selected";?>>Louisiana</option>
         <option value="ME" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="ME") echo "selected";?>>Maine</option>
         <option value="MD" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MD") echo "selected";?>>Maryland</option>
         <option value="MA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MA") echo "selected";?>>Massachusetts</option>
         <option value="MI" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MI") echo "selected";?>>Michigan</option>
         <option value="MN" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MN") echo "selected";?>>Minnesota</option>
         <option value="MS" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MS") echo "selected";?>>Mississippi</option>
         <option value="MO" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MO") echo "selected";?>>Missouri</option>
         <option value="MT" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="MT") echo "selected";?>>Montana</option>
         <option value="NE" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NE") echo "selected";?>>Nebraska</option>
         <option value="NV" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NV") echo "selected";?>>Nevada</option>
         <option value="NH" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NH") echo "selected";?>>New Hampshire</option>
         <option value="NJ" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NJ") echo "selected";?>>New Jersey</option>
         <option value="NM" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NM") echo "selected";?>>New Mexico</option>
         <option value="NY" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NY") echo "selected";?>>New York</option>
         <option value="NC" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="NC") echo "selected";?>>North Carolina</option>
         <option value="ND" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="ND") echo "selected";?>>North Dakota</option>
         <option value="OH" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="OH") echo "selected";?>>Ohio</option>
         <option value="OK" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="OK") echo "selected";?>>Oklahoma</option>
         <option value="OR" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="OR") echo "selected";?>>Oregon</option>
         <option value="PA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="PA") echo "selected";?>>Pennsylvania</option>
         <option value="RI" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="RI") echo "selected";?>>Rhode Island</option>
         <option value="SC" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="SC") echo "selected";?>>South Carolina</option>
         <option value="SD" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="SD") echo "selected";?>>South Dakota</option>
         <option value="TN" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="TN") echo "selected";?>>Tennessee</option>
         <option value="TX" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="TX") echo "selected";?>>Texas</option>
         <option value="UT" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="UT") echo "selected";?>>Utah</option>
         <option value="VT" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="VT") echo "selected";?>>Vermont</option>
         <option value="VA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="VA") echo "selected";?>>Virginia</option>
         <option value="WA" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="WA") echo "selected";?>>Washington</option>
         <option value="WV" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="WV") echo "selected";?>>West Virginia</option>
         <option value="WI" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="WI") echo "selected";?>>Wisconsin</option>
         <option value="WY" <?php if (isset($_POST['state_list']) && $_POST['state_list']=="WY") echo "selected";?>>Wyoming</option>

     </select>
     <div>
     <input type="submit" name="search_for_weather" value="search" id="search_button" style="border-radius: 3px;background-color: white; border:1px solid #D3D3D3;font-size: 12px; position:absolute; top:60px; left:142px;">
     <input type="button" name="clear_button" value="clear" id="clear_button" onclick="clearall();" style="border-radius: 3px;background-color: white; border:1px solid #D3D3D3;font-size: 12px; position:absolute; top:60px; left:198px;">
   </div>
</form>

<div id="error_msg"> </div>
<div id="result"> </div>
<div id="table_result"> </div>
<div id="detail_weather"> </div>
<div id="open_symbol" style="cursor: pointer;"> </div>
<div id="close_symbol" style="cursor: pointer;"> </div>
<div id="graph" style="display:none;"> </div>



</div>

</body>
<script type="text/javascript">

var current_weather_parse;

var xmlhttp1 = new XMLHttpRequest();
xmlhttp1.overrideMimeType("application/json");
xmlhttp1.open('GET',"http://ip-api.com/json", false);
xmlhttp1.send();
var response1;
response1 = xmlhttp1.responseText;
current_weather_parse = JSON.parse(response1);
var data = JSON.stringify(current_weather_parse);
var lat = current_weather_parse['lat'];
var lon = current_weather_parse['lon'];
var city = current_weather_parse['city'];

console.log(lat);
console.log(lon);

document.getElementById("current_lat").value = lat;
document.getElementById("current_lon").value = lon;
document.getElementById("current_city").value = city;

var xmlhttp2 = new XMLHttpRequest();
xmlhttp2.open("POST","index.php", false);
xmlhttp2.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
//xmlhttp2.open('GET',"HW6_Demo_2.php?"+"lat="+lat+"&lon="+lon, false);

xmlhttp2.send(current_weather_parse);
//xmlhttp2.send("lat="+encodeURIComponent(lat)+"&lon="+encodeURIComponent(lon));

</script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
</script>

<script type="text/javascript">
google.charts.load('current', {packages: ['corechart', 'line']});

function check_func(bEnable){
if(document.getElementById('weather_location').checked == true){
  document.getElementById('weather_street').value="";
    document.getElementById('weather_street').disabled=true;
    document.getElementById('weather_city').value="";
    document.getElementById('weather_city').disabled=true;
    document.getElementById('state_list').value="States";
    document.getElementById('state_list').disabled=true;

}
else{
  document.getElementById('weather_street').disabled=false;
  document.getElementById('weather_city').disabled=false;
  document.getElementById('state_list').disabled=false;
}
}
function clearall(){
document.getElementById('weather_street').disabled=false;
document.getElementById('weather_city').disabled=false;
document.getElementById('state_list').disabled=false;
document.getElementById('weather_street').value="";
document.getElementById('weather_city').value="";
document.getElementById('state_list').value="States";
document.getElementById('weather_location').checked=false;
document.getElementById('error_msg').innerHTML="";
document.getElementById('result').innerHTML="";
document.getElementById('table_result').innerHTML="";
document.getElementById('detail_weather').innerHTML="";

document.getElementById('open_symbol').innerHTML="";
document.getElementById('close_symbol').innerHTML="";
document.getElementById('graph').innerHTML="";
}
validateForm = function(e){
var x=document.forms["weatherbox_form"]["weather_street"].value;
var y=document.forms["weatherbox_form"]["weather_city"].value;
var z=document.forms["weatherbox_form"]["state_list"].value;
if(document.getElementById('weather_street').disabled == false && document.getElementById('weather_city').disabled == false && document.getElementById('state_list').disabled == false){
if(x == "" || y == "" || z == ""){
  var str = "<div style='height:20px;width:305px;margin-left:70px;margin-top:110px;background-color: #F9F9F9;border: 3px solid #A9A9A9;'>" + "<p style='padding-left:52px;margin-top:0px;'>" + "Please check the input address." + "</p>" + "</div>";
  document.getElementById('error_msg').innerHTML = str;
  return false;

}
}
if(document.getElementById('weather_location').checked == true){
  document.getElementById('weather_street').disabled=true;
  document.getElementById('weather_city').disabled=true;
  document.getElementById('state_list').disabled=true;
}
}
function extend_details(){
document.getElementById('open_symbol').innerHTML="";
var up_arrow = "";
up_arrow = '<span style="position:absolute; top:626px; left:220px;"><img src="https://csci571.com/hw/hw6/images/arrow_up.png" id="up_arrow" width="23px" height="15px" class="brightness" onclick="reduce_details()"> </img></span>';
document.getElementById('close_symbol').innerHTML = up_arrow;

var x = document.getElementById('graph');
if(x.style.display == "none"){
  x.style.display = "block";
}
}
function reduce_details(){
  document.getElementById('close_symbol').innerHTML="";
  var down_arrow = "";
  down_arrow = '<span style="position:absolute; top:626px; left:220px;"><img src="https://csci571.com/hw/hw6/images/arrow_down.png" id="down_arrow" width="23px" height="15px" class="brightness" onclick="extend_details()"> </img></span>';
  document.getElementById('open_symbol').innerHTML = down_arrow;

  var x = document.getElementById('graph');
  if(x.style.display == "block"){
    x.style.display = "none";
  }
}


function more_details(time){
  document.getElementById('result').innerHTML="";
  document.getElementById('table_result').innerHTML="";
//document.getElementById("time").value = time;
//document.getElementById("weatherbox").submit();
//  document.getElementById("weather_time").value = time;

var xmlhttp = new XMLHttpRequest();
xmlhttp.open("POST","index.php", false);
xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//xmlhttp2.open('GET',"HW6_Demo_2.php?"+"lat="+lat+"&lon="+lon, false);

xmlhttp.send("time="+time);
var response;
if(xmlhttp.readyState==4 && xmlhttp.status=="200"){
  response = xmlhttp.responseText;
}
//console.log(response);
//var cut = response.substring(0, 9084);
//var cut = response.split('<html>');
var cut = response.substring(0, response.indexOf("<"));
//console.log(cut);
var res1 = <?php
if($response1 == "")
  echo("null");
else {
  echo(json_encode($response1));
}
 ?>;
 //console.log(res1);
//console.log(cut);
   var detail_data = JSON.parse(cut);
//console.log(detail_data);
   var detail_content = "";
   detail_content = '<h1 style="position:relative; top:0px; left: 70px;">Daily Weather Detail</h1>';
    detail_content += '<div id="detail_weather_container">';
  var detail_summary = detail_data['currently']['summary'];
 detail_content += '<div id="summary2" style="padding-top:60px">' + '<span style="color:white; font-size: 210%; margin-left:15px;"><b>'+ detail_summary + '</b></span>' + '</div>';
detail_content += '<div id="temperature2" style="padding-top:3px">' + '<span style="color:white; font-size: 450%; margin-left:15px;">' + '<b>' + detail_data['currently']['temperature'] + '</b>' + '</span>';
detail_content += '<span style="position:absolute; top:105px;"><img src="https://cdn3.iconfinder.com/data/icons/virtual-notebook/16/button_shape_oval-512.png" id="degree_sign" width="13px" height="13px"> </img></span>';
detail_content += '<span style="color:white; font-size: 390%; margin-left:1px;"><b> F </b></span></div>';
var icon = detail_data['currently']['icon'];
if(icon == "clear-day" || icon == "clear-night"){
  detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/sun-512.png" id="sun_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "rain"){
  detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/rain-512.png" id="rain_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "snow"){
  detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/snow-512.png" id="rain_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "sleet"){
  detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/lightning-512.png" id="lighting_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "wind"){
detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn4.iconfinder.com/data/icons/the-weather-is-nice-today/64/weather_10-512.png" id="lighting_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "fog"){
  detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/cloudy-512.png" id="fog_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "cloudy"){
  detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/cloud-512.png" id="fog_sign" width="170px" height="170px"> </img></span>';
}
else if(icon == "partly-cloudy-day" || icon == "partly-cloudy-night"){
    detail_content += '<span style="position:absolute; top:-2px; margin-left:229px;"><img src="https://cdn3.iconfinder.com/data/icons/weather-344/142/sunny-512.png" id="fog_sign" width="170px" height="170px"> </img></span>';
}


var pre="";
var precipitation = detail_data['currently']['precipIntensity'];
if(precipitation < 0.001 || precipitation == 0.001){
  pre = "None";
}
else if(precipitation > 0.001 && precipitation <=0.015){
  pre = "Very Light";
}
else if(precipitation > 0.015 && precipitation <= 0.05){
  pre = "Light";
}
else if(precipitation > 0.05 && precipitation <= 0.1){
  pre = "Moderate";
}
else if(precipitation > 0.1){
  pre = "Heavy";
}
detail_content += '<div id="precip" style="top:67px">' + '<span style="color:white; font-size: 20px; margin-left:160px;"><b>Precipitation:</b></span>';
detail_content += '<span style="color:white; font-size: 22px; margin-left:2px; top:1px;"><b>'+pre+'</b></span>' + '</div>';
var chance_rain = detail_data['currently']['precipProbability'];
detail_content += '<div id="precip" style="top:87px">' + '<span style="color:white; font-size: 20px; margin-left:138px;"><b>Chance of Rain:</b></span>';
detail_content += '<span style="color:white; font-size: 24px; margin-left:2px; top:2px;"><b>'+Math.round(chance_rain*100)+'</b></span><span style="color:white; font-size: 20px; margin-left:2px;"><b>%</b></span>' +'</div>';
var wind = detail_data['currently']['windSpeed'];
detail_content += '<div id="precip" style="top:107px">' + '<span style="color:white; font-size: 20px; margin-left:167px;"><b>Wind Speed:</b></span>';
detail_content += '<span style="color:white; font-size: 24px; margin-left:2px; top:2px;"><b>'+wind+'</b></span><span style="color:white; font-size: 20px; margin-left:2px;"><b>mph</b></span>' +'</div>';
var humid =  detail_data['currently']['humidity'];
detail_content += '<div id="precip" style="top:127px">' + '<span style="color:white; font-size: 20px; margin-left:186px;"><b>Humidity:</b></span>';
detail_content += '<span style="color:white; font-size: 24px; margin-left:2px; top:2px;"><b>'+Math.round(humid*100)+'</b></span><span style="color:white; font-size: 20px; margin-left:2px;"><b>%</b></span>' +'</div>';
var visible = detail_data['currently']['visibility'];
detail_content += '<div id="precip" style="top:147px">' + '<span style="color:white; font-size: 20px; margin-left:190px;"><b>Visibility:</b></span>';
detail_content += '<span style="color:white; font-size: 24px; margin-left:2px; top:2px;"><b>'+visible+'</b></span><span style="color:white; font-size: 20px; margin-left:2px;"><b>mi</b></span>' +'</div>';

var sunrise_time = detail_data['daily']['data']['0']['sunriseTime'];
var sunset_time = detail_data['daily']['data']['0']['sunsetTime'];
sunrise_time = sunrise_time*1000;
sunset_time = sunset_time*1000;
var sunrise_hours = new Date(sunrise_time).getHours();
var sunset_hours = new Date(sunset_time).getHours();

var ampm1 = sunrise_hours >= 12 ? 'PM' : 'AM';
sunrise_hours = sunrise_hours % 12;
sunrise_hours = sunrise_hours ? sunrise_hours : 12;

var ampm2 = sunset_hours >= 12 ? 'PM' : 'AM';
sunset_hours = sunset_hours % 12;
sunset_hours = sunset_hours ? sunrise_hours : 12;

detail_content += '<div id="precip" style="top:167px">' + '<span style="color:white; font-size: 20px; margin-left:130px;"><b>Sunrise / Sunset:</b></span>';
detail_content += '<span style="color:white; font-size: 24px; margin-left:2px; top:2px;"><b>'+sunrise_hours+'</b></span><span style="color:white; font-size: 19px; margin-left:2px;"><b>'+ampm1+'/</b></span>';
detail_content += '<span style="color:white; font-size: 24px; margin-left:2px; top:2px;"><b>'+sunset_hours+'</b></span><span style="color:white; font-size: 19px; margin-left:2px;"><b>'+ampm2+'</b></span>' +'</div>';


   detail_content += '</div>';
   detail_content += '<h1 style="position:relative; top:380px; left: 70px;">Day\'s Hourly Weather</h1>';
   document.getElementById('detail_weather').innerHTML = detail_content;

var down_arrow = "";
down_arrow = '<span style="position:absolute; top:626px; left:220px;"><img src="https://csci571.com/hw/hw6/images/arrow_down.png" id="down_arrow" width="23px" height="15px" class="brightness" onclick="extend_details()"> </img></span>';
document.getElementById('open_symbol').innerHTML = down_arrow;

 //var graph_content = "";

 google.charts.setOnLoadCallback(drawBasic);

 function drawBasic(){
   var data = new google.visualization.DataTable();
   data.addColumn('number', 'Time');
   data.addColumn('number', 'T');
 var len = detail_data['hourly']['data'].length;
   var input_data = [];
   for(var i=0; i<len; i++){
     input_data.push(detail_data['hourly']['data'][i]['temperature']);
     }
  // console.log(input_data);
    for(var k=0; k<len; k++){
      var temp = [];
      temp.push(k);
      temp.push(input_data[k]);
      data.addRow(temp);
    }

//console.log(input_data);
/*data.addRows([
      [0, 0],   [1, 50],  [2, 23],  [3, 17],  [4, 18],  [5, 9],
      [6, 11],  [7, 27],  [8, 33],  [9, 40],  [10, 32], [11, 35],
      [12, 30], [13, 40], [14, 42], [15, 47], [16, 44], [17, 48],
      [18, 52], [19, 54], [20, 42], [21, 55], [22, 56], [23, 57],
      [24, 60], [25, 50], [26, 52], [27, 51], [28, 49], [29, 53],
      [30, 55], [31, 60], [32, 61], [33, 59], [34, 62], [35, 65],
      [36, 62], [37, 58], [38, 55], [39, 61], [40, 64], [41, 65],
      [42, 63], [43, 66], [44, 67], [45, 69], [46, 69], [47, 70],
      [48, 72], [49, 68], [50, 66], [51, 65], [52, 67], [53, 70],
      [54, 71], [55, 72], [56, 73], [57, 75], [58, 70], [59, 68],
      [60, 64], [61, 60], [62, 65], [63, 67], [64, 68], [65, 69],
      [66, 70], [67, 72], [68, 75], [69, 80]
    ]); */

   var options = {
        hAxis: {
          title: 'Time'

        },
        vAxis: {
          title: 'Temperature'
        },
        series:{
        0: {color: '#87CEEB'}
      },
      width:700,
      height:200
      };

      var chart = new google.visualization.LineChart(document.getElementById('graph'));

      chart.draw(data, options);

 }




 //document.getElementById('graph').innerHTML = graph_content;
}


var res = <?php
if($response == "")
  echo("null");
else
  echo(json_encode($response));
 ?>;
if(res != null){

var weather_data = JSON.parse(res);

<?php
global $data;
if($flag == 1){
 $city = $_POST["weather_city"];
}
else if($flag == 0){
 $city = $data;
#  $city = $data['city'];
}
 ?>
var weather_content = "";
//var flag = <?php echo $flag; ?>;
//if(flag==1){
//var city = <?php echo $city; ?>;
//}
//if(flag==0){
//var city = current_weather_parse['city'];
//}
var city = "<?php echo $city; ?>";
weather_content = '<div id="current_weather_container">';
weather_content += '<div id="city_name" style="padding-top:20px;">';
weather_content += '<span style="color:white; font-size: 200%; padding-top:20px; margin-left:15px;">' + '<b>';
weather_content += city + '</b>' + '</span>' + '</div>';
weather_content += '<div id="time_zone" style="padding-top:3px">' + '<span style="color:white; font-size: 13px; margin-left:15px;">'+ weather_data['timezone'] + '</span>' + '</div>';
weather_content += '<div id="temperature" style="padding-top:2px">' + '<span style="color:white; font-size: 450%; margin-left:15px;">' + '<b>' + weather_data['currently']['temperature'] + '</b>' + '</span>';
weather_content += '<span style="position:absolute; top:85px;"><img src="https://cdn3.iconfinder.com/data/icons/virtual-notebook/16/button_shape_oval-512.png" id="degree_sign" width="14px" height="14px"> </img></span>';
weather_content += '<span style="color:white; font-size: 240%; margin-left:10px;"><b> F </b></span></div>';
weather_content += '<div id="summary" style="padding-top:10px">'+'<span style="color:white; font-size: 210%; margin-left:15px;"><b>'+ weather_data['currently']['summary']+'</b></span></div>';
weather_content += '<span style="position:absolute; top:217px; margin-left:23px;"><img src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-16-512.png" id="humidity_sign" width="21px" height="21px" title="Humidity"> </img></span>';
weather_content += '<span style="position:absolute; top:217px; margin-left:97px;"><img src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-25-512.png" id="humidity_sign" width="21px" height="21px" title="Pressure"> </img></span>';
weather_content += '<span style="position:absolute; top:217px; margin-left:165px;"><img src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-27-512.png" id="humidity_sign" width="21px" height="21px" title="Wind Speed"> </img></span>';
weather_content += '<span style="position:absolute; top:217px; margin-left:240px;"><img src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-30-512.png" id="humidity_sign" width="21px" height="21px" title="Visibility"> </img></span>';
weather_content += '<span style="position:absolute; top:217px; margin-left:311px;"><img src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-28-512.png" id="humidity_sign" width="21px" height="21px" title="Cloud Cover"> </img></span>';
weather_content += '<span style="position:absolute; top:217px; margin-left:382px;"><img src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-24-512.png" id="humidity_sign" width="21px" height="21px" title="Ozone"> </img></span>';
var hum;
var press;
var wind;
var vis;
var cloud;
var ozone;
if(weather_data['currently'].hasOwnProperty("humidity")){
  hum =  weather_data['currently']['humidity'];
}
else {
  hum = "N/A";
}
if(weather_data['currently'].hasOwnProperty("pressure")){
  press =  weather_data['currently']['pressure'];
}
else {
  press = "N/A";
}
if(weather_data['currently'].hasOwnProperty("windSpeed")){
  wind =  weather_data['currently']['windSpeed'];
}
else {
  wind = "N/A";
}
if(weather_data['currently'].hasOwnProperty("visibility")){
  vis =  weather_data['currently']['visibility'];
}
else {
  vis = "N/A";
}
if(weather_data['currently'].hasOwnProperty("cloudCover")){
  cloud =  weather_data['currently']['cloudCover'];
}
else {
  cloud = "N/A";
}
if(weather_data['currently'].hasOwnProperty("ozone")){
  ozone =  weather_data['currently']['ozone'];
}
else {
  ozone = "N/A";
}
weather_content += '<div class="humidity_text" style="padding-top:1px">'+'<span style="color:white; font-size: 130%; margin-left:17px; position:absolute;top:240px;"><b>'+hum+'</b></span></div>';
weather_content += '<div id="pressure_text" style="padding-top:1px">'+'<span style="color:white; font-size: 130%; margin-left:73px; position:absolute;top:240px;"><b>'+press+'</b></span></div>';
weather_content += '<div id="wind_text" style="padding-top:1px">'+'<span style="color:white; font-size: 130%; margin-left:160px; position:absolute;top:240px;"><b>'+wind +'</b></span></div>';
weather_content += '<div id="visibility_text" style="padding-top:1px">'+'<span style="color:white; font-size: 130%; margin-left:235px; position:absolute;top:240px;"><b>'+vis +'</b></span></div>';
weather_content += '<div id="cloud_text" style="padding-top:1px">'+'<span style="color:white; font-size: 130%; margin-left:312px; position:absolute;top:240px;"><b>'+ cloud+'</b></span></div>';
weather_content += '<div id="ozone_text" style="padding-top:1px">'+'<span style="color:white; font-size: 130%; margin-left:370px; position:absolute;top:240px;"><b>'+ozone +'</b></span></div>';

weather_content += '</div>';
document.getElementById('result').innerHTML = weather_content;

var table_content = "";
table_content = '<table class="search_details" border="1 solid #1E90FF" style="border-collapse: collapse;width:950px;margin-left: -240px; background-color: #87CEEB">';
table_content += "<tr>";
table_content += "<th style='border: 2px solid #1E90FF;'>Date</th>";
table_content += "<th style='border: 2px solid #1E90FF;'>Status</th>";
table_content += "<th style='border: 2px solid #1E90FF;'>Summary</th>";
table_content += "<th style='border: 2px solid #1E90FF;'>TemperatureHigh</th>";
table_content += "<th style='border: 2px solid #1E90FF;'>TemperatureLow</th>";
table_content += "<th style='border: 2px solid #1E90FF;'>Wind Speed</th>";
table_content += "</tr>";
var len = weather_data['daily']['data'].length;
var index_array = [];

for(var i=0; i<len; i++){
  index_array[i] = weather_data['daily']['data'][i]['time'];
table_content += "<tr style='border: 2px solid #1E90FF;'>";
var date = weather_data['daily']['data'][i]['time'];
date = date*1000;
var year = new Date(date).getFullYear();
var month = new Date(date).getMonth()+1;
var day = new Date(date).getDate();
var status = weather_data['daily']['data'][i]['icon'];
table_content += "<td>"+year+"-"+month+"-"+day+"</td>";
if(status == "clear-day" || status == "clear-night"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-12-512.png"> </td>';
}
else if(status == "rain"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-04-512.png"> </td>';
}
else if(status == "snow"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-19-512.png"> </td>';
}
else if(status == "sleet"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-07-512.png"> </td>';
}
else if(status == "wind"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-27-512.png"> </td>';
}
else if(status == "fog"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-28-512.png"> </td>';
}
else if(status == "cloudy"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-01-512.png"> </td>';
}
else if(status == "partly-cloudy-day" || status == "partly-cloudy-night"){
  table_content += '<td style="height:30px;width:30px"><img style="height:60px;width:60px" src="https://cdn2.iconfinder.com/data/icons/weather-74/24/weather-02-512.png"> </td>';
}

var summary = weather_data['daily']['data'][i]['summary'];
table_content += '<td id="more_details" style="cursor: pointer;" onclick="more_details('+index_array[i]+')">' + summary + '</td>';
var tempHigh = weather_data['daily']['data'][i]['temperatureHigh'];
table_content += '<td>' + tempHigh + '</td>';
var tempLow = weather_data['daily']['data'][i]['temperatureLow'];
table_content += '<td>' + tempLow + '</td>';
var wind_speed = weather_data['daily']['data'][i]['windSpeed'];
table_content += '<td>' + wind_speed + '</td>';

table_content += '</tr>';


}
table_content += '</table>';


document.getElementById('table_result').innerHTML = table_content;

}

</script>

</html>
