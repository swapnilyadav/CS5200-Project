if(window.co === undefined||window.co==""){co="100";} if(window.wi === undefined||window.wi==""){wi="n";} 
if(window.d === undefined||window.d==""){d="n";} 
var temp='http://www.cricwaves.com/cricket/widgets/!/'+co+'/'+wi+'/Ratings.html'; 
var wdidth='400'; var hite = 600; if(wi == 'w'){wdidth='310'} if(co == 'New'){wdidth='300'; hite = 385 } 
document.write("");

function getContent(str) {
    displayFixtures();
    var xmlhttp;
    var fileName = str + ".htm";
    if (str == "test") {
        document.getElementById(str).src = "images/" + str + "-change.jpg";
        document.getElementById("t20").src = "images/T20.jpg";
        document.getElementById("odi").src = "images/ODI.jpg";
    }
    if (str == "odi") {
        document.getElementById(str).src = "images/" + str + "-change.jpg";
        document.getElementById("test").src = "images/Test.jpg";
        document.getElementById("t20").src = "images/T20.jpg";
    }
    if (str == "t20") {
        document.getElementById(str).src = "images/" + str + "-change.jpg";
        document.getElementById("test").src = "images/Test.jpg";
        document.getElementById("odi").src = "images/ODI.jpg";
    }
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("content").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", fileName, true);
    xmlhttp.send();
}