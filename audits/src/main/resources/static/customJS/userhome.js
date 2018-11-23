var app = angular.module("myApp", ["ngRoute"]);
app.controller('sidebarctrl', function ($scope) {
    console.log('angular controller is working');


});
app.controller('contactsctrl', function ($scope, $http) {
    console.log('inside contacts controller');
    $scope.allContacts = '';
    $http({
        method : "GET",
        url : "getContacts",
      }).then(function mySuccess(response) {
          console.log(response);
          $scope.allContacts = response.data;
        }, function myError(response) {
        //   $scope.myWelcome = response.statusText;
      });
    
    console.log('sadf',$scope.allContacts);
    $scope.deletecontact = function (contactid) {
        console.log(contactid);
        var res = confirm("Are you sure you want to delete this contact?");
        if (res) {
            console.log('inside ajax');
            $http({
                method: "DELETE",
                url: "deleteContact/" + contactid,
                
            }).then(function success(data, status, headers){
                console.log(data);
                window.location.reload();
            }, function error(response){
                console.log(response);
            });
            
        }
    }
});
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

function addcontact() {
    alert('you want to add new contact');
    var formVal = $('form').serializeArray();
    console.log(formVal);
    var formjson = '{';
    for (var i = 0; i < formVal.length; i++) {
        if (formVal[i].value == "") {
            alert(formVal[i].name + ' cannot be empty');
            return;
        }
        formjson += '"' + formVal[i].name + '":"' + formVal[i].value + '"';
        if (i != formVal.length - 1) formjson += ",";
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


