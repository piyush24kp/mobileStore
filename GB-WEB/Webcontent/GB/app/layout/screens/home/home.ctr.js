(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('homeCtr', homeCtr);

    homeCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function homeCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;
        var params = {};
        vm.allUsers = [];

        vm.myInterval = 5000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.itemsByPage = 10;
        var currIndex = 0;
        vm.slides = [{
            image: 'GB/assets/images/carousel-1.jpg',
            text: ['Nice image', 'Awesome photograph', 'That is so cool', 'I love that'],
            id: currIndex++
        }, {
            image: 'GB/assets/images/carousel-2.jpg',
            text: ['Nice image', 'Awesome photograph', 'That is so cool', 'I love that'],
            id: currIndex++
        }, {
            image: 'GB/assets/images/carousel-3.jpg',
            text: ['Nice image', 'Awesome photograph', 'That is so cool', 'I love that'],
            id: currIndex++
        }];

        activate();

        function activate() {

        }

        vm.getOrders = getOrders;

        function getOrders() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getOrders(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data;

                    vm.orderDetails = response;
                    vm.rowCollection = response;

                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });
        }

        vm.getOrders();

        $rootScope.$on('addOrder', function(event, args) {
            vm.orderDetails.push(args.order);
            vm.rowCollection.push(args.order);
        });


        vm.getSupplier = getSupplier;

        function getSupplier() {
            resetParam();
            /*if (!uid) {
                return false;
            }
            params.uid = uid;*/
            return authfactory.getSupplier(params).then(function successCallback(response) {
                if (response.status === 200) {

                    response = response.data;

                    vm.supplierDetail = response;
                    vm.suppDetailCollection = response;

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
            }

        }



        vm.searchUser = searchUser;

        function searchUser(name) {
            resetParam();
            if (!name) {
                return false;
            }
            params.name = name;
            return authfactory.getUser(params).then(function successCallback(response) {
                vm.allUsers = [];
                if (response.status === 200) {
                    response = response.data;

                    vm.allUsers = response;
                    return response;
                }
            }, function errorCallback(response) {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                return false;
            });

        }

        function resetParam() {
            params = {};
        }

    }
})();
