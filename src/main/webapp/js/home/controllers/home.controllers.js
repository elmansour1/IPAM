(function() {
    'use strict';

    angular
        .module('ipam')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state'];

    function HomeController ($scope, $state) {

       $scope.help ="That is initial step ;please we are working it now, thank ...";

         //update different pages ...
         $scope.actualisation = function () {
             $scope.tab = 1;
             $state.go('network');
          };
         $scope.actualisation();

          $scope.isSet = function(checkTab) {
            return $scope.tab === checkTab;
          };

          $scope.setTab = function(activeTab) {
            $scope.tab = activeTab;
          };
            $scope.onclickNetwork = function () {
                $scope.setTab(1);
                $state.go('network');
          };
          $scope.onclickBuilding = function () {
                $scope.setTab(2);
                $state.go('batiment');
          };
          $scope.onclickSubnet = function () {
                $scope.setTab(3);
                $state.go('subnet');
          };
          $scope.onclickVlan = function () {
                $scope.setTab(4);
                $state.go('vlan');
          };
          $scope.onclickUsers = function () {
                $scope.setTab(5);
                $state.go('user');
          };
           $scope.onclickHelp = function () {
                $scope.setTab(6);
                $state.go('help');
          };
          $scope.onclickAbout = function () {
                $scope.setTab(7);
                $state.go('about');
          };
    }
})();
