(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('EquipementController', EquipementController)
            .controller('EquipementDialogController', EquipementDialogController)
            .controller('EquipementDeleteController', EquipementDeleteController);


    //============  Generale Equipement Controller  ======================//

    EquipementController.$inject = [ 'Equipement'];

    function EquipementController(Equipement) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Equipement.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.equipements = data;
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  Equipement dialog controller ====================//

    EquipementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Equipement'];

    function EquipementDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Equipement) {

    var vm = this;

        vm.equipement = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon id est ..."+vm.equipement.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.equipement.id !== null) {
               console.log("vm.equipement.id = "+vm.equipement.id+" vm.equipement.description = "+vm.equipement.description+" vm.equipement.marque = "+vm.equipement.marque);
               Equipement.update({id : vm.equipement.id} ,vm.equipement, onSaveSuccess, onSaveError);
            } else {
                Equipement.save(vm.equipement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : equipementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  Equipement delete controller ====================//

    EquipementDeleteController.$inject = ['$uibModalInstance', 'Equipement','entity'];

    function   EquipementDeleteController($uibModalInstance,Equipement,entity) {
         var vm = this;

        vm.equipement = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Equipement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();



