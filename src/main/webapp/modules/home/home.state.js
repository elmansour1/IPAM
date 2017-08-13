(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('accueil', {
                    parent: 'module',
                    url: '/accueil',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/home/views/home.html',
                            controller: 'HomeController',
                            controllerAs: 'vm',
                            size: 'lg'
                        }
                    }
                })
            }
        })();
//                .state('batiment.detail', {
//                    parent: 'batiment',
//                    url: '/batiment/{id}',
//                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
//                            $uibModal.open({
//                                templateUrl: 'modules/building/views/detail.building.html',
//                                controller: 'BuildingDialogController',
//                                controllerAs: 'vm',
//                                backdrop: 'static',
//                                size: 'sm',
//                                resolve: {
//                                    entity: ['Building', function (Building) {
//                                            console.log("valeur de id" + $stateParams.id);
//                                            return Building.get({id: $stateParams.id}).$promise;
//                                        }]
//                                }
//                            })
//                                    .result.then(function () {
//                                        $state.go('batiment', null, {reload: true});
//                                    }, function () {
//                                        $state.go('batiment');
//                                    });
//                        }]
//                })
//                .state('batiment.new', {
//                    parent: 'batiment',
//                    url: '/new',
//                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
//                            $uibModal.open({
//                                templateUrl: 'modules/building/views/createBuilding.html',
//                                controller: 'BuildingDialogController',
//                                controllerAs: 'vm',
//                                backdrop: 'static',
//                                size: 'sm',
//                                resolve: {
//                                    entity: function () {
//                                        return {
//                                            position: null,
//                                            name: null,
//                                            id: null
//                                        };
//                                    }
//                                }
//                            }).result.then(function () {
//                                $state.go('batiment', null, {reload: true});
//                            }, function () {
//                                $state.go('batiment');
//                            });
//                        }]
//                })
//                .state('batiment.edit', {
//                    parent: 'batiment',
//                    url: '/{id}/edit',
//                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
//                            $uibModal.open({
//                                templateUrl: 'modules/building/views/updateBuilding.html',
//                                controller: 'BuildingDialogController',
//                                controllerAs: 'vm',
//                                backdrop: 'static',
//                                size: 'sm',
//                                resolve: {
//                                    entity: ['Building', function (Building) {
//                                        console.log("valeur de id" + $stateParams.id);
//                                            return Building.get({id: $stateParams.id}).$promise;
//                                        }]
//                                }
//                            }).result.then(function () {
//                                $state.go('batiment', null, {reload: true});
//                            }, function () {
//                                $state.go('^');
//                            });
//                        }]
//                })
//                .state('batiment.delete', {
//                    parent: 'batiment',
//                    url: '/delete/{id}',
//                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
//                            $uibModal.open({
//                                templateUrl: 'modules/building/views/deleteBuilding.html',
//                                controller: 'BuildingDeleteController',
//                                controllerAs: 'vm',
//                                size: 'sm',
//                                resolve: {
//                                  entity: ['Building', function (Building) {
//                                          console.log("valeur de id peut etre ..." + $stateParams.id);
//                                          return Building.get({id: $stateParams.id}).$promise;
//                                      }]
//                                }
//                            }).result.then(function () {
//                                $state.go('batiment', null, {reload: true});
//                            }, function () {
//                                $state.go('batiment');
//                            });
//                        }]
//                });
//            }
//
//})();





