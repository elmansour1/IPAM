(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('VlanController', VlanController)
            .controller('VlanDialogController', VlanDialogController)
            .controller('VlanDeleteController', VlanDeleteController);


    //============  Generale Vlan Controller  ======================//

    VlanController.$inject = [ 'Vlan'];

    function VlanController(Vlan) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Vlan.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.vlans = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  Vlan dialog controller ====================//

    VlanDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Vlan'];

    function VlanDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Vlan) {

    var vm = this;

        vm.vlan = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon id est ..."+vm.vlan.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.vlan.id !== null) {
               console.log("vm.vlan.id = "+vm.vlan.id+" vm.vlan.nom = "+vm.vlan.nom);
               Vlan.update({id : vm.vlan.id} ,vm.vlan, onSaveSuccess, onSaveError);
            } else {
                Vlan.save(vm.vlan, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : vlanUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
            alert('Le Vlan a été enrégistrer avec succès');
        }

        function onSaveError () {
            alert('error');
            vm.isSaving = false;
        }
    };

    //============  Vlan delete controller ====================//

    VlanDeleteController.$inject = ['$uibModalInstance', 'Vlan','entity'];

    function   VlanDeleteController($uibModalInstance,Vlan,entity) {
         var vm = this;

        vm.vlan = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Vlan.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();



