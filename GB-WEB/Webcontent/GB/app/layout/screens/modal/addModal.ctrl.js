(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('addModalCtr', addModalCtr);

    addModalCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function addModalCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        vm.order = {};
        var params = {};


        vm.ok = function() {


            return authfactory.createOrder(vm.order).then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data;
                    $scope.$emit("addOrder", {
                        order: response
                    });
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });


        };

        vm.saveSupplier = function() {

            return authfactory.createSupplier(vm.order).then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data;
                    $scope.$emit("supplierOrder", {
                        supplier: response
                    });
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        };


        vm.getSuppliers = function() {

            return authfactory.getSupplierId().then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data;

                    vm.suppliyers = response;
                    return response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        };

        vm.getSuppliers();

        vm.cancel = function() {
            $scope.$emit("cancelModal");
        };

    }
})();
