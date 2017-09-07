(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('vlan', {
                    parent: 'module',
                    url: '/vlans',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/vlan/views/listOfVlan.html',
                            controller: 'VlanController',
                            controllerAs: 'vm',
                            size: 'lg'
                        }
                    }
                })
                .state('vlan.detail', {
                    parent: 'vlan',
                    url: '/vlan/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/vlan/views/detail.vlan.html',
                                controller: 'VlanDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Vlan', function (Vlan) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return Vlan.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('vlan', null, {reload: true});
                                    }, function () {
                                        $state.go('vlan');
                                    });
                        }]
                })
                .state('vlan.new', {
                    parent: 'vlan',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/vlan/views/createVlan.html',
                                controller: 'VlanDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: function () {
                                        return {
                                            number: null,
                                            name: null,
                                            batiment: null,
                                            reseau: null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('vlan', null, {reload: true});
                            }, function () {
                                $state.go('vlan');
                            });
                        }]
                })
                .state('vlan.edit', {
                    parent: 'vlan',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/vlan/views/updateVlan.html',
                                controller: 'VlanDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Vlan', function (Vlan) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return Vlan.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('vlan', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
                .state('vlan.delete', {
                    parent: 'vlan',
                    url: '/delete/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/vlan/views/deleteVlan.html',
                                controller: 'VlanDeleteController',
                                controllerAs: 'vm',
                                size: 'sm',
                                resolve: {
                                  entity: ['Vlan', function (Vlan) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return Vlan.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('vlan', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                });
            }

})();


