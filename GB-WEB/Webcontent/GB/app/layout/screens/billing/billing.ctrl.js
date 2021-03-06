(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('billingCtr', billingCtr);

    billingCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function billingCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        var params = {};
        vm.itemsByPage = 10;



        function activate() {
            vm.getSellOrders();
        }

        vm.getSellOrders = getSellOrders;

        function getSellOrders() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getSellOrders(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data.databean;

                    vm.sellDetail = response;
                    vm.sellDetailList = response;

                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.selectTab = selectTab;

        function selectTab(tab) {
            resetParam();

            if (tab === 'supplier') {
                vm.getSupplier();
            } else if (tab === 'brand') {
                vm.getBrand();
            } else if (tab === 'model') {
                vm.getModel();
            } else if (tab === 'category') {

            }
        }

        function resetParam() {
            params = {};
        }

        activate();

    }
})();
