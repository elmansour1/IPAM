(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('administrateur', {
                    parent: 'module',
                    url: '/administrateurs',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/administrateur/views/Administrateur.list.html',
                            controller: 'AdministrateurController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('administrateur.detail', {
                    parent: 'administrateur',
                    url: '/administrateur/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/administrateur/views/Administrateur.detail.html',
                                controller: 'AdministrateurDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Administrateur', function (Administrateur) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return Administrateur.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('administrateur', null, {reload: true});
                                    }, function () {
                                        $state.go('administrateur');
                                    });
                        }]
                })
                .state('administrateur.new', {
                    parent: 'administrateur',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/administrateur/views/Administrateur.create.html',
                                controller: 'AdministrateurDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: function () {
                                        return {
                                            name: null,
                                            email: null,
                                            password:null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('administrateur', null, {reload: true});
                            }, function () {
                                $state.go('administrateur');
                            });
                        }]
                })
                .state('administrateur.edit', {
                    parent: 'administrateur',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/administrateur/views/Administrateur.edit.html',
                                controller: 'AdministrateurDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Administrateur', function (Administrateur) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return Administrateur.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('administrateur', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('administrateur.delete', {
                    parent: 'administrateur',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/administrateur/views/Administrateur.delete.html',
                                controller: 'AdministrateurDeleteController',
                                controllerAs: 'vm',
                                size: 'sm',
                                resolve: {
                                  entity: ['Administrateur', function (Administrateur) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return Administrateur.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('administrateur', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
            }

})();
