(function() {
    'use strict';

    angular
        .module('ipam')
        .controller('AppController', ourController);

    ourController.$inject = ['$scope'];

    function ourController ($scope) {
       $scope.information =" IP Adress Management ...";
    }
})();
