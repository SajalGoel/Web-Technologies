var express = require('express');
var bodyParser = require('body-parser');
var cors = require('cors');
var https = require('https');
var http = require('http');
var path = require('path');

var app = express();
//const port = 3000;
const port = process.env.PORT || 3000;

app.use(cors());
app.use(bodyParser());

var allowCrossDomain = function(req,res,next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    next();
}
app.use(allowCrossDomain);


app.use(express.static(path.join(__dirname, 'dist/angular-project')));

app.post('/events', function(req, res) {
    res.send('{"hi":"there"}');

})

var route = require('./routes/route');
app.use('/api', route);

app.listen(port, ()=>{
    console.log('Server started at port:' + port);
});