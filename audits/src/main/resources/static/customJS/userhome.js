var app = angular.module("myApp", ["ngRoute"]);
app.controller('sidebarctrl', function ($scope,$http) {
	
	$scope.sidebardatas = "";
    $http({
        method : "GET",
        url : "getSideBarMenu",
      }).then(function mySuccess(response) {
        
         $scope.sidebardatas = response.data;
        }, function myError(response) {
       
      });
});
app.controller('contactsctrl', function ($scope, $http) {
    console.log('inside contacts controller');
    $scope.allContacts = '';
    $http({
        method : "GET",
        url : "getContacts",
      }).then(function mySuccess(response) {
          
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
        })
        .when("/renderGSTR3BCompletedTask", {
            templateUrl: "renderGSTR3BCompletedTask"
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


// [
//     {
//       "iconcssclass": "sidebaricon glyphicon glyphicon-home"
//     },
//     {
//       "iconcssclass": "sidebaricon glyphicon glyphicon-th-list",
//       "iconlink": "",
//       "mainhead": {
//         "GST": {
//           "subhead": {
//             "GSTR 3B": {"link":"#!gstr3b"},
//             "GSTR 1": {},
//             "GSTR 4": {}
//           }
//         },
//         "INCOME TAX": {
//           "subhead": {
//             "RETURN FILING": {},
//             "Refund Matters / Demand Matters / Defective Return Notices": {
//               "style": "font-size:12px;padding-top: 3px;"
//             },
//             "Non Filing of Return Notices": {},
//             "15G/H": {},
//             "Income Tax Notice": {}
//           }
//         },
//         "AUDIT": {
//           "subhead": {
//             "TAX AUDIT": {},
//             "SCHOOL AUDIT": {}
//           }
//         },
//         "TDS": {
//           "subhead": {
//             "TDS Return Filing": {},
//             "Correction Returns": {},
//             "Default Corrections": {}
//           }
//         },
//         "MISCELLANEOUS": {
//           "subhead": {
//             "Deed Writing": {},
//             "Certificates": {},
//             "PAN / TAN": {},
//             "Digital Signature": {},
//             "Projection": {}
//           }
//         },
//         "FINANCE": {
//           "subhead": {
//             "PROJECT REPORT": {},
//             "CMA": {},
//             "PROPOSAL": {}
//           }
//         }
//       }
//     },
//     {
//       "iconcssclass": "sidebaricon glyphicon glyphicon-envelope",
//       "iconlink": "",
//       "mainhead": {
//         "ADD/VIEW CONTACT": {
//           "link":"#!clientcontacts"
//         },
//         "ADD/VIEW PHONE BOOK CATEGORY": {
//           "style": "font-size:12px;padding-top: 3px;"
//         },
//         "MANAGE TEMPLATE": {
//           "subhead": {
//             "GENERATE TEMPLATE": {}
//           }
//         },
//         "CREATE LIST": {},
//         "VIEW LIST": {},
//         "SMS GENERATOR": {},
//         "EMAIL-LOG": {},
//         "SMS-LOG": {}
//       }
//     },
//     {
//       "iconcssclass": "fa fa-inr sidebaricon",
//       "mainhead": {
//         "BILLING SETTINGS": {
//           "subhead": {
//             "MANAGE ORGANIZATION": {},
//             "MANAGE ACCOUNTS": {},
//             "MANAGE RECEIPT BOOKS": {},
//             "MANAGE TAX": {},
//             "NARRATIN, NOTES & SAC": {},
//             "CONFIGURE AUTO RECEIPT NO": {"style":"font-size:12px;"}
//           }
//         },
//         "OPENING BILLING": {},
//         "BILLS": {
//           "subhead": {
//             "GENERATE INDIVIDUAL BILLS": {"style":"font-size:13px"},
//             "GENERATE BILL WITHOUT TASK": {"style":"font-size:12px"},
//             "GENERATE COMBINE BILLS": {},
//             "DELETE BILLS": {},
//             "MULTIPLE SERVICES BILL": {}
//           }
//         },
//         "RECEIPTS": {
//           "subhead": {
//             "IND. RECEIPT": {},
//             "GROUP RECEIPTS": {},
//             "ADVANCE/DIRECT INVOICE & RECEIPT": {"style":"font-size:10px"},
//             "SETTLING ADVANCE RECEIPT": {"style":"font-size:13px"},
//             "DELETE RECEIPTS": {}
//           }
//         },
//         "REPORTS": {
//           "subhead": {
//             "FREE TASK REPORT": {},
//             "UNBILLED TASK REPORT": {},
//             "BILL REGISTER": {},
//             "RECEIPT REGISTER": {},
//             "SEARCH BILLS": {},
//             "SEARCH COLLECTION": {},
//             "BILL SUMMARY": {},
//             "RECEIPT SUMMARY": {}
//           }
//         },
//         "BILLING DASHBOARD": {
//           "subhead": {
//             "PROJECT REPORT": {},
//             "CMA": {},
//             "PROPOSAL": {}
//           }
//         },
//         "VIEW ACCOUNTS":{},
//         "MARK THE TASK FREE":{},
//         "BILLING":{}
//       }
//     },
//     {"iconcssclass":"sidebaricon glyphicon glyphicon-th",
//       "mainhead":{"NOTICE BOARD":{
//         "subhead":{
//           "CREATE NOTICE BOARD MESSAGE":{"style":"font-size:11px;"},
//           "CREATED BY ME":{},
//           "POSTED FOR ME":{}
//         }
//       },
//         "LEAVE MANAGEMENT":{
//         "subhead":{
//           "LEAVES PENDING FOR APPROVAL":{},
//           "STAFF LEAVE DASHBOARD":{},
//           "MODIFY LEAVE BALANCES":{}
//         }
//       },
//       "DIGITAL SIGNATURE REGISTER":{"style":"font-size:10px;"}
//       }
//     },
//     {"iconcssclass":"sidebaricon glyphicon glyphicon-hourglass",
//       "mainhead":{"SHOW ALL":{},
//         "ATTACH A DOCUMENT":{},
//         "ATTACHED BY ME":{},
//         "ATTACHED FOR ME":{}
//       }
//     },
//     {"iconcssclass":"sidebaricon glyphicon glyphicon-bitcoin",
//       "mainhead":{"CREATE DOCUMENT":{},
//         "CREATED BY ME":{},
//         "SHARED TO ME":{}
//       }
//     },
//     {"iconcssclass":"sidebaricon glyphicon glyphicon-cog",
//       "mainhead":{"TASK SETTINGS":{
//         "subhead":{
//           "CLIENT LIST":{},
//           "MANAGE TYPES":{},
//           "MANAGE CATEGORIES":{},
//           "GROUP MASTER":{}
//         }
//       },
//         "USER MANAGEMENT":{},
//         "CHECK CONTROLS":{},
//         "AUTO SMS CONFIGURATION":{"style":"font-size: 11px;"},
//         "MANAGE CHECKS":{
//           "subhead":{
//             "MANAGE TASK CHECKS":{},
//             "MANAGE CHECK GROUPS":{}
//           }
//         },
//         "IMPORT MASTERS":{},
//         "BULK TASK CREATION":{}
//       }
//     },
//     {"iconcssclass":"sidebaricon glyphicon glyphicon-list-alt",
//       "mainhead":{
//         "INWARD-REGISTER":{},
//         "SETTING":{
//           "subhead":{
//             "CREATE/EDIT INWARD REGISTER":{"style":"font-size: 14px;padding-top: 2px;"},
//             "DOCUMENT NATURE & THROUGH":{"style":"font-size: 14px;padding-top: 2px;"}
//           }
//         }
//       }
//     }
//   ]