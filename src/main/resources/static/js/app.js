var stocksApp = angular.module("stocksApp", ['ui.materialize', 'ngRoute', 'ngResource']);

stocksApp.config(stateConfig);
function stateConfig($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix('');
  $routeProvider
    //HOME Controller
    .when("/", {
      templateUrl:"home_.html",
      controller:"homeController"
    })
    //CREATE controllers
    .when("/stocks/add/categorie", {
      templateUrl:"views/ajouterCategorie.html",
      controller:"newCategorieController"
    })
    .when("/stocks/add/client", {
      templateUrl:"views/newClient.html",
      controller:"newClientController"
    })
    .when("/stocks/add/employe", {
      templateUrl:"views/newEmploye.html",
      controller:"newEmployeController"
    })
    .when("/stocks/add/achat", {
      templateUrl:"views/newAchat.html",
      controller:"newAchatController"
    })
    .when("/stocks/add/vente", {
      templateUrl:"views/newVente.html",
      controller:"newVenteController"
    })
    //EDIT controllers
    .when("/stocks/edit/categorie/:idCategorie", {
      templateUrl:"views/editCategorie.html",
      controller:"newCategorieController"
    })
    .when("/stocks/edit/client/:idClient", {
      templateUrl:"views/editClient.html",
      controller:"newClientController"
    })
    .when("/stocks/edit/employe/:idEmploye", {
      templateUrl:"views/editEmploye.html",
      controller:"newEmployeController"
    })
    .when("/stocks/edit/achat/:idAchat", {
      templateUrl:"views/editAchat.html",
      controller:"newAchatController"
    })
    .when("/stocks/edit/vente/:idVente", {
      templateUrl:"views/editVente.html",
      controller:"newVenteController"
    })
    //LIST controllers
    .when("/stocks/listes/categories", {
      templateUrl:"views/listCategories.html",
      controller:"newCategorieController"
    })
    .when("/stocks/listes/clients", {
      templateUrl:"views/listeClients.html",
      controller:"newClientController"
    })
    .when("/stocks/listes/employes", {
      templateUrl:"views/listeEmployes.html",
      controller:"newEmployeController"
    })
    .when("/stocks/listes/achats", {
      templateUrl:"views/listeAchats.html",
      controller:"newAchatController"
    })
    .when("/stocks/listes/ventes", {
      templateUrl:"views/listeVentes.html",
      controller:"newVenteController"
    });
}
