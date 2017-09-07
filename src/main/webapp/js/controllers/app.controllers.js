(function() {
    'use strict';

    angular
        .module('ipam')
        .controller('AppController', AppController);

    ourController.$inject = ['$scope'];

    function AppController ($scope) {
       $scope.information =" IP Adress Management ...";
    }
})();
