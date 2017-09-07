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
               // console.log('je suis ...'+data[0].id);
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
            //calculeIP(vm.network.adress,vm.network.masque);
            vm.adresse = vm.network.adress.split(".");
            vm.Poctet = vm.adresse[0];
            vm.Doctet = vm.adresse[1];
            vm.Toctet = vm.adresse[2];
            vm.Qoctet = vm.adresse[3];
            console.log(vm.Poctet);
            console.log(vm.Doctet);
            console.log(vm.Toctet);
            console.log(vm.Qoctet);
            if((vm.Poctet>=0 && vm.Poctet<=255) &&(vm.Doctet>=0 && vm.Doctet<=255) &&(vm.Toctet>=0 && vm.Toctet<=255) &&(vm.Qoctet>=0 && vm.Qoctet<=255) && (vm.network.masque>=0 && vm.network.masque<=32)){
                vm.ipBin={};
                    vm.ipBin[1]=String("00000000"+parseInt(vm.Poctet,10).toString(2)).slice(-8);
                    vm.ipBin[2]=String("00000000"+parseInt(vm.Doctet,10).toString(2)).slice(-8);
                    vm.ipBin[3]=String("00000000"+parseInt(vm.Toctet,10).toString(2)).slice(-8);
                    vm.ipBin[4]=String("00000000"+parseInt(vm.Qoctet,10).toString(2)).slice(-8);
                    
                     console.log(vm.ipBin[1]);
                        console.log(vm.ipBin[2]);
                         console.log(vm.ipBin[3]);
                          console.log(vm.ipBin[4]);
                          
                           vm.standartClass="";
    
                if(vm.Poctet<=126) {

                         vm.standartClass="A";
                    }else if (vm.Poctet===127) {

                         vm.standartClass="loopback IP";
                    }else if (vm.Poctet>=128 && vm.Poctet<=191) {
                         vm.standartClass="B";
                    }else if (vm.Poctet>=192 && vm.Poctet<=223) {
                         standartClass="C";
                     }else if (vm.Poctet>=224 && vm.Poctet<=239) {
                        vm.standartClass="D (Multicast Address)";
                     }else if (vm.Poctet>=240 && vm.Poctet<=225) {
                         standartClass="E (Experimental)";
                    }else {
                        vm.standartClass="Out of range";
                }
                console.log("la classe est " +vm.standartClass);
                
                //netmask
                     vm.mask=vm.network.masque;
                    vm.importantBlock=Math.ceil(vm.mask/8);
                    vm.importantBlockBinary=vm.ipBin[vm.importantBlock];
                    vm.maskBinaryBlockCount=vm.mask%8;
                    if(vm.maskBinaryBlockCount===0)vm.importantBlock++;
                    vm.maskBinaryBlock="";
                    vm.maskBlock="";
                    
                    for(var i=1; i<=8; i++){
                        if(vm.maskBinaryBlockCount>=i){
                            vm.maskBinaryBlock+="1";
                        }else{
                            vm.maskBinaryBlock+="0";
                        }
                    }
                    
                    //convert binary mask block to decimal
                    vm.maskBlock=parseInt(vm.maskBinaryBlock,2);

                    console.log(" je ne le connais pas " +vm.maskBlock);
                    
                    //net & broadcast addr
                    vm.netBlockBinary="";
                    vm.bcBlockBinary="";
                    for(var i=1;i<=8;i++){

                        if(vm.maskBinaryBlock.substr(i-1,1)==="1"){
                            vm.netBlockBinary+=vm.importantBlockBinary.substr(i-1,1);
                            vm.bcBlockBinary+=vm.importantBlockBinary.substr(i-1,1);
                        }else{
                            vm.netBlockBinary+="0";
                            vm.bcBlockBinary+="1";
                        }
                    }
                    
                    console.log(vm.netBlockBinary);
                    console.log(vm.bcBlockBinary);
                    
                    
                    //put everything together, create a string container variables
        vm.mask="";
        vm.maskBinary="";
        vm.net="";
        vm.bc="";
        vm.netBinary="";
        vm.bcBinary="";
        vm.rangeA="";
        vm.rangeB="";
        
        //loop to put whole strings block together
    for(var i=1;i<=4;i++){
         if(vm.importantBlock>i) {
             //blocks before the important block.
                vm.mask+="255";
                vm.maskBinary+="11111111";
                vm.netBinary+=vm.ipBin[i];
                vm.bcBinary+=vm.ipBin[i];
                vm.net+=parseInt(vm.ipBin[i],2);
                vm.bc+=parseInt(vm.ipBin[i],2);
                vm.rangeA+=parseInt(vm.ipBin[i],2);
                vm.rangeB+=parseInt(vm.ipBin[i],2);
                
                }else if (vm.importantBlock===i) {
                    //the important block.
                    vm.mask+=vm.maskBlock;
                    vm.maskBinary+=vm.maskBinaryBlock;
                    vm.netBinary+=vm.netBlockBinary;
                    vm.bcBinary+=vm.bcBlockBinary;
                    vm.net+=parseInt(vm.netBlockBinary,2);
                    vm.bc+=parseInt(vm.bcBlockBinary,2);
                    vm.rangeA+=(parseInt(vm.netBlockBinary,2)+1);
                    vm.rangeB+=(parseInt(vm.bcBlockBinary,2)-1);
                    }else {
                        //block after the important block.
                        vm.mask+=0;
                        vm.maskBinary+="00000000";
                        vm.netBinary+="00000000";
                        vm.bcBinary+="11111111";
                        vm.net+="0";
                        vm.bc+="255";
                        vm.rangeA+=0;
                        vm.rangeB+=255;
                        
                    }
                    
                    //add . separator except the last block
                        if(i<4){
                        vm.mask+=".";
                        vm.maskBinary+=".";
                        vm.netBinary+=".";
                        vm.bcBinary+=".";
                        vm.net+=".";
                        vm.bc+=".";
                        vm.rangeA+=".";
                        vm.rangeB+=".";
                        
                        }
                }
                
                 vm.network.masque = vm.mask;
                vm.network.nid = vm.net;
                vm.network.broadcast = vm.bc;
                vm.network.plage = vm.rangeA + " - " + vm.rangeB;
                vm.network.classe = vm.standartClass;
                
                console.log(vm.network.masque);
                console.log(vm.network.nid);
                console.log(vm.network.broadcast);
                console.log(vm.network.plage);
                console.log(vm.network.classe);
                
         
                    
               // alert("cool");
                
                vm.isSaving = true;
            console.log("le masque est" +vm.network.masque);
            
            if (vm.network.id !== null) {
               console.log("vm.network.id = "+vm.network.id+" vm.network.adress = "+vm.network.adress+ ""+vm.network.masque);
//              
              Network.update({id : vm.network.id} ,vm.network, onSaveSuccess, onSaveError);
              alert(" Modification effectuer avec succes");
            } else {
//               
                Network.save(vm.network, onSaveSuccess, onSaveError);
               // alert("Enregistrement Effectuer");
            }
                
            }else{
                alert("Adresse Invalide");
            }
            
        }

        function onSaveSuccess (result) {
            $scope.$emit('ipam : networkUpdate', result);
            $uibModalInstance.close(result);
            alert('Enregistrement Effectuer avec succes');
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
            alert('error');
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






