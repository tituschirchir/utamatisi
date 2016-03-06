/**
 * Author: Per Spilling, per@kodemaker.no
 */
var myApp = angular.module('trees', ['ngResource', 'ui.bootstrap'], function ($dialogProvider) {
    $dialogProvider.options({backdropClick: false, dialogFade: true});
});

myApp.factory('TreeResource', function ($resource) {
    return $resource('/api/trees', {}, {});
});

function TreeCtrl($scope, TreeResource, $dialog) {
    $scope.treeForm = {
        tree: {}
    };
    $scope.treeList = TreeResource.query();
}

