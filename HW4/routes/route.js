const express = require('express');
const router = express.Router();
var req_hit = require('request-promise');
const https = require('https');
var http = require('http');

router.get('/contacts',(req,res)=>{
    res.send('Retrieval');
});

router.get('/autosuggest', (req, res) => {
    let x = req.query.keyword;

    let url = 'https://maps.googleapis.com/maps/api/place/autocomplete/json?input='+encodeURI(x)+'&types=(cities)&language=en&key=AIzaSyAV1LfzB-1GvmcTuBofHtB-cWD6-kXlLMs';
   //let url = 'https://maps.googleapis.com/maps/api/place/autocomplete/json?input=LOS&types=(cities)&language=en&key=AIzaSyAV1LfzB-1GvmcTuBofHtB-cWD6-kXlLMs';

    let body = getContent(url);
    body.then(function(data) {
        res.header("Content-Type",'application/json');
        let response = [];
       // console.log(data);
        //console.log("Hello");
        if(data["predictions"]) {
            let resp = data["predictions"];
            for (var i =0;i<resp.length;i++) {
                let tup = {};
                tup.city = resp[i]['structured_formatting']['main_text'];
                response.push(tup);   
            }
        }
        res.send(JSON.stringify(response));
        }).catch(err => {
            console.log(err);
            res.status(503).send(err);
            
    })

}); 

router.get('/forcast', (req, res) => {
    let x = req.query.latitude;
    let y = req.query.longitude;
    let z = req.query.city;
    let w = req.query.state;
    let url = 'https://api.darksky.net/forecast/882e09751dc983842f7405e28e658fea/'+encodeURI(x)+','+encodeURI(y);
    let body = getContent(url);
    body.then(function(data) {
        res.header("Content-Type",'application/json');
        let response = [];
        let tup = {};
        tup.city = z;
        tup.state = w;
        tup.timezone = data['timezone'];
        tup.temperature = data['currently']['temperature'];
        tup.summary = data['currently']['summary'];
        tup.humidity = data['currently']['humidity'];
        tup.pressure = data['currently']['pressure'];
        tup.windspeed = data['currently']['windSpeed'];
        tup.visibility = data['currently']['visibility'];
        tup.cloudCover = data['currently']['cloudCover'];
        tup.ozone = data['currently']['ozone'];
        tup.precip = data['currently']['precipIntensity'];

       tup.dailyIcon = data['daily']['icon'];
       tup.dailySummary = data['daily']['summary'];

        tup.time1 = data['daily']['data'][0]['time'];
        tup.time2 = data['daily']['data'][1]['time'];
        tup.time3 = data['daily']['data'][2]['time'];
        tup.time4 = data['daily']['data'][3]['time'];
        tup.time5 = data['daily']['data'][4]['time'];
        tup.time6 = data['daily']['data'][5]['time'];
        tup.time7 = data['daily']['data'][6]['time'];
        tup.time8 = data['daily']['data'][7]['time'];  

        tup.tempLow1 = data['daily']['data'][0]['temperatureLow'];
        tup.tempLow2 = data['daily']['data'][1]['temperatureLow'];
        tup.tempLow3 = data['daily']['data'][2]['temperatureLow'];
        tup.tempLow4 = data['daily']['data'][3]['temperatureLow'];
        tup.tempLow5 = data['daily']['data'][4]['temperatureLow'];
        tup.tempLow6 = data['daily']['data'][5]['temperatureLow'];
        tup.tempLow7 = data['daily']['data'][6]['temperatureLow'];
        tup.tempLow8 = data['daily']['data'][7]['temperatureLow'];

        tup.tempHigh1 = data['daily']['data'][0]['temperatureHigh'];
        tup.tempHigh2 = data['daily']['data'][1]['temperatureHigh'];
        tup.tempHigh3 = data['daily']['data'][2]['temperatureHigh'];
        tup.tempHigh4 = data['daily']['data'][3]['temperatureHigh'];
        tup.tempHigh5 = data['daily']['data'][4]['temperatureHigh'];
        tup.tempHigh6 = data['daily']['data'][5]['temperatureHigh'];
        tup.tempHigh7 = data['daily']['data'][6]['temperatureHigh'];
        tup.tempHigh8 = data['daily']['data'][7]['temperatureHigh'];

        tup.icon = data['currently']['icon'];
        tup.icon1 = data['daily']['data'][0]['icon'];
        tup.icon2 = data['daily']['data'][1]['icon'];
        tup.icon3 = data['daily']['data'][2]['icon'];
        tup.icon4 = data['daily']['data'][3]['icon'];
        tup.icon5 = data['daily']['data'][4]['icon'];
        tup.icon6 = data['daily']['data'][5]['icon'];
        tup.icon7 = data['daily']['data'][6]['icon'];
        tup.icon8 = data['daily']['data'][7]['icon'];

        let url2 = 'https://www.googleapis.com/customsearch/v1?q='+encodeURI(tup.state)+'%20State%20Seal&cx=003410437470048956045:gvxfuame1cy&imgSize=huge&imgType=news&num=1&searchType=image&key=AIzaSyBYdmppskZfEB-Fmkeylim0NVFLh9o_TdQ';
        let body2 = getContent(url2);
        body2.then(function(data2) {
            res.header("Content-Type",'application/json');
            tup.link = data2['items'][0]['link'];            

        response.push(tup);
    
    res.send(JSON.stringify(response));
    })
   }).catch(err => {
    console.log(err);
    res.status(503).send(err);
    
   })
});

