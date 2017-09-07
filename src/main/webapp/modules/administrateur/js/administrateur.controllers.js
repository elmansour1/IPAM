(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('AdministrateurController', AdministrateurController)
            .controller('AdministrateurDialogController', AdministrateurDialogController)
            .controller('AdministrateurDeleteController', AdministrateurDeleteController);


    //============  Generale Administrateur Controller  ======================//

    AdministrateurController.$inject = [ 'Administrateur'];

    function AdministrateurController(Administrateur) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Administrateur.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.administrateurs = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  Administrateur dialog controller ====================//

    AdministrateurDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Administrateur'];

    function AdministrateurDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Administrateur) {

    var vm = this;

        vm.administrateur = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon id est ..."+vm.administrateur.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.administrateur.id !== null) {
               console.log("vm.administrateur.id = "+vm.administrateur.id+" vm.administrateur.name = "+vm.administrateur.name);
               Administrateur.update({id : vm.administrateur.id} ,vm.administrateur, onSaveSuccess, onSaveError);
            } else {
                Administrateur.save(vm.administrateur, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam :administrateurUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  Administrateur delete controller ====================//

    AdministrateurDeleteController.$inject = ['$uibModalInstance', 'Administrateur','entity'];

    function   AdministrateurDeleteController($uibModalInstance,Administrateur,entity) {
         var vm = this;

        vm.administrateur = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Administrateur.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();
