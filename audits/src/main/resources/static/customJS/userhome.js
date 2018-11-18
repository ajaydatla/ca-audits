var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/returnfiling", {
            templateUrl: "returnfiling"
        })
        .when("/gstr3b", {
            templateUrl: "gstr3b"
        })
        .when("/clientcontacts", {
            templateUrl: "clientcontacts"
        });
});
function deletecontact(contactid) {
    console.log(contactid);
    var res = confirm("Are you sure you want to delete this contact?");
    console.log("res ", res);
    if (res) {
        console.log('inside ajax');
        $.ajax({
            type: "DELETE",
            url: "deleteContact/" + contactid,
            dataType: 'text',
            success: function (response) {
                console.log(response);
                window.location.reload();
            }
        });
    }

}
function addcontact(){
    alert('you want to add new contact');
    var formVal = $('form').serializeArray();
    console.log(formVal);
    var formjson = '{';
    for(var i = 0; i < formVal.length; i++){
        if(formVal[i].value == ""){
            alert(formVal[i].name+' cannot be empty');
            return;
        }
        formjson +='"' +formVal[i].name +'":"'+formVal[i].value+'"';
        if(i != formVal.length-1) formjson+=",";
    }
    formjson += "}";
    console.log(formjson);
    $.ajax({
        type: "POST",
        url: "addNewContact",
        data: JSON.parse(formjson),
        dataType: 'text',
        success: function (response) {
            console.log('success');
            window.location.reload();
        }
    });
}


