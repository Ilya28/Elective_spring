angular.module("login_form",[])
    .controller("LogCtrl", function ($scope, $http) {
        $scope.auth = {};
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                function(data) {
                    window.alert("Доступ разрешен");
                },
                function(error) {
                    window.alert("Доступ запрещен");
                }
            );
        }
    })
    .controller("RegCtrl", function ($scope, $http) {
        $scope.auth = {};
        $scope.sendForm = function(auth){
            //if (!$scope.auth.password !== document.getElementsByName("confirm-password")[0].value) {
            //    $scope.emailRequired = 'Email confirmation error';
            //} else {
                $http({
                    method: "POST",
                    url: "/meow",
                    data: $.param(auth),
                    headers: {"Content-Type": "application/x-www-form-urlencoded"}
                }).then(
                    function (data) {
                        window.alert("Доступ разрешен");
                    },
                    function (error) {
                        window.alert("Доступ запрещен");
                    }
                );
            //}
        }
    });
