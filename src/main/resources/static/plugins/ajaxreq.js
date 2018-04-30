$( document ).ready(function() {

    var userId = $("#currentUserId").text();
    var currentTripId = 0;

    //Load trip names and populate Trip Select Dropdown
    $.ajax({
        type: "get", url: "http://localhost:7070/getTrips/" + userId,
        success: function(tripNames){
            $.each(tripNames, function(val, text) {
                $('#tripSelect').append( $('<option></option>').val(val).html(text) )
            });

            populatePageFirstPart(encodeURIComponent($("#tripSelect :selected").text().trim()));
        },

        error: function (request) {
            $("#impressionsText").text("Error loading impressions");
            $("#datesText").text("Error loading dates");
        }
    });




    // Populate trip selector + impressions + dates
    function populatePageFirstPart(sParameter) {
        $.ajax({
            type: "get", url: "http://localhost:7070/trip?tripName=" + sParameter,
            success: function(result){
                $("#impressionsText").text(result['impression']);
                $("#datesText").text("From: " + result['dateFrom'] + " to " + result['dateTo']);
                currentTripId = result['id'];
                map.setCenter(new google.maps.LatLng(result['latitude'], result['longitude']));
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
                    $( '<div class="col-md-4 photoImage"> <img src="' + resultTwo[i]['photoLink'] + '" alt="photo" class="img-thumbnail"> <h3>' + resultTwo[i]['title'] + '</h3></div>' ).insertAfter( ".googleMap" );
                }
            },

            error: function (resultTwo) {
                // $("#impressionsText").text("Error loading impressions");
                // $("#datesText").text("Error loading dates");
            }
        });
    }

    //Populate page on selected trip change
    $('#tripSelect').on('change', function() {
        $(".photoImage").remove()
        populatePageFirstPart(encodeURIComponent($("#tripSelect :selected").text().trim()));
    });

    //
    $( "#deleteTrip" ).click(function() {
        var answer = confirm("Are you sure you want to delete " + $("#tripSelect :selected").text() + "?");
        if (answer) {

            $.ajax({
                type: "POST",
                url: "http://localhost:7070/delete/trip/" + currentTripId,
                success: function () {
                    location.reload(true);
                },
                error: function () {
                    alert("The trip can't be deleted.");
                }
            });
        }
    });


    //Add Photo Form
    $("#addPhoto").click(function() {
        $("#addPhotoDiv").css("display", "block");
    });
    $("#addPhotoForm #cancelPhoto").click(function() {
        $(this).parent().parent().hide();
    });

    $("#sendPhoto").click(function() {
        var photoName = $("#photoName").val();
        var photoLink = $("#photoLink").val();
        if (photoName == "" || photoLink == ""){
            alert("Please fill all the fields!");
        }else{
            $("#addPhotoDiv").css("display", "none");
            var photoDetails = {"title": photoName, "photoLink": photoLink, "tripId":currentTripId};

            $.ajax({
                type: "POST",
                url: "http://localhost:7070/addPhoto",
                // The key needs to match your method's input parameter (case-sensitive).
                data: JSON.stringify(photoDetails),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data){
                    location.reload(true);
                },
                failure: function(errMsg) {
                    alert("The photo can't be added.");
                }
            });
        }
    });

    //Add trip date pickers
    $( "#datePickerFrom" ).datepicker({ dateFormat: 'd M yy' });
    $( "#datePickerTo" ).datepicker({ dateFormat: 'd M yy' });


    //Add Trip Form
    $("#addTrip").click(function() {
        $("#addTripDiv").css("display", "block");
    });
    $("#addTripForm #cancelTrip").click(function() {
        $(this).parent().parent().hide();
    });

    $("#sendTrip").click(function() {

        var tripName = $("#tripName").val();
        var datePickerFrom = $("#datePickerFrom").val();
        var datePickerTo = $("#datePickerTo").val();
        var tripImpressions = $("#tripImpressions").val();
        var countryName = $("#countryName").val();
        var cityName = $("#cityName").val();
        if (tripName == "" || datePickerFrom == "" || datePickerTo == "" || tripImpressions == "" || countryName == "" || cityName == ""){
            alert("Please fill all the fields!");
        }else{

            var tripDetails = {"userId": userId,"tripName": tripName, "dateFrom": datePickerFrom, "dateTo": datePickerTo,
            "impression": tripImpressions};

            //Request google coordinates
            $.ajax({
                type: "get", url: "https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName + "+" + countryName +
                "&key=AIzaSyDL3OcNBWkphg7AdM5AZEPN-MU8c2r68Nw",
                success: function(mapCoordinates){
                    tripDetails['latitude'] = mapCoordinates['results'][0]['geometry']['location']['lat'];
                    tripDetails['longitude'] = mapCoordinates['results'][0]['geometry']['location']['lng'];

                    $.ajax({
                        type: "POST",
                        url: "http://localhost:7070/addTrip",
                        // The key needs to match your method's input parameter (case-sensitive).
                        data: JSON.stringify(tripDetails),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function(data){
                            $("#addTripDiv").css("display", "none");
                            location.reload(true);
                        },
                        failure: function(errMsg) {
                            alert(errMsg);
                        }
                    });
                },

                error: function (request) {
                    alert("Please enter a valid country/state and/or city!")
                }
            });



        }
    });


});
