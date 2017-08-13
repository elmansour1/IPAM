(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('equipement', {
                    parent: 'module',
                    url: '/equipements',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/equipement/views/listOfEquipement.html',
                            controller: 'EquipementController',
                            controllerAs: 'vm',
                            size: 'lg'
                        }
                    }
                })
                .state('equipement.detail', {
                    parent: 'equipement',
                    url: '/equipement/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/equipement/views/detail.equipement.html',
                                controller: 'EquipementDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Equipement', function (Equipement) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return Equipement.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('equipement', null, {reload: true});
                                    }, function () {
                                        $state.go('equipement');
                                    });
                        }]
                })
                .state('equipement.new', {
                    parent: 'equipement',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/equipement/views/createEquipement.html',
                                controller: 'EquipementDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: function () {
                                        return {
                                            marque: null,
                                            description:null,
                                            users: null,
                                            reseau:null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('equipement', null, {reload: true});
                            }, function () {
                                $state.go('equipement');
                            });
                        }]
                })
                .state('equipement.edit', {
                    parent: 'equipement',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/equipement/views/updateEquipement.html',
                                controller: 'EquipementDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Equipement', function (Equipement) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return Equipement.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('equipement', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('equipement.delete', {
                    parent: 'equipement',
                    url: '/delete/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/equipement/views/deleteEquipement.html',
                                controller: 'EquipementDeleteController',
                                controllerAs: 'vm',
                                size: 'sm',
                                resolve: {
                                  entity: ['Equipement', function (Equipement) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return Equipement.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('equipement', null, {reload: true});
                            }, function () {
                                $state.go('equipement');
                            });
                        }]
                });
            }

})();


