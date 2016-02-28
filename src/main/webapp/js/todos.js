/**
 * Author: Per Spilling, per@kodemaker.no
 */
var myApp = angular.module('todos', ['ngResource', 'ui.bootstrap'], function ($dialogProvider) {
    $dialogProvider.options({backdropClick: false, dialogFade: true});
});

myApp.factory('TodosResource', function ($resource) {
    return $resource('/api/todos', {}, {});
});

function TodoCtrl($scope, TodosResource, $dialog, $q) {
    $scope.todoForm = {
        todo: {}
    };
    $scope.todoList = TodosResource.query();
    console.log($scope.todoList);

    $scope.addTodo = function (todo) {
        console.log(todo);
        if (todo != undefined) {
            TodosResource.save(todo).$promise.then(function() {
                $scope.todoForm.todo = {};  // clear the form
                $scope.todoList = TodosResource.query();
            });
        }
    };

    $scope.remaining = function () {
        var count = 0;
        angular.forEach($scope.todoList, function (todo) {
            count += todo.done ? 0 : 1;
        });
        return count;
    };

    $scope.archive = function () {
        var oldTodos = $scope.todoList;
        $scope.todoList = [];
        angular.forEach(oldTodos, function (todo) {
            if (!todo.done) $scope.todoList.push(todo);
        });
    };
    $scope.deleteTodo = function (todo) {
        var msgBox = $dialog.messageBox('You are about to delete a todo from the database', 'This cannot be undone. Are you sure?', [
            {label: 'Yes', result: 'yes'},
            {label: 'Cancel', result: 'no'}
        ]);
        msgBox.open().then(function (result) {
            if (result === 'yes') {
                TodosResource.delete({id: todo.id}).$promise.then(function() {
                    $scope.todoList = TodosResource.query();
                });
            }
        });
    };
    $scope.updateTodo = function(todo) {
        console.log(todo);
        TodosResource.save(todo).$promise.then(function() {
            $scope.todoForm.todo = {};  // clear the form
            $scope.todoList = TodosResource.query();
        });
    }
    $scope.toggleTodo = function(todo)
    {
        if(!todo.done && !todo.inProgress)
        {
            todo.inProgress = true;
        }
        else if(todo.inProgress) {
            todo.done = true;
            todo.inProgress = false;
        }
        else if(todo.done && !todo.inProgress)
        {
            todo.inProgress = true;
            todo.done = false;
        }
        TodosResource.save(todo).$promise.then(function() {
            $scope.todoForm.todo = {};  // clear the form
            $scope.todoList = TodosResource.query();
        });
    }
};
