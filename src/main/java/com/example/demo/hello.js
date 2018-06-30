$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/sgdToCn/getAll"
    }).then(function(data) {
        $('.company-id').append(data[0].company);
        $('.rate').append(data[0].rate);
    });
});