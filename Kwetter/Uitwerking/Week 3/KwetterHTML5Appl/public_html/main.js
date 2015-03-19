/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
;
(function () {

    /*var users = [
     {
     id: 1,
     name: "Luc Kolen",
     location: "Moergestel",
     web: "http://www.luckolen.nl",
     image: "http://i.imgur.com/5HTC2In.jpg",
     bio: "I should really put some text about myself here, but I'm too lazy to do it",
     tweets: [
     {id: 1, tweet: "First tweet", date: "1-3-2015 12:00:00", postedFrom: "web"},
     {id: 4, tweet: "Second tweet", date: "4-3-2015 12:00:00", postedFrom: "web"}
     ],
     followers: [2],
     following: [2]
     },
     {
     id: 2,
     name: "Test user",
     location: "Test city",
     web: "http://google.com",
     image: "http://i.imgur.com/SxKJEWo.png",
     bio: "Some bio text",
     tweets: [
     {id: 2, tweet: "First test tweet", date: "2-3-2015 12:00:00", postedFrom: "web"},
     {id: 3, tweet: "Second test tweet", date: "3-3-2015 12:00:00", postedFrom: "web"}
     ],
     followers: [1],
     following: [1]
     }
     ];*/

    var users = [];

    function getNextTweetID() {
        var id = 1;
        for (i in users) {
            for (j in users[i].tweets) {
                if (users[i].tweets[j].id >= id) {
                    id = users[i].tweets[j].id + 1;
                }
            }
        }
        return id;
    }


    function getUserById(id) {
        for (i in users) {
            if (users[i].id == id) {
                return users[i];
            }
        }
        return null;
    }

    function getUserByName(name) {
        for (i in users) {
            if (users[i].name == name) {
                return users[i];
            }
        }
        return null;
    }

    var params = {};
    if (location.search) {
        var parts = location.search.substring(1).split('&');

        for (var i = 0; i < parts.length; i++) {
            var nv = parts[i].split('=');
            if (!nv[0])
                continue;
            params[nv[0]] = nv[1] || true;
        }
    }

    function getUsers($http) {
        $http.get("http://localhost:8080/Kwetter-EE-Backend/rest/findAllUsers")
                .success(function (response) {
                    users = response;
                });
    }

    var app = angular.module('Kwetter', []);
    app.factory("getUsers", function ($http) {
        var getUsers = function () {
            return $http({
                method: "GET", url: "http://localhost:8080/Kwetter-EE-Backend/rest/findAllUsers"
            }).then(function (result) {
                users = result.data;
                return result;
            });
        };
        return {getUsers: getUsers};
    });
    app.controller("Kwetter_profile", function ($scope, getUsers) {
        var getUsersPromise = getUsers.getUsers();
        getUsersPromise.then(function (result) {
            if (getUserById(params.id) === null) {
                alert("no id given in url, will be redirected to account with id 1");
                //location.href = "profile.html?id=1";
            }
            $scope.currentUser = getUserById(params.id);
            $scope.getUserById = function (id) {
                return getUserById(id);
            };
            $scope.s1_switch = "tweets"; //(options are: tweets, following and followers)
        });
    });
    app.controller("Kwetter_home", function ($scope, getUsers) {
        var getUsersPromise = getUsers.getUsers();
        getUsersPromise.then(function (result) {
            $scope.submitLogin = function () {
                var user = getUserByName($scope.name);
                if (user != null) {
                    window.location.href = "loggedin.html?id=" + user.id;
                } else {
                    $scope.error = true;
                }
            };
            $scope.getUsernames = function () {
                var usernames = [];
                for (i in users) {
                    usernames.push(users[i].name);
                }
                console.log(usernames);
                return usernames;
            }
        })
    });
    app.controller("Kwetter_loggedin", function ($scope, getUsers) {
        var getUsersPromise = getUsers.getUsers();
        getUsersPromise.then(function (result) {
            if (getUserById(params.id) === null) {
                alert("Not logged in, you'll be redirected");
                location.href = "index.html";
            }
            $scope.currentUser = getUserById(params.id);
            $scope.logout = function () {
                location.href = "index.html";
            };
            $scope.submitTweet = function () {
                var text = $scope.tweetText;
                var tweet = {
                    id: getNextTweetID(),
                    tweet: text,
                    date: new Date().toLocaleString(),
                    postedFrom: "web"
                };
                $scope.currentUser.tweets.push(tweet);
                $scope.tweetText = "";
            };
            $scope.getUserById = function (id) {
                return getUserById(id);
            };
            $scope.getMyTweetsAndFromFollowedAccounts = function () {
                var timeline = [];
                for (i in $scope.currentUser.tweets) {
                    timeline.push($scope.currentUser.tweets[i]);
                }
                for (i in $scope.currentUser.following) {
                    var user = getUserById($scope.currentUser.following[i]);
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
    });
}());