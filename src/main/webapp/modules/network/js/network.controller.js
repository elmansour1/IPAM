(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('NetworkController', NetworkController)
            .controller('NetworkDialogController', NetworkDialogController)
            .controller('NetworkDeleteController', NetworkDeleteController);


    //============  Generale Network Controller  ======================//

    NetworkController.$inject = [ 'Network'];

    function NetworkController(Network) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Network.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.networks = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  Network dialog controller ====================//

    NetworkDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Network'];

    function NetworkDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Network) {

    var vm = this;

        vm.network = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon id est ..."+vm.network.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.network.id !== null) {
               console.log("vm.network.id = "+vm.network.id+" vm.network.adress = "+vm.network.adress);
              Network.update({id : vm.network.id} ,vm.network, onSaveSuccess, onSaveError);
            } else {
                Network.save(vm.network, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : networkUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  Network delete controller ====================//

    NetworkDeleteController.$inject = ['$uibModalInstance', 'Network','entity'];

    function   NetworkDeleteController($uibModalInstance,Network,entity) {
         var vm = this;

        vm.network = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Network.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();






