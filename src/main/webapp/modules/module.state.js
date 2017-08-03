(function () {
    'use strict';

    angular
            .module('ipam')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('module', {
            abstract: true,
            parent: 'app'
        });
    }
})();
