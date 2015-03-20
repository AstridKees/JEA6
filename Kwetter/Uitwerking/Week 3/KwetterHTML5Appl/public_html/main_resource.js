; //This JavaScript file will use $resource instead of $http, except for that they are the same
(function () {
    function getUserById(users, id) {
        for (i in users) {
            if (users[i].id == id) {
                return users[i];
            }
        }
        return null;
    }

    function getUserByName(users, name) {
        for (i in users) {
            if (users[i].name == name) {
                return users[i];
            }
        }
        return null;
    }

    var params = {};
    if (location.search != undefined) {
        var parts = location.search.substring(1).split('&');
        for (var i = 0; i < parts.length; i++) {
            var nv = parts[i].split('=');
            if (!nv[0])
                continue;
            params[nv[0]] = nv[1] || true;
        }
    } else {
        alert("location.search isn't supported in your webbrowser, this will result in errors");
    }

    var app = angular.module('Kwetter', ['ngResource']);
    app.factory("userFactory", ['$resource', function ($resource) {
            return $resource("http://localhost:8080/Kwetter-EE-Backend/rest/api/:id");
        }]);
    app.controller("Kwetter_profile", ['$scope', 'userFactory', function ($scope, userFactory) {
            userFactory.query(function (data) {
                console.log("Loaded users");
                console.log(data);
                $scope.users = data;
                $scope.currentUser = getUserById($scope.users, params.id);
            });
            $scope.s1_switch = "tweets"; //(options are: tweets, following and followers)
        }]);
    app.controller("Kwetter_home", ['$scope', 'userFactory', function ($scope, userFactory) {
            userFactory.query(function (data) {
                console.log("Loaded users");
                console.log(data);
                $scope.users = data;
            });
            $scope.submitLogin = function () {
                var user = getUserByName($scope.users, $scope.name);
                if (user != null) {
                    $scope.error = false;
                    window.location.href = "loggedin.html?id=" + user.id;
                } else {
                    $scope.error = true;
                }
            };
            $scope.getUsernames = function () {
                var usernames = [];
                for (i in $scope.users) {
                    if ($scope.users[i].name)
                        usernames.push($scope.users[i].name);
                }
                return usernames;
            }
        }]);
    app.controller("Kwetter_loggedin", ['$scope', 'userFactory', function ($scope, userFactory) {
            function getUsers() {
                userFactory.query(function (data) {
                    console.log("Loaded users");
                    console.log(data);
                    $scope.users = data;
                    $scope.currentUser = getUserById($scope.users, params.id);
                    $scope.getMyTweetsAndFromFollowedAccounts = function () {
                        var timeline = [];
                        for (i in $scope.currentUser.tweets) {
                            timeline.push($scope.currentUser.tweets[i]);
                        }
                        for (i in $scope.currentUser.following) {
                            var user = getUserById($scope.users, $scope.currentUser.following[i]);
                            for (j in user.tweets) {
                                timeline.push(user.tweets[j]);
                            }
                        }
                        timeline.sort(function (a, b) {
                            return a.id == b.id ? 0 : a.id < b.id ? 1 : -1;
                        });
                        return timeline;
                    };
                });
            }
            getUsers();
            //this is needed because we've data-ng-src="{{getUserById(followedUserID).image}}" in line 105 on loggedin.html
            $scope.getUserById = function (id) {
                return getUserById($scope.users, id);
            };
            $scope.logout = function () {
                location.href = "index.html";
            };

            $scope.submitTweet = function () {
                var tweet = {
                    tweetText: $scope.tweetText,
                    datum: new Date().toJSON(), //JavaEE understands this format while consuming JSON
                    vanaf: "web"
                };
                userFactory.save({id: params.id}, tweet).$promise.finally(function () {
                    getUsers();
                });
                $scope.tweetText = "";
            };
        }]);
}());