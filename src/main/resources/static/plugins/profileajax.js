$(document).ready(function () {

    var userId = $("#currentUserId").text();


    $.ajax({
        type: "GET",
        url: "http://localhost:7070/profile/" + userId,
        success: function (profile) {
            $("#firstName").attr("placeholder", profile['firstName']);
            $("#lastName").attr("placeholder", profile['lastName']);
            $("#email").attr("placeholder", profile['email']);
            $("#city").attr("placeholder", profile['city']);
            $("#address").attr("placeholder", profile['address']);
            $("#phone").attr("placeholder", profile['phone']);
        },

        error: function (error) {
            $("#firstName").attr("placeholder", "Error loading First Name");
            $("#lastName").attr("placeholder", "Error loading Last Name");
            $("#email").attr("placeholder", "Error loading Email");
            $("#city").attr("placeholder", "Error loading City");
            $("#address").attr("placeholder", "Error loading Address");
            $("#phone").attr("placeholder", "Error loading Phone");
        }
    });

});