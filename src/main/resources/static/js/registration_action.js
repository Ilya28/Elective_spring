angular.module("login_form",[])
    /*.controller("LogCtrl", function ($scope, $http) {
        $scope.auth = {};
        $scope.sendForm = function(auth){
            const parameter = JSON.stringify({name: auth.name, email: auth.email, password: auth.password});
            $http.post("/en/login", parameter);
        }
    })*/
    .controller("RegCtrl", function ($scope, $http) {
        $scope.auth = {};
        $scope.sendForm = function(auth){
            if ($scope.auth.password !== document.getElementsByName("confirm-password")[0].value) {
                //$scope.emailRequired = 'Password confirmation error';
                window.alert("Password confirmation error");
            } else {
                const parameter = JSON.stringify({name: auth.name, email: auth.email, password: auth.password});
                $http.post("/register", parameter);
            }
        }
    });
