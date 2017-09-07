var app = angular.module('ipam',['angularUtils.directives.dirPagination',
   'ngResource',
   'ngAria',
   'ui.bootstrap',
   'ui.router'
]);


app.config(function($stateProvider, $urlRouterProvider){
   
    $urlRouterProvider.otherwise('/');
    
    $stateProvider
            .state('app', {
                url:'/',
                views: {
                    'navbarContent': {
                        templateUrl: 'partials/navbar.html',
                        controller: 'AppController',
                        controllerAs: 'vm'
                    },
                    'homeContent': {
                        templateUrl: 'partials/generic.entities.html',
                        controller: 'HomeController',
                        controllerAs: 'vm'
                    }
                }
    });
});