router.get('/forcast-chart', (req, res) => {
    let x = req.query.latitude;
    let y = req.query.longitude;
    let z = req.query.city;
    let w = req.query.state;
    let url = 'https://api.darksky.net/forecast/882e09751dc983842f7405e28e658fea/'+encodeURI(x)+','+encodeURI(y);
    let body = getContent(url);
    body.then(function(data) {
        res.header("Content-Type",'application/json');
        let response = [];
        
            let tup = {};
            tup.temp1 = data['hourly']['data'][0]['temperature'];
            tup.temp2 = data['hourly']['data'][1]['temperature'];
            tup.temp3 = data['hourly']['data'][2]['temperature'];
            tup.temp4 = data['hourly']['data'][3]['temperature'];
            tup.temp5 = data['hourly']['data'][4]['temperature'];
            tup.temp6 = data['hourly']['data'][5]['temperature'];
            tup.temp7 = data['hourly']['data'][6]['temperature'];
            tup.temp8 = data['hourly']['data'][7]['temperature'];
            tup.temp9 = data['hourly']['data'][8]['temperature'];
            tup.temp10 = data['hourly']['data'][9]['temperature'];
            tup.temp11 = data['hourly']['data'][10]['temperature'];
            tup.temp12 = data['hourly']['data'][11]['temperature'];
            tup.temp13 = data['hourly']['data'][12]['temperature'];
            tup.temp14 = data['hourly']['data'][13]['temperature'];
            tup.temp15 = data['hourly']['data'][14]['temperature'];
            tup.temp16 = data['hourly']['data'][15]['temperature'];
            tup.temp17 = data['hourly']['data'][16]['temperature'];
            tup.temp18 = data['hourly']['data'][17]['temperature'];
            tup.temp19 = data['hourly']['data'][18]['temperature'];
            tup.temp20 = data['hourly']['data'][19]['temperature'];
            tup.temp21 = data['hourly']['data'][20]['temperature'];
            tup.temp22 = data['hourly']['data'][21]['temperature'];
            tup.temp23 = data['hourly']['data'][22]['temperature'];
            tup.temp24 = data['hourly']['data'][23]['temperature'];

            tup.press1 = data['hourly']['data'][0]['pressure'];
            tup.press2 = data['hourly']['data'][1]['pressure'];
            tup.press3 = data['hourly']['data'][2]['pressure'];
            tup.press4 = data['hourly']['data'][3]['pressure'];
            tup.press5 = data['hourly']['data'][4]['pressure'];
            tup.press6 = data['hourly']['data'][5]['pressure'];
            tup.press7 = data['hourly']['data'][6]['pressure'];
            tup.press8 = data['hourly']['data'][7]['pressure'];
            tup.press9 = data['hourly']['data'][8]['pressure'];
            tup.press10 = data['hourly']['data'][9]['pressure'];
            tup.press11 = data['hourly']['data'][10]['pressure'];
            tup.press12 = data['hourly']['data'][11]['pressure'];
            tup.press13 = data['hourly']['data'][12]['pressure'];
            tup.press14 = data['hourly']['data'][13]['pressure'];
            tup.press15 = data['hourly']['data'][14]['pressure'];
            tup.press16 = data['hourly']['data'][15]['pressure'];
            tup.press17 = data['hourly']['data'][16]['pressure'];
            tup.press18 = data['hourly']['data'][17]['pressure'];
            tup.press19 = data['hourly']['data'][18]['pressure'];
            tup.press20 = data['hourly']['data'][19]['pressure'];
            tup.press21 = data['hourly']['data'][20]['pressure'];
            tup.press22 = data['hourly']['data'][21]['pressure'];
            tup.press23 = data['hourly']['data'][22]['pressure'];
            tup.press24 = data['hourly']['data'][23]['pressure'];

            tup.humid1 = data['hourly']['data'][0]['humidity'];
            tup.humid2 = data['hourly']['data'][1]['humidity'];
            tup.humid3 = data['hourly']['data'][2]['humidity'];
            tup.humid4 = data['hourly']['data'][3]['humidity'];
            tup.humid5 = data['hourly']['data'][4]['humidity'];
            tup.humid6 = data['hourly']['data'][5]['humidity'];
            tup.humid7 = data['hourly']['data'][6]['humidity'];
            tup.humid8 = data['hourly']['data'][7]['humidity'];
            tup.humid9 = data['hourly']['data'][8]['humidity'];
            tup.humid10 = data['hourly']['data'][9]['humidity'];
            tup.humid11 = data['hourly']['data'][10]['humidity'];
            tup.humid12 = data['hourly']['data'][11]['humidity'];
            tup.humid13 = data['hourly']['data'][12]['humidity'];
            tup.humid14 = data['hourly']['data'][13]['humidity'];
            tup.humid15 = data['hourly']['data'][14]['humidity'];
            tup.humid16 = data['hourly']['data'][15]['humidity'];
            tup.humid17 = data['hourly']['data'][16]['humidity'];
            tup.humid18 = data['hourly']['data'][17]['humidity'];
            tup.humid19 = data['hourly']['data'][18]['humidity'];
            tup.humid20 = data['hourly']['data'][19]['humidity'];
            tup.humid21 = data['hourly']['data'][20]['humidity'];
            tup.humid22 = data['hourly']['data'][21]['humidity'];
            tup.humid23 = data['hourly']['data'][22]['humidity'];
            tup.humid24 = data['hourly']['data'][23]['humidity'];

            tup.ozone1 = data['hourly']['data'][0]['ozone'];
            tup.ozone2 = data['hourly']['data'][1]['ozone'];
            tup.ozone3 = data['hourly']['data'][2]['ozone'];
            tup.ozone4 = data['hourly']['data'][3]['ozone'];
            tup.ozone5 = data['hourly']['data'][4]['ozone'];
            tup.ozone6 = data['hourly']['data'][5]['ozone'];
            tup.ozone7 = data['hourly']['data'][6]['ozone'];
            tup.ozone8 = data['hourly']['data'][7]['ozone'];
            tup.ozone9 = data['hourly']['data'][8]['ozone'];
            tup.ozone10 = data['hourly']['data'][9]['ozone'];
            tup.ozone11 = data['hourly']['data'][10]['ozone'];
            tup.ozone12 = data['hourly']['data'][11]['ozone'];
            tup.ozone13 = data['hourly']['data'][12]['ozone'];
            tup.ozone14 = data['hourly']['data'][13]['ozone'];
            tup.ozone15 = data['hourly']['data'][14]['ozone'];
            tup.ozone16 = data['hourly']['data'][15]['ozone'];
            tup.ozone17 = data['hourly']['data'][16]['ozone'];
            tup.ozone18 = data['hourly']['data'][17]['ozone'];
            tup.ozone19 = data['hourly']['data'][18]['ozone'];
            tup.ozone20 = data['hourly']['data'][19]['ozone'];
            tup.ozone21 = data['hourly']['data'][20]['ozone'];
            tup.ozone22 = data['hourly']['data'][21]['ozone'];
            tup.ozone23 = data['hourly']['data'][22]['ozone'];
            tup.ozone24 = data['hourly']['data'][23]['ozone'];

            tup.vis1 = data['hourly']['data'][0]['visibility'];
            tup.vis2 = data['hourly']['data'][1]['visibility'];
            tup.vis3 = data['hourly']['data'][2]['visibility'];
            tup.vis4 = data['hourly']['data'][3]['visibility'];
            tup.vis5 = data['hourly']['data'][4]['visibility'];
            tup.vis6 = data['hourly']['data'][5]['visibility'];
            tup.vis7 = data['hourly']['data'][6]['visibility'];
            tup.vis8 = data['hourly']['data'][7]['visibility'];
            tup.vis9 = data['hourly']['data'][8]['visibility'];
            tup.vis10 = data['hourly']['data'][9]['visibility'];
            tup.vis11 = data['hourly']['data'][10]['visibility'];
            tup.vis12 = data['hourly']['data'][11]['visibility'];
            tup.vis13 = data['hourly']['data'][12]['visibility'];
            tup.vis14 = data['hourly']['data'][13]['visibility'];
            tup.vis15 = data['hourly']['data'][14]['visibility'];
            tup.vis16 = data['hourly']['data'][15]['visibility'];
            tup.vis17 = data['hourly']['data'][16]['visibility'];
            tup.vis18 = data['hourly']['data'][17]['visibility'];
            tup.vis19 = data['hourly']['data'][18]['visibility'];
            tup.vis20 = data['hourly']['data'][19]['visibility'];
            tup.vis21 = data['hourly']['data'][20]['visibility'];
            tup.vis22 = data['hourly']['data'][21]['visibility'];
            tup.vis23 = data['hourly']['data'][22]['visibility'];
            tup.vis24 = data['hourly']['data'][23]['visibility'];

            tup.wind1 = data['hourly']['data'][0]['windSpeed'];
            tup.wind2 = data['hourly']['data'][1]['windSpeed'];
            tup.wind3 = data['hourly']['data'][2]['windSpeed'];
            tup.wind4 = data['hourly']['data'][3]['windSpeed'];
            tup.wind5 = data['hourly']['data'][4]['windSpeed'];
            tup.wind6 = data['hourly']['data'][5]['windSpeed'];
            tup.wind7 = data['hourly']['data'][6]['windSpeed'];
            tup.wind8 = data['hourly']['data'][7]['windSpeed'];
            tup.wind9 = data['hourly']['data'][8]['windSpeed'];
            tup.wind10 = data['hourly']['data'][9]['windSpeed'];
            tup.wind11 = data['hourly']['data'][10]['windSpeed'];
            tup.wind12 = data['hourly']['data'][11]['windSpeed'];
            tup.wind13 = data['hourly']['data'][12]['windSpeed'];
            tup.wind14 = data['hourly']['data'][13]['windSpeed'];
            tup.wind15 = data['hourly']['data'][14]['windSpeed'];
            tup.wind16 = data['hourly']['data'][15]['windSpeed'];
            tup.wind17 = data['hourly']['data'][16]['windSpeed'];
            tup.wind18 = data['hourly']['data'][17]['windSpeed'];
            tup.wind19 = data['hourly']['data'][18]['windSpeed'];
            tup.wind20 = data['hourly']['data'][19]['windSpeed'];
            tup.wind21 = data['hourly']['data'][20]['windSpeed'];
            tup.wind22 = data['hourly']['data'][21]['windSpeed'];
            tup.wind23 = data['hourly']['data'][22]['windSpeed'];
            tup.wind24 = data['hourly']['data'][23]['windSpeed'];
            response.push(tup);   
        
        res.send(JSON.stringify(response));
    }).catch(err => {
        console.log(err);
        res.status(503).send(err);
        
})
});


