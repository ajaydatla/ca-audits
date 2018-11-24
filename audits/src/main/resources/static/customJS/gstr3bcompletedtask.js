app.controller('gstr3bcompletedtask',function ($scope,$http){
    console.log('inside gstr3bcompletedtask');
    $scope.gstr3bcompletedtaskdata = '';
    $http({
        method : "GET",
        url : "getGSTR3BCompletedTask",
      }).then(function mySuccess(response) {
        
         $scope.gstr3bcompletedtaskdata = response.data;
        }, function myError(response) {
       
      });
});