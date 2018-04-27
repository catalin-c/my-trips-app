//JS google map
var map;
function initMap() {
    var latitude = 41.390205; // YOUR LATITUDE VALUE
    var longitude = 2.154007; // YOUR LONGITUDE VALUE

    var myLatLng = {lat: latitude, lng: longitude};

    map = new google.maps.Map(document.getElementById('map'), {
        center: myLatLng,
        zoom: 10
    });

    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        //title: 'Hello World'

        // setting latitude & longitude as title of the marker
        // title is shown when you hover over the marker
        title: latitude + ', ' + longitude
    });
}

// google.maps.event.addDomListener(window,'load', initMap());