router.get('/getPosition', (req, res) => {
    let x = req.query.street;
    let y = req.query.city;
    let z = req.query.state;
    let url = 'https://maps.googleapis.com/maps/api/geocode/json?address=['+encodeURI(x)+','+encodeURI(y)+','+encodeURI(z)+']&key=AIzaSyBPAY2kuASyh31SAOWb8z6pmXgDjKr02jA';
    let body = getContent(url);
    body.then(function(data) {
        res.header("Content-Type",'application/json');
        let response = [];
        if(data["results"]){
           let tup = {};
           let tup2={};
           tup.latitude = data['results'][0]['geometry']['location']['lat'];
           tup.longitude = data['results'][0]['geometry']['location']['lng'];
          
           let url2 = 'https://api.darksky.net/forecast/882e09751dc983842f7405e28e658fea/'+encodeURI(tup.latitude)+','+encodeURI(tup.longitude);
           let body2 = getContent(url2);
           body2.then(function(data2) {
            res.header("Content-Type",'application/json');
           tup2.city = y;
           tup2.timezone = data2['timezone'];
        tup2.temperature = data2['currently']['temperature'];
        tup2.summary = data2['currently']['summary'];
        tup2.humidity = data2['currently']['humidity'];
        tup2.pressure = data2['currently']['pressure'];
        tup2.windspeed = data2['currently']['windSpeed'];
        tup2.visibility = data2['currently']['visibility'];
        tup2.cloudCover = data2['currently']['cloudCover'];
        tup2.ozone = data2['currently']['ozone'];
        tup2.precip = data2['currently']['precipIntensity'];

       tup2.dailyIcon = data2['daily']['icon'];
       tup2.dailySummary = data2['daily']['summary'];
        tup2.time1 = data2['daily']['data'][0]['time'];
        tup2.time2 = data2['daily']['data'][1]['time'];
        tup2.time3 = data2['daily']['data'][2]['time'];
        tup2.time4 = data2['daily']['data'][3]['time'];
        tup2.time5 = data2['daily']['data'][4]['time'];
        tup2.time6 = data2['daily']['data'][5]['time'];
        tup2.time7 = data2['daily']['data'][6]['time'];
        tup2.time8 = data2['daily']['data'][7]['time'];  

        tup2.tempLow1 = data2['daily']['data'][0]['temperatureLow'];
        tup2.tempLow2 = data2['daily']['data'][1]['temperatureLow'];
        tup2.tempLow3 = data2['daily']['data'][2]['temperatureLow'];
        tup2.tempLow4 = data2['daily']['data'][3]['temperatureLow'];
        tup2.tempLow5 = data2['daily']['data'][4]['temperatureLow'];
        tup2.tempLow6 = data2['daily']['data'][5]['temperatureLow'];
        tup2.tempLow7 = data2['daily']['data'][6]['temperatureLow'];
        tup2.tempLow8 = data2['daily']['data'][7]['temperatureLow'];

        tup2.tempHigh1 = data2['daily']['data'][0]['temperatureHigh'];
        tup2.tempHigh2 = data2['daily']['data'][1]['temperatureHigh'];
        tup2.tempHigh3 = data2['daily']['data'][2]['temperatureHigh'];
        tup2.tempHigh4 = data2['daily']['data'][3]['temperatureHigh'];
        tup2.tempHigh5 = data2['daily']['data'][4]['temperatureHigh'];
        tup2.tempHigh6 = data2['daily']['data'][5]['temperatureHigh'];
        tup2.tempHigh7 = data2['daily']['data'][6]['temperatureHigh'];
        tup2.tempHigh8 = data2['daily']['data'][7]['temperatureHigh'];

        tup2.icon = data2['currently']['icon'];  
        tup2.icon1 = data2['daily']['data'][0]['icon'];
        tup2.icon2 = data2['daily']['data'][1]['icon'];
        tup2.icon3 = data2['daily']['data'][2]['icon'];
        tup2.icon4 = data2['daily']['data'][3]['icon'];
        tup2.icon5 = data2['daily']['data'][4]['icon'];
        tup2.icon6 = data2['daily']['data'][5]['icon'];
        tup2.icon7 = data2['daily']['data'][6]['icon'];
        tup2.icon8 = data2['daily']['data'][7]['icon'];

            tup2.temp1 = data2['hourly']['data'][0]['temperature'];
            tup2.temp2 = data2['hourly']['data'][1]['temperature'];
            tup2.temp3 = data2['hourly']['data'][2]['temperature'];
            tup2.temp4 = data2['hourly']['data'][3]['temperature'];
            tup2.temp5 = data2['hourly']['data'][4]['temperature'];
            tup2.temp6 = data2['hourly']['data'][5]['temperature'];
            tup2.temp7 = data2['hourly']['data'][6]['temperature'];
            tup2.temp8 = data2['hourly']['data'][7]['temperature'];
            tup2.temp9 = data2['hourly']['data'][8]['temperature'];
            tup2.temp10 = data2['hourly']['data'][9]['temperature'];
            tup2.temp11 = data2['hourly']['data'][10]['temperature'];
            tup2.temp12 = data2['hourly']['data'][11]['temperature'];
            tup2.temp13 = data2['hourly']['data'][12]['temperature'];
            tup2.temp14 = data2['hourly']['data'][13]['temperature'];
            tup2.temp15 = data2['hourly']['data'][14]['temperature'];
            tup2.temp16 = data2['hourly']['data'][15]['temperature'];
            tup2.temp17 = data2['hourly']['data'][16]['temperature'];
            tup2.temp18 = data2['hourly']['data'][17]['temperature'];
            tup2.temp19 = data2['hourly']['data'][18]['temperature'];
            tup2.temp20 = data2['hourly']['data'][19]['temperature'];
            tup2.temp21 = data2['hourly']['data'][20]['temperature'];
            tup2.temp22 = data2['hourly']['data'][21]['temperature'];
            tup2.temp23 = data2['hourly']['data'][22]['temperature'];
            tup2.temp24 = data2['hourly']['data'][23]['temperature'];

            tup2.press1 = data2['hourly']['data'][0]['pressure'];
            tup2.press2 = data2['hourly']['data'][1]['pressure'];
            tup2.press3 = data2['hourly']['data'][2]['pressure'];
            tup2.press4 = data2['hourly']['data'][3]['pressure'];
            tup2.press5 = data2['hourly']['data'][4]['pressure'];
            tup2.press6 = data2['hourly']['data'][5]['pressure'];
            tup2.press7 = data2['hourly']['data'][6]['pressure'];
            tup2.press8 = data2['hourly']['data'][7]['pressure'];
            tup2.press9 = data2['hourly']['data'][8]['pressure'];
            tup2.press10 = data2['hourly']['data'][9]['pressure'];
            tup2.press11 = data2['hourly']['data'][10]['pressure'];
            tup2.press12 = data2['hourly']['data'][11]['pressure'];
            tup2.press13 = data2['hourly']['data'][12]['pressure'];
            tup2.press14 = data2['hourly']['data'][13]['pressure'];
            tup2.press15 = data2['hourly']['data'][14]['pressure'];
            tup2.press16 = data2['hourly']['data'][15]['pressure'];
            tup2.press17 = data2['hourly']['data'][16]['pressure'];
            tup2.press18 = data2['hourly']['data'][17]['pressure'];
            tup2.press19 = data2['hourly']['data'][18]['pressure'];
            tup2.press20 = data2['hourly']['data'][19]['pressure'];
            tup2.press21 = data2['hourly']['data'][20]['pressure'];
            tup2.press22 = data2['hourly']['data'][21]['pressure'];
            tup2.press23 = data2['hourly']['data'][22]['pressure'];
            tup2.press24 = data2['hourly']['data'][23]['pressure'];

            tup2.humid1 = data2['hourly']['data'][0]['humidity'];
            tup2.humid2 = data2['hourly']['data'][1]['humidity'];
            tup2.humid3 = data2['hourly']['data'][2]['humidity'];
            tup2.humid4 = data2['hourly']['data'][3]['humidity'];
            tup2.humid5 = data2['hourly']['data'][4]['humidity'];
            tup2.humid6 = data2['hourly']['data'][5]['humidity'];
            tup2.humid7 = data2['hourly']['data'][6]['humidity'];
            tup2.humid8 = data2['hourly']['data'][7]['humidity'];
            tup2.humid9 = data2['hourly']['data'][8]['humidity'];
            tup2.humid10 = data2['hourly']['data'][9]['humidity'];
            tup2.humid11 = data2['hourly']['data'][10]['humidity'];
            tup2.humid12 = data2['hourly']['data'][11]['humidity'];
            tup2.humid13 = data2['hourly']['data'][12]['humidity'];
            tup2.humid14 = data2['hourly']['data'][13]['humidity'];
            tup2.humid15 = data2['hourly']['data'][14]['humidity'];
            tup2.humid16 = data2['hourly']['data'][15]['humidity'];
            tup2.humid17 = data2['hourly']['data'][16]['humidity'];
            tup2.humid18 = data2['hourly']['data'][17]['humidity'];
            tup2.humid19 = data2['hourly']['data'][18]['humidity'];
            tup2.humid20 = data2['hourly']['data'][19]['humidity'];
            tup2.humid21 = data2['hourly']['data'][20]['humidity'];
            tup2.humid22 = data2['hourly']['data'][21]['humidity'];
            tup2.humid23 = data2['hourly']['data'][22]['humidity'];
            tup2.humid24 = data2['hourly']['data'][23]['humidity'];

            tup2.ozone1 = data2['hourly']['data'][0]['ozone'];
            tup2.ozone2 = data2['hourly']['data'][1]['ozone'];
            tup2.ozone3 = data2['hourly']['data'][2]['ozone'];
            tup2.ozone4 = data2['hourly']['data'][3]['ozone'];
            tup2.ozone5 = data2['hourly']['data'][4]['ozone'];
            tup2.ozone6 = data2['hourly']['data'][5]['ozone'];
            tup2.ozone7 = data2['hourly']['data'][6]['ozone'];
            tup2.ozone8 = data2['hourly']['data'][7]['ozone'];
            tup2.ozone9 = data2['hourly']['data'][8]['ozone'];
            tup2.ozone10 = data2['hourly']['data'][9]['ozone'];
            tup2.ozone11 = data2['hourly']['data'][10]['ozone'];
            tup2.ozone12 = data2['hourly']['data'][11]['ozone'];
            tup2.ozone13 = data2['hourly']['data'][12]['ozone'];
            tup2.ozone14 = data2['hourly']['data'][13]['ozone'];
            tup2.ozone15 = data2['hourly']['data'][14]['ozone'];
            tup2.ozone16 = data2['hourly']['data'][15]['ozone'];
            tup2.ozone17 = data2['hourly']['data'][16]['ozone'];
            tup2.ozone18 = data2['hourly']['data'][17]['ozone'];
            tup2.ozone19 = data2['hourly']['data'][18]['ozone'];
            tup2.ozone20 = data2['hourly']['data'][19]['ozone'];
            tup2.ozone21 = data2['hourly']['data'][20]['ozone'];
            tup2.ozone22 = data2['hourly']['data'][21]['ozone'];
            tup2.ozone23 = data2['hourly']['data'][22]['ozone'];
            tup2.ozone24 = data2['hourly']['data'][23]['ozone'];

            tup2.vis1 = data2['hourly']['data'][0]['visibility'];
            tup2.vis2 = data2['hourly']['data'][1]['visibility'];
            tup2.vis3 = data2['hourly']['data'][2]['visibility'];
            tup2.vis4 = data2['hourly']['data'][3]['visibility'];
            tup2.vis5 = data2['hourly']['data'][4]['visibility'];
            tup2.vis6 = data2['hourly']['data'][5]['visibility'];
            tup2.vis7 = data2['hourly']['data'][6]['visibility'];
            tup2.vis8 = data2['hourly']['data'][7]['visibility'];
            tup2.vis9 = data2['hourly']['data'][8]['visibility'];
            tup2.vis10 = data2['hourly']['data'][9]['visibility'];
            tup2.vis11 = data2['hourly']['data'][10]['visibility'];
            tup2.vis12 = data2['hourly']['data'][11]['visibility'];
            tup2.vis13 = data2['hourly']['data'][12]['visibility'];
            tup2.vis14 = data2['hourly']['data'][13]['visibility'];
            tup2.vis15 = data2['hourly']['data'][14]['visibility'];
            tup2.vis16 = data2['hourly']['data'][15]['visibility'];
            tup2.vis17 = data2['hourly']['data'][16]['visibility'];
            tup2.vis18 = data2['hourly']['data'][17]['visibility'];
            tup2.vis19 = data2['hourly']['data'][18]['visibility'];
            tup2.vis20 = data2['hourly']['data'][19]['visibility'];
            tup2.vis21 = data2['hourly']['data'][20]['visibility'];
            tup2.vis22 = data2['hourly']['data'][21]['visibility'];
            tup2.vis23 = data2['hourly']['data'][22]['visibility'];
            tup2.vis24 = data2['hourly']['data'][23]['visibility'];

            tup2.wind1 = data2['hourly']['data'][0]['windSpeed'];
            tup2.wind2 = data2['hourly']['data'][1]['windSpeed'];
            tup2.wind3 = data2['hourly']['data'][2]['windSpeed'];
            tup2.wind4 = data2['hourly']['data'][3]['windSpeed'];
            tup2.wind5 = data2['hourly']['data'][4]['windSpeed'];
            tup2.wind6 = data2['hourly']['data'][5]['windSpeed'];
            tup2.wind7 = data2['hourly']['data'][6]['windSpeed'];
            tup2.wind8 = data2['hourly']['data'][7]['windSpeed'];
            tup2.wind9 = data2['hourly']['data'][8]['windSpeed'];
            tup2.wind10 = data2['hourly']['data'][9]['windSpeed'];
            tup2.wind11 = data2['hourly']['data'][10]['windSpeed'];
            tup2.wind12 = data2['hourly']['data'][11]['windSpeed'];
            tup2.wind13 = data2['hourly']['data'][12]['windSpeed'];
            tup2.wind14 = data2['hourly']['data'][13]['windSpeed'];
            tup2.wind15 = data2['hourly']['data'][14]['windSpeed'];
            tup2.wind16 = data2['hourly']['data'][15]['windSpeed'];
            tup2.wind17 = data2['hourly']['data'][16]['windSpeed'];
            tup2.wind18 = data2['hourly']['data'][17]['windSpeed'];
            tup2.wind19 = data2['hourly']['data'][18]['windSpeed'];
            tup2.wind20 = data2['hourly']['data'][19]['windSpeed'];
            tup2.wind21 = data2['hourly']['data'][20]['windSpeed'];
            tup2.wind22 = data2['hourly']['data'][21]['windSpeed'];
            tup2.wind23 = data2['hourly']['data'][22]['windSpeed'];
            tup2.wind24 = data2['hourly']['data'][23]['windSpeed'];

        let url3 = 'https://www.googleapis.com/customsearch/v1?q='+encodeURI(z)+'%20State%20Seal&cx=003410437470048956045:gvxfuame1cy&imgSize=huge&imgType=news&num=1&searchType=image&key=AIzaSyBYdmppskZfEB-Fmkeylim0NVFLh9o_TdQ';
        let body3 = getContent(url3);
        body3.then(function(data3) {
            res.header("Content-Type",'application/json');
            tup2.link = data3['items'][0]['link'];  

           response.push(tup2);
        
    res.send(JSON.stringify(response));
    })
})
}
   }).catch(err => {
    console.log(err);
    res.status(503).send(err);
    
   })
});

async function getContent(url) {
    let opt = {
        uri: url,
        json: true
    }

    try{let response =await req_hit(opt);
        return response;
    }
    catch (err) {
        console.log(err);
      }
}




module.exports = router;
