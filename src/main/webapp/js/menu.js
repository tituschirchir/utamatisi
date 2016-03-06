var sidebarMenu = angular.module('sidebarMenu', ['ngRoute'])
    .config(function ($locationProvider, $routeProvider) {
        $routeProvider
            .when('/', {templateUrl: '/partials/welcome.html'})
            .when('/persons', {templateUrl: '/partials/persons.html'})
            .when('/todos', {templateUrl: '/partials/todos.html'})
            .when('/trees', {templateUrl: '/partials/trees.html'})
            .otherwise({redirectTo: '/'})
    });

sidebarMenu.controller("MenuCtrl", function ($scope, $location, Menu) {
    $scope.menu = Menu;
    $scope.getClass = function (item) {
        if ($location.path() == item.href.substr(2)) {
            return "active"
        } else {
            return ""
        }
    }
});

sidebarMenu.directive("menu", function () {
    return {
        restrict: "A",
        template: '<ul class="nav nav-list">' +
            '<li class="nav-header">Menu</li>' +
            '<li ng-repeat="item in menu.items"><a href="{{item.href}}">{{item.name}}</a></li>' +
            '</ul>'
    }
});

sidebarMenu.factory('Menu', function () {
    var Menu = {};
    Menu.items = [
        {
            class: "",
            href: "/",
            name: "Home"
        },
        {
            class: "",
            href: "/#/persons",
            name: "Person View / Edit"
        },
        {
            class: "",
            href: "/#/todos",
            name: "Todo List"
        },
        {
            class: "",
            href: "/#/trees",
            name: "Trees"
        }
    ];
    return Menu;
});

