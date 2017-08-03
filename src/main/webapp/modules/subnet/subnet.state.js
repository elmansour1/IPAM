(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig ($stateProvider) {
        $stateProvider
                .state('subnet', {
                    parent: 'module',
                    url: '/subnets',
                    views: {
                        'moduleContent@app': {
                            templateUrl: 'modules/subnet/views/listOfSubnet.html',
                            controller: 'SubnetController',
                            controllerAs: 'vm',
                            size: 'lg'
                        }
                    }
                })
                .state('subnet.detail', {
                    parent: 'subnet',
                    url: '/subnet/{id}',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/subnet/views/detail.subnet.html',
                                controller: 'SubnetDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Subnet', function (Subnet) {
                                            console.log("valeur de id" + $stateParams.id);
                                            return Subnet.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            })
                                    .result.then(function () {
                                        $state.go('subnet', null, {reload: true});
                                    }, function () {
                                        $state.go('subnet');
                                    });
                        }]
                })
                .state('subnet.new', {
                    parent: 'subnet',
                    url: '/new',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/subnet/views/create.subnet.html',
                                controller: 'SubnetDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: function () {
                                        return {
                                            adress: null,
                                            description: null,
                                            equipment: null,
                                            reseau: null,
                                            id: null
                                        };
                                    }
                                }
                            }).result.then(function () {
                                $state.go('subnet', null, {reload: true});
                            }, function () {
                                $state.go('subnet');
                            });
                        }]
                })
                .state('subnet.edit', {
                    parent: 'subnet',
                    url: '/{id}/edit',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/subnet/views/edit.subnet.html',
                                controller: 'SubnetDialogController',
                                controllerAs: 'vm',
                                backdrop: 'static',
                                size: 'sm',
                                resolve: {
                                    entity: ['Subnet', function (Subnet) {
                                        console.log("valeur de id" + $stateParams.id);
                                            return Subnet.get({id: $stateParams.id}).$promise;
                                        }]
                                }
                            }).result.then(function () {
                                $state.go('subnet', null, {reload: true});
                            }, function () {
                                $state.go('subnet');
                            });
                        }]
                })
                .state('subnet.delete', {
                    parent: 'subnet',
                    url: '/{id}/delete',
                    onEnter: ['$stateParams', '$state', '$uibModal', function ($stateParams, $state, $uibModal) {
                            $uibModal.open({
                                templateUrl: 'modules/subnet/views/delete.subnet.html',
                                controller: 'SubnetDeleteController',
                                controllerAs: 'vm',
                                size: 'sm',
                                resolve: {
                                  entity: ['Subnet', function (Subnet) {
                                          console.log("valeur de id peut etre ..." + $stateParams.id);
                                          return Subnet.get({id: $stateParams.id}).$promise;
                                      }]
                                }
                            }).result.then(function () {
                                $state.go('subnett', null, {reload: true});
                            }, function () {
                                $state.go('subnet');
                            });
                        }]
                })
            }

})();




