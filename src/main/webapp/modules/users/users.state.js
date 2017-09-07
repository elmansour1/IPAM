(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('user', {
                    parent: 'module',
                    url: '/users',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/users/views/listOfUser.html',
                            controller: 'UserController',
                            controllerAs: 'vm',
                            size: 'lg'
                        }
                    }
                })
                .state('user.detail', {
                    parent: 'user',
                    url: '/{id}/user',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/users/detail.user.html',
                                controller: 'UserDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['User', function (User) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return User.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('user', null, {reload: true});
                                    }, function () {
                                        $state.go('user');
                                    });
                        }]
                })
                .state('user.new', {
                    parent: 'user',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/users/views/createUser.html',
                                controller: 'UserDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: function () {
                                        return {
                                            matricule: null,
                                            first_name: null,
                                            last_name: null,
                                            poste:null,
                                            sexe: null,
                                            id:null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('user', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('user.edit', {
                    parent: 'user',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/users/updateUser.html',
                                controller: 'UserDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['User', function (User) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return User.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('user', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('user.delete', {
                    parent: 'user',
                    url: '/delete/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/users/deleteUser.html',
                                controller: 'UserDeleteController',
                                controllerAs: 'vm',
                                size: 'sm',
                                resolve: {
                                  entity: ['User', function (User) {
                                          console.log("valeur de id" + $stateParams.id);
                                            return User.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('user', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();


