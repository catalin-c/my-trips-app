$( document ).ready(function() {


    var sParameter = encodeURIComponent($("#tripSelect :selected").text().trim());
    // var tripId = 0;

    function populatePageFirst(sParameter) {

        $.ajax({
            type: "get", url: "http://localhost:7070/trip?tripName=" + sParameter,
            success: function(result){
                $("#impressionsText").text(result['impression']);
                $("#datesText").text("From: " + result['dateFrom'] + " to " + result['dateTo']);
                populatePageSecond(result['id']);
            },

            error: function (request) {
                $("#impressionsText").text("Error loading impressions");
                $("#datesText").text("Error loading dates");
            }

        });

    }

    function populatePageSecond(tripId) {
        $.ajax({
            type: "get", url: "http://localhost:7070/photos?tripId=" + tripId,
            success: function(resultTwo){
                $( '<div class="col-md-4 photoImage"> <img src="' + resultTwo[0]['photoLink'] + '" alt="photo" class="img-thumbnail"> <h3>' + resultTwo[0]['title'] + '</h3></div>' ).insertAfter( "#googleMap" );

                console.log(resultTwo[0]['photoLink']);
                console.log(resultTwo[0]['title']);
            },

            error: function (resultTwo) {
                // $("#impressionsText").text("Error loading impressions");
                // $("#datesText").text("Error loading dates");

            }
        });
    }

    populatePageFirst(sParameter);

    //On trip selector change
    $('#tripSelect').on('change', function() {

        var parameter = encodeURIComponent(this.value.trim());
        $(".photoImage").remove()
        populatePageFirst(parameter);
    });
});
