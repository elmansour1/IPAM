(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('UserController', UserController)
            .controller('UserDialogController', UserDialogController)
            .controller('UserDeleteController', UserDeleteController);


    //============  Generale User Controller  ======================//

    UserController.$inject = [ 'User'];

    function UserController(User) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            User.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.users = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  User dialog controller ====================//

    UserDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'User'];

    function UserDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, User) {

    var vm = this;

        vm.user = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon matricule est ..."+vm.user.matricule);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.user.matricule !== null) {
               console.log("vm.user.matricule = "+vm.user.matricule+" vm.user.firstName = "+vm.user.firstName);
               User.update({matricule : vm.user.matricule} ,vm.user, onSaveSuccess, onSaveError);
            } else {
                User.save(vm.user, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : userUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  user delete controller ====================//

   UserDeleteController.$inject = ['$uibModalInstance', 'User','entity'];

    function   UserDeleteController($uibModalInstance,User,entity) {
         var vm = this;

        vm.user = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (matricule) {
              User.delete({matricule: matricule},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();



