$( document ).ready(function() {

    function populateOnPageLoad() {
        sParameter = encodeURIComponent($("#tripSelect :selected").text().trim());

        $.ajax({
            type: "get", url: "http://localhost:7070/trip?tripName=" + sParameter,
            success: function(result){
                $("#impressionsText").text(result['impression']);
                $("#datesText").text("From: " + result['dateFrom'] + " to " + result['dateTo']);

            },

            error: function (request) {
                $("#impressionsText").text("Error loading impressions");
                $("#datesText").text("Error loading dates");
            }

        });
    }

    populateOnPageLoad();

    //On trip selector change
    $('#tripSelect').on('change', function() {

        sParameter = encodeURIComponent(this.value.trim());

        $.ajax({
            type: "get", url: "http://localhost:7070/trip?tripName=" + sParameter,
            success: function(result){
                $("#impressionsText").text(result['impression']);
                $("#datesText").text("From: " + result['dateFrom'] + " to " + result['dateTo']);

            },

            error: function (request) {
                $("#impressionsText").text("Error loading impressions");
                $("#datesText").text("Error loading dates");
            }

        });
    });
});