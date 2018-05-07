$(document).ready(function () {

    var userId = $("#currentUserId").text();
    var currentTripId = 0;

    // Load trip names and populate Trip Select Dropdown
    $.ajax({
        type: "get",
        url: "/getTrips/" + userId,
        success: function (tripNames) {
            $.each(tripNames, function (val, text) {
                $('#tripSelect').append($('<option></option>').val(val).html(text))
            });


            populatePageFirstPart(encodeURIComponent($("#tripSelect :selected").text().trim()));
        },

        error: function (request) {
            $("#impressionsText").text("Error loading impressions");
            $("#datesText").text("Error loading dates");

            $("#impressionsText").hide();
            $("#datesText").hide();
            $(".googleMap").hide();
        }
    });


    // Populate map + impressions + dates
    function populatePageFirstPart(sParameter) {
        $.ajax({
            type: "get", url: "/trip?tripName=" + sParameter,
            success: function (result) {
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
            type: "get", url: "/photos?tripId=" + tripId,
            success: function (resultTwo) {
                for (var i = 0; i < resultTwo.length; i++) {
                    $('<div class="col-md-4 photoImage"> <img src="' + resultTwo[i]['photoLink'] + '" alt="photo" class="img-thumbnail"> <h3>' + resultTwo[i]['title'] + '</h3></div>').insertAfter(".googleMap");
                }
            },

            error: function (error) {
                alert("The photos couldn't be loaded!");
            }
        });
    }

    //Populate page on selected trip change
    $('#tripSelect').on('change', function () {
        $(".photoImage").remove()
        populatePageFirstPart(encodeURIComponent($("#tripSelect :selected").text().trim()));
    });

    //
    $("#deleteTrip").click(function () {
        var answer = confirm("Are you sure you want to delete " + $("#tripSelect :selected").text() + "?");
        if (answer) {

            $.ajax({
                type: "POST",
                url: "/delete/trip/" + currentTripId,
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
    $("#addPhoto").click(function () {
        $("#addPhotoDiv").css("display", "block");
    });
    $("#addPhotoForm #cancelPhoto").click(function () {
        $(this).parent().parent().hide();
    });

    $.validate({
        form: '#addPhotoForm',
        onSuccess: function () {
            var photoName = $("#photoName").val();
            var photoLink = $("#photoLink").val();
            $("#addPhotoDiv").css("display", "none");
            var photoDetails = {"title": photoName, "photoLink": photoLink, "tripId": currentTripId};

            $.ajax({
                type: "POST",
                url: "/addPhoto",
                data: JSON.stringify(photoDetails),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    location.reload(true);
                },
                failure: function (errMsg) {
                    alert("The photo can't be added.");
                }
            });
            return false;
        }
    });

    //Add trip date pickers
    $("#datePickerFrom").datepicker({dateFormat: 'd M yy'});
    $("#datePickerTo").datepicker({dateFormat: 'd M yy'});

    //Add Trip Form
    $("#addTrip").click(function () {
        $("#addTripDiv").css("display", "block");
    });
    $("#addTripForm #cancelTrip").click(function () {
        $(this).parent().parent().hide();
    });

    $.validate({
        form: '#addTripForm',
        modules: 'security',
        onSuccess: function () {
            var tripName = $("#tripName").val();
            var datePickerFrom = $("#datePickerFrom").val();
            var datePickerTo = $("#datePickerTo").val();
            var tripImpressions = $("#tripImpressions").val();
            var countryName = $("#countryName").val();
            var cityName = $("#cityName").val();
            if (tripName == "" || datePickerFrom == "" || datePickerTo == "" || tripImpressions == "" || countryName == "" || cityName == "") {
                alert("Please fill all the fields!");
            } else {

                var tripDetails = {
                    "userId": userId, "tripName": tripName, "dateFrom": datePickerFrom, "dateTo": datePickerTo,
                    "impression": tripImpressions
                };

                //Request google coordinates
                $.ajax({
                    type: "get",
                    url: "https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName + "+" + countryName +
                    "&key=AIzaSyDL3OcNBWkphg7AdM5AZEPN-MU8c2r68Nw",
                    success: function (mapCoordinates) {
                        if (mapCoordinates['status'] !== 'OK') {
                            alert("Please enter a valid country/state and/or city!");
                        } else {
                            tripDetails['latitude'] = mapCoordinates['results'][0]['geometry']['location']['lat'];
                            tripDetails['longitude'] = mapCoordinates['results'][0]['geometry']['location']['lng'];

                            $.ajax({
                                type: "POST",
                                url: "/addTrip",
                                data: JSON.stringify(tripDetails),
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                success: function (data) {
                                    $("#addTripDiv").css("display", "none");
                                    location.reload(true);
                                },
                                failure: function (errMsg) {
                                    alert(errMsg);
                                }
                            });
                        }
                    },
                    error: function (error) {
                        alert("Please enter a valid country/state and/or city!");
                    }
                });
            }
            return false;
        }
    });

    //Edit trip date pickers
    $("#editDatePickerFrom").datepicker({dateFormat: 'd M yy'});
    $("#editDatePickerTo").datepicker({dateFormat: 'd M yy'});

    //Edit Trip Form
    $("#editTrip").click(function () {
        $.ajax({
            type: "get", url: "/trip?tripName=" + encodeURIComponent($("#tripSelect :selected").text().trim()),
            success: function (trip) {
                $("#editTripName").attr("placeholder", trip['tripName']);
                $("#editDatePickerFrom").attr("placeholder", trip['dateFrom']);
                $("#editDatePickerTo").attr("placeholder", trip['dateTo']);
                $("#editTripImpressions").attr("placeholder", trip['impression']);
                $("#editCityName").attr("placeholder", trip['city']);
                $("#editCountryName").attr("placeholder", trip['country']);

                $("#editTripDiv").css("display", "block");
            },
            error: function (error) {
                $("#editTripName").attr("placeholder", "Error Loading..");
                $("#editDatePickerFrom").attr("placeholder", "Error Loading..");
                $("#editDatePickerTo").attr("placeholder", "Error Loading..");
                $("#editTripImpressions").attr("placeholder", "Error Loading..");
                $("#editCityName").attr("placeholder", "Error Loading..");
                $("#editCountryName").attr("placeholder", "Error Loading..");
                $("#editTripDiv").css("display", "block");
                console.log(error);
            }
        });
    });

    $("#editTripForm #editCancelTrip").click(function () {
        $(this).parent().parent().hide();
    });

    $.validate({
        form: '#editTripForm',
        onSuccess: function () {
            var editTripName = $("#editTripName").val();
            var editDatePickerFrom = $("#editDatePickerFrom").val();
            var editDatePickerTo = $("#editDatePickerTo").val();
            var editTripImpressions = $("#editTripImpressions").val();
            var editCountryName = $("#editCountryName").val();
            var editCityName = $("#editCityName").val();

            var editDetails = {};

            if (!editTripName == "") {
                editDetails['tripName'] = editTripName;
            }

            if (!editDatePickerFrom == "") {
                editDetails['dateFrom'] = editDatePickerFrom;
            }

            if (!editDatePickerTo == "") {
                editDetails['dateFrom'] = editDatePickerFrom;
            }

            if (!editTripImpressions == "") {
                editDetails['impression'] = editTripImpressions;
            }


            if (!editCityName == "" && editCountryName == "") {
                alert("You need to enter both City Name and Country Name!");
                return false;
            }else if (editCityName == "" && !editCountryName == "") {
                alert("You need to enter both City Name and Country Name!");
                return false;
            } else if(!editCityName == "" && !editCountryName == ""){
                editDetails['country'] = editCityName;
                editDetails['city'] = editCountryName;
            }


            // Google Maps Validation
            $.ajax({
                type: "get",
                url: "https://maps.googleapis.com/maps/api/geocode/json?address=" + editCityName + "+" + editCountryName +
                "&key=AIzaSyDL3OcNBWkphg7AdM5AZEPN-MU8c2r68Nw",
                success: function (mapCoordinates) {
                    if (mapCoordinates['status'] !== 'OK') {
                        alert("Please enter a valid country/state and/or city!");
                    } else {
                        editDetails['latitude'] = mapCoordinates['results'][0]['geometry']['location']['lat'];
                        editDetails['longitude'] = mapCoordinates['results'][0]['geometry']['location']['lng'];
                        $.ajax({
                            type: "PATCH",
                            url: "http://localhost:7070/updateTrip?tripName=" + encodeURIComponent($("#tripSelect :selected").text().trim()),
                            data: JSON.stringify(editDetails),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                $("#addTripDiv").css("display", "none");
                                location.reload(true);
                            },
                            failure: function (errMsg) {
                                alert("Error Updating Trip");
                            }
                        });
                    }
                },
                error: function (error) {
                }
            });
            return false;
        }
    });
});
