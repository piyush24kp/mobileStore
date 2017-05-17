(function() {
    'use strict';

    angular
        .module('app.widgets')
        .factory('authfactory', authfactory);

    authfactory.$inject = ['$http', '$q', 'config'];

    function authfactory($http, $q, config) {
        var isPrimed = false;
        var primePromise;

        var service = {
            getParamUrl: getParamUrl,
            getOrders: getOrders,
            createOrder: createOrder,
            createSupplier: createSupplier,
            getSupplier: getSupplier,
            getSupplierId: getSupplierId,
            getBrand: getBrand,
            getModel: getBrand,
            changeBrand: changeBrand,
            saveBrand: saveBrand
        };
        return service;

        function getParamUrl(url, param) {
            if (param) {
                if (param.uid) {
                    url = url + "?uid=" + param.uid;
                } else if (param.name) {
                    url = url + "?name=" + param.name;
                }


            }
            return url;
        }

        function getOrders(param) {
            var url = '/stock/getOrders';
            url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function createOrder(data) {
            var url = '/stock/setOrders';

            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function createSupplier(data) {
            var url = '/stock/createSupplier';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function saveBrand(data) {
            var url = '/stock/createBrands';
            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function getSupplier(param) {
            var url = '/stock/getSupplier';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getSupplierId(param) {
            var url = '/stock/getSupplierId';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getBrand(param) {
            var url = '/stock/getBrands';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getModel(param) {
            var url = '/stock/getModel';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function changeBrand(brandId) {
            var url = '/stock/getModelById';
            //url = getParamUrl(url, param);
            url = url + '?id=' + brandId;
            return $http.get(config.APIurl + url, brandId)
                .then(getDataComplete);
        }


        function getDataComplete(response, status) {
            return response;
        }

        /*        function getSearchData(param) {

                    var url = '/keywordSearch';
                    if (param) {
                        url = url + "?search=" + param.searchKey;
                        if (param.disease) {
                            url = url + "&disease=" + param.disease;
                        }
                        if (param.filter) {
                            url = url + '&filter=' + param.filter;
                        }
                    }
                    return $http.get(config.APIurl + url, param)
                        .then(getDataComplete);
                }
        */
    }

})();
