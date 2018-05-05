$(document).ready(function () {

    var userId = $("#currentUserId").text();


    $.validate({
        modules: 'security',
        onError: function () {
            // alert('Validation of form failed!');
        },
        onSuccess: function () {
            var firstName = $("#firstName").val();
            var lastName = $("#lastName").val();
            var email = $("#email").val();
            var city = $("#city").val();
            var address = $("#address").val();
            var phone = $("#phone").val();
            var password = $("#password").val();

            var userDetails = {};
            // userDetails['id'] = currentTripId;

            if (!firstName == "") {
                userDetails['firstName'] = firstName;
            }

            if (!lastName == "") {
                userDetails['lastName'] = lastName;
            }

            if (!email == "") {
                userDetails['email'] = lastName;
            }

            if (!city == "") {
                userDetails['city'] = city;
            }

            if (!phone == "") {
                userDetails['phone'] = phone;
            }

            if (!address == "") {
                userDetails['address'] = address;
            }

            if (!password == "") {
                userDetails['passwordHash'] = password;
            }

            if (firstName == "" && lastName == "" && email == "" && city == "" && address == "" && phone == "" && password == ""){
                alert("Please fill at least one field!");
            }else{

                $.ajax({
                    type: "PATCH",
                    url: "http://localhost:7070/updateProfile?id=" + userId,
                    // The key needs to match your method's input parameter (case-sensitive).
                    data: JSON.stringify(userDetails),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function(data){

                        location.reload(true);
                    },
                    failure: function(errMsg) {

                    }
                });
            }
            return false;
        }
    });

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

    $("#editProfile").click(function() {



    });

});