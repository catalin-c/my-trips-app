$( document ).ready(function() {

    var currentTripName = encodeURIComponent($("#tripSelect :selected").text().trim());
    var currentTripId = 0;

    // Populate trip selector + impressions + dates
    function populatePageFirstPart(sParameter) {
        $.ajax({
            type: "get", url: "http://localhost:7070/trip?tripName=" + sParameter,
            success: function(result){
                $("#impressionsText").text(result['impression']);
                $("#datesText").text("From: " + result['dateFrom'] + " to " + result['dateTo']);
                currentTripId = result['id'];
                populatePageSecondPart(result['id']);
            },

            error: function (request) {
                $("#impressionsText").text("Error loading impressions");
                $("#datesText").text("Error loading dates");
            }
        });
    }

    //Populate the page with trip photos
    function populatePageSecondPart(tripId) {
        $.ajax({
            type: "get", url: "http://localhost:7070/photos?tripId=" + tripId,
            success: function(resultTwo){
                for (var i = 0; i < resultTwo.length; i++) {
                    $( '<div class="col-md-4 photoImage"> <img src="' + resultTwo[i]['photoLink'] + '" alt="photo" class="img-thumbnail"> <h3>' + resultTwo[i]['title'] + '</h3></div>' ).insertAfter( "#googleMap" );
                }
            },

            error: function (resultTwo) {
                // $("#impressionsText").text("Error loading impressions");
                // $("#datesText").text("Error loading dates");
            }
        });
    }

    //Populate page when the page loads
    populatePageFirstPart(currentTripName);

    //Populate page on selected trip change
    $('#tripSelect').on('change', function() {

        var parameter = encodeURIComponent(this.value.trim());
        $(".photoImage").remove()
        populatePageFirstPart(parameter);
    });






    $( "#deleteTrip" ).click(function() {
        var answer = confirm("Are you sure you want to delete " + $("#tripSelect :selected").text() + "?");
        if (answer) {

        }else{
            //confirm false code
        }
    });

    //JS google map
    // var map;
    // function initMap() {
    //     var latitude = 27.7172453; // YOUR LATITUDE VALUE
    //     var longitude = 85.3239605; // YOUR LONGITUDE VALUE
    //
    //     var myLatLng = {lat: latitude, lng: longitude};
    //
    //     map = new google.maps.Map(document.getElementById('map'), {
    //         center: myLatLng,
    //         zoom: 14
    //     });
    //
    //     var marker = new google.maps.Marker({
    //         position: myLatLng,
    //         map: map,
    //         //title: 'Hello World'
    //
    //         // setting latitude & longitude as title of the marker
    //         // title is shown when you hover over the marker
    //         title: latitude + ', ' + longitude
    //     });
    // }
    //
    // google.maps.event.addDomListener(window,'load', initMap());



});
