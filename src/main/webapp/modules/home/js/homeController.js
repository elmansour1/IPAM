//(function () {
//    'use strict';
//
//    angular
//            .module('ipam')
//            .controller('HomeController', HomeController);
//
//
//    //============  Generale Home Controller  ======================//
//
//    HomeController.$inject = [ 'Home'];
//
//    function HomeController(Home) {
//        var vm = this;
//        vm.loadAll = loadAll;
//
//        loadAll();
//
//        function loadAll() {
//
//            Home.query(null, onSuccess, onError);
//
//            function onSuccess(data, headers) {
//                vm.accueils = data;
//                //console.log('je suis ...'+data[0]._id);
//            }
//            function onError(error) {
//                Console.log(error.data.message);
//            }
//        }
//
//    };
//    
//})();
//
//
