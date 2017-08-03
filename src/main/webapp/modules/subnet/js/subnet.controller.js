(function () {
    'use strict';

    angular
            .module('ipam')
            .controller('SubnetController', SubnetController)
            .controller('SubnetDialogController', SubnetDialogController)
            .controller('SubnetDeleteController', SubnetDeleteController);


    //============  Generale Subnet Controller  ======================//

    SubnetController.$inject = [ 'Subnet'];

    function SubnetController(Subnet) {
        var vm = this;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {

            Subnet.query(null, onSuccess, onError);

            function onSuccess(data, headers) {
                vm.subnets = data;
                //console.log('je suis ...'+data[0]._id);
            }
            function onError(error) {
                Console.log(error.data.message);
            }
        }

    };

    //============  subnet dialog controller ====================//

    SubnetDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Subnet'];

    function SubnetDialogController($timeout, $scope, $stateParams, $uibModalInstance, entity, Subnet) {

    var vm = this;

        vm.subnet = entity;
        vm.clear =clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
        console.log("mon non est ..."+vm.subnet.id);
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.subnet.id !== null) {
               console.log("vm.subnet.id = "+vm.subnet.id+" vm.subnet.nom = "+vm.subnet.nom);
               Subnet.update({id : vm.subnet.id} ,vm.subnet, onSaveSuccess, onSaveError);
            } else {
                Subnet.save(vm.subnet, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : subnetUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }
    };

    //============  Subnet delete controller ====================//

    SubnetDeleteController.$inject = ['$uibModalInstance', 'Subnet','entity'];

    function   SubnetDeleteController($uibModalInstance,Subnet,entity) {
         var vm = this;

        vm.subnet = entity;
        vm.clear = clear;
        vm.confirmDelete = function(entity){
            //if(confirm("Do you want to realy delete this Building ?")){
                entity.remove({
                        id: entity.id
                    }, function(){
                        var id;
                        for(var i=0; i<entity.length; i++){
                            if(entity[i].id === entity.id){
                                id = i;
                                break;
                            }
                        }
                        if(id){
                            entity.splice(id,1);
                        }
                    });
            //}
        };

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
              Subnet.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }

})();





