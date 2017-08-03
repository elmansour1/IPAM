(function () {
    'use strict';

    angular
            .module('memoire')
            .controller('AuteurController', AuteurController)
            .controller('AuteurDialogController', AuteurDialogController)
            .controller('AuteurDeleteController', AuteurDeleteController);


    //============  Generale Author Controller  ======================//

    AuteurController.$inject = [ 'Auteur'];

    function AuteurController(Auteur) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Auteur.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.auteurs = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  Author dialog controller ====================//

    AuteurDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Auteur'];

    function AuteurDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Auteur) {

    var vm = this;

        vm.auteur = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon non est ..."+vm.auteur.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.auteur.id !== null) {
               console.log("vm.auteur.id = "+vm.auteur.id+" vm.auteur.nom = "+vm.auteur.nom);
               Auteur.update({id : vm.auteur.id} ,vm.auteur, onSaveSuccess, onSaveError);
            } else {
                Auteur.save(vm.auteur, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('memoire :auteurUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  Author delete controller ====================//

    AuteurDeleteController.$inject = ['$uibModalInstance', 'Auteur','entity'];

    function   AuteurDeleteController($uibModalInstance,Auteur,entity) {
         var vm = this;

        vm.auteur = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Auteur.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();
