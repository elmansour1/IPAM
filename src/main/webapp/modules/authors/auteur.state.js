(function () {
    'use strict';

    angular
            .module('memoire')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('auteur', {
                    parent: 'module',
                    url: '/auteurs',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/authors/views/auteurs.list.html',
                            controller: 'AuteurController',
                            controllerAs: 'vm'
                        }
                    }
                })
                .state('auteur.detail', {
                    parent: 'auteur',
                    url: '/auteur/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/authors/views/auteur.detail.html',
                                controller: 'AuteurDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['Auteur', function (Auteur) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return Auteur.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('auteur', null, {reload: true});
                                    }, function () {
                                        $state.go('auteur');
                                    });
                        }]
                })
                .state('auteur.new', {
                    parent: 'auteur',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/authors/views/auteur.create.html',
                                controller: 'AuteurDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: function () {
                                        return {
                                            matricule: null,
                                            nom: null,
                                            telephone: null,
                                            email: null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('auteur', null, {reload: true});
                            }, function () {
                                $state.go('auteur');
                            });
                        }]
                })
                .state('auteur.edit', {
                    parent: 'auteur',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/authors/views/auteur.edit.html',
                                controller: 'AuteurDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'md',
                                resolve: {
                                    entity: ['Auteur', function (Auteur) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return Auteur.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('auteur', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('auteur.delete', {
                    parent: 'auteur',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/authors/views/auteur.delete.html',
                                controller: 'AuteurDeleteController',
                                controllerAs: 'vm',
                                size: 'md',
                                resolve: {
                                  entity: ['Auteur', function (Auteur) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return Auteur.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('auteur', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
            }

})();
