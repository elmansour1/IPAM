

var app = angular.module('ipam', [
    'ngResource',
    'ngAria',
    'ui.bootstrap',
    'ui.router'
]);

app.config(function ($stateProvider, $urlRouterProvider) {

   $urlRouterProvider.otherwise('/home');

    $stateProvider
            .state('app', {
                url:'/home',
                views: {
                    'navbarContent': {
                        templateUrl: 'partials/navbar.html',
                        controller: 'AppController',
                        controllerAs: 'vm'
                    },
                    'homeContent':{
                        templateUrl: 'partials/generic.entities.html',
                        controller: 'HomeController',
                        controllerAs: 'vm'
                    }
                }

            })
            ;
});
