(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('loginCtr', loginCtr);

    loginCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function loginCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;

    }
})();
