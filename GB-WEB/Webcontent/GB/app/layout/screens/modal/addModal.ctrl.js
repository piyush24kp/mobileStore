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
                    response = response.data.databean;
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
                    response = response.data.databean;
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

        vm.saveBrand = saveBrand;

        function saveBrand() {
            return authfactory.saveBrand(vm.order).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    $scope.$emit("brandOrder", {
                        brand: response
                    });
                    $scope.$emit("cancelModal");
                    return response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }


        vm.getSuppliers = function() {

            return authfactory.getSupplierId().then(function successCallback(response) {

                if (response.status === 200) {
                    response = response.data.databean;

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

        vm.getBrand = getBrand;

        function getBrand() {
            return authfactory.getBrand(params).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.brandDetail = response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.getBrand();

        vm.changeBrand = changeBrand;

        function changeBrand(brandId) {
            return authfactory.changeBrand(brandId).then(function successCallback(response) {
                if (response.status === 200) {
                    response = response.data.databean;
                    vm.modelDetail = response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }


        vm.cancel = function() {
            $scope.$emit("cancelModal");
        };

    }
})();
