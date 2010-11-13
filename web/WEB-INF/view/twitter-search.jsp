<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width = device-width"/>
    <title>Local Twitter Search</title>
    <script type="text/javascript">
        function $(id) {
            return document.getElementById(id);
        }
        function startSearch() {
            alert("startSearch");
            var gps = navigator.geolocation;
            if (gps) {
                alert("gps enabled");
                gps.getCurrentPosition(searchTwitter,
                        function(error) {
                            alert("Got an error, code: " + error.code + " message: " +
                                    error.message);
                        });
            } else {
                searchTwitter();
            }
        }
        function searchTwitter(position) {
            alert("searchTwitter position=" + position);
            var query = "http://search.twitter.com/search.json?callback=showResults&q=";
            query += $("kwBox").value;
            if (position) {
                var lat = position.coords.latitude;
                var long = position.coords.longitude;
                query += "&geocode=" + escape(lat + "," + long + ",50mi");
            }
            alert("query=" + query);
            var script = document.createElement("script");
            script.src = query;
            document.getElementsByTagName("head")[0].appendChild(script);
        }
        function showResults(response) {
            alert("showResults");
            var tweets = response.results;
            tweets.forEach(function(tweet) {
                tweet.linkUrl = "http://twitter.com/" + tweet.from_user + "/status/" + tweet.id;
            });
            makeResultsTable(tweets);
        }
        function makeResultsTable(tweets) {
            var cnt = 1;
            var rows = tweets.map(function(tweet) {
                cnt++;
                return createResult(tweet.from_user, tweet.profile_image_url, tweet.text,
                        tweet.linkUrl, cnt % 2 == 0);
            });
            $("results").innerHTML = "<table id='resultsTable'></table>";
            rows.forEach(function(row) {
                $("resultsTable").appendChild(row);
            });
        }
        function createResult(srcName, srcImgUrl, title, linkUrl, odd) {
            var resultsRow = document.createElement("tr");
            if (odd) {
                resultsRow.setAttribute("class", "odd");
            }
            var srcCell = document.createElement("td");
            srcCell.setAttribute("class", "src");
            resultsRow.appendChild(srcCell);
            var icon = document.createElement("img");
            icon.src = srcImgUrl;
            icon.setAttribute("alt", srcName);
            icon.setAttribute("height", 48);
            icon.setAttribute("width", 48);
            srcCell.appendChild(icon);
            srcCell.appendChild(document.createTextNode(srcName));
            var messageCell = document.createElement("td");
            messageCell.setAttribute("class", "msg");
            var link = document.createElement("a");
            link.setAttribute("href", linkUrl);
            link.setAttribute("target", "_blank");
            link.appendChild(document.createTextNode(title));
            messageCell.appendChild(link);
            resultsRow.appendChild(messageCell);
            return resultsRow;
        }
    </script>
    <style type="text/css">
        td {
            padding-top: 15px;
            padding-right: 15px;
            padding-bottom: 20px;
            padding-left: 15px;
            border: 1px solid black;
        }

        table {
            text-align: center;
            border-collapse: collapse;
        }

        a:link {
            text-decoration: none;
            color: #f24d1b;
        }

        .msg {
            text-align: left;
            padding-left: 8px;
        }

        tr.odd {
            background-color: #bce4ee;
        }

        .src {
            background: url(timoreilly.gif) left center no-repeat;
            text-align: right;
            font-style: italic;
            padding: 10px;
        }

        h1 {
            font-size: medium;
            font-style: inherit;
            font-variant: small-caps
        }
    </style>
</head>
<body>
<div id="main">
    <label for="kwBox">Search Twitter:</label>
    <input type="text" id="kwBox"/>
    <input type="button" value="Go!" onclick="startSearch()"/>
</div>
<div id="results">
</div>
</body>
</html>