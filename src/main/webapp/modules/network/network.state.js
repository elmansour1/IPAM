(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('network', {
                    parent: 'module',
                    url: '/networks',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/network/views/listOfNetwork.html',
                            controller: 'NetworkController',
                            controllerAs: 'vm',
                            size: 'lg'
                        }
                    }
                })
                .state('network.detail', {
                    parent: 'network',
                    url: '/network/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/network/views/detail.network.html',
                                controller: 'NetworkDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'lg',
                                resolve: {
                                    entity: ['Network', function (Network) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return Network.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('networkt', null, {reload: true});
                                    }, function () {
                                        $state.go('network');
                                    });
                        }]
                })
                .state('network.new', {
                    parent: 'network',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/network/views/create.network.html',
                                controller: 'NetworkDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: function () {
                                        return {
                                            adress: null,
                                            description: null,
                                            vlan: null,
                                            masque: null,
                                            gateway: null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('network', null, {reload: true});
                            }, function () {
                                $state.go('network');
                            });
                        }]
                })
                .state('network.edit', {
                    parent: 'network',
                    url: '/edit/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/network/views/editNetwork.html',
                                controller: 'NetworkDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Network', function (Network) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return Network.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('network', null, {reload: true});
                            }, function () {
                                $state.go('network');
                            });
                        }]
                })
                .state('network.delete', {
                    parent: 'network',
                    url: '/delete/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/network/views/deleteNetwork.html',
                                controller: 'NetworkDeleteController',
                                controllerAs: 'vm',
                                size: 'sm',
                                resolve: {
                                  entity: ['Network', function (Network) {
                                          console.log("value of id can be ..." + $stateParams.id);
                                          return Network.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('network', null, {reload: true});
                            }, function () {
                                $state.go('^');
                            });
                        }]
                })
            }

})();





