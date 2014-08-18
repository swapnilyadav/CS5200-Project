  $(document).ready(function () {
        var height = $(document).height(); // returns height of HTML document
        $('.black_overlay').css('height', height + 'px');
    });
    function displayFixtures() {        
        var xmlhttp;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                //parse XML response here. Parse the XML, and store the fixture (as a drop down) in 
                //innerHTML of fixtures-id.
                //alert(xmlhttp.responseText);
                xmlDoc = loadXMLString(xmlhttp.responseText);
                var text = "<a href='my-home-page.html' class='parent' target='_blank'><span>Latest Scores</span></a>" + "<ul class='dropDown' >";
                //var matches = xmlDoc.getElementsByTagName("mchdata").childNodes.length;dropDown
                var matches = xmlDoc.getElementsByTagName("match");
                for (i = 0; i < matches.length ; i++) {
                    if (matches[i].getAttribute("id") <= matches.length)
                        text += "<li><a href='display-scores.aspx?matchName="+matches[i].getAttribute("mchDesc")+"' target='_blank'><span>" + matches[i].getAttribute("mchDesc") + "</span></a></li>"
                    //alert(matches[i].getAttribute("mchDesc"));
                }
                text += "</ul>"



                document.getElementById("fixtures-id").innerHTML = text;
            }
        }
        xmlhttp.open("GET", "retrieve-fixtures.aspx", true);
        xmlhttp.send();
    }
    function loadXMLString(str) {
        if (window.DOMParser) {
            parser = new DOMParser();
            xmlDoc = parser.parseFromString(str, "text/xml");
        }
        else // Internet Explorer  
        {
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async = "false";
            xmlDoc.loadXML(str);
        }
        return xmlDoc;
    }