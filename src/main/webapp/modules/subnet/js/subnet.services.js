
(function() {
    'use strict';
    angular
        .module('ipam')
        .factory('Subnet', SubnetFonction);

    SubnetFonction.$inject = ['$resource'];

    function SubnetFonction ($resource) {
        var resourceUrl =  'api/subnets/:id';

        return $resource(resourceUrl, {id:'@id'}, {
            /*'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    return angular.toJson(data);
                }
            },
            'delete':{
                method: 'DELETE',
                transform: function(data){
                    return angular.toJson(data);
                }
            }*/
            update:{
                method: 'PUT'
            }
        });
    }
})();






