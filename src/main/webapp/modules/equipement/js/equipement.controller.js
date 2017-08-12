(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('BuildingController', BuildingController)
            .controller('BuildingDialogController', BuildingDialogController)
            .controller('BuildingDeleteController', BuildingDeleteController);


    //============  Generale Building Controller  ======================//

    BuildingController.$inject = [ 'Building'];

    function BuildingController(Building) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Building.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.batiments = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  Building dialog controller ====================//

    BuildingDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Building'];

    function BuildingDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Building) {

    var vm = this;

        vm.batiment = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon id est ..."+vm.batiment.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.batiment.id !== null) {
               console.log("vm.batiment.id = "+vm.batiment.id+" vm.batiment.nom = "+vm.batiment.nom);
               Building.update({id : vm.batiment.id} ,vm.batiment, onSaveSuccess, onSaveError);
            } else {
                Building.save(vm.batiment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : batimentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  Building delete controller ====================//

    BuildingDeleteController.$inject = ['$uibModalInstance', 'Building','entity'];

    function   BuildingDeleteController($uibModalInstance,Building,entity) {
         var vm = this;

        vm.batiment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Building.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();



