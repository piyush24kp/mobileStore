(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('ShellCtr', ShellCtr);

    ShellCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location'];

    function ShellCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location) {
        /*jshint validthis: true */
        var vm = this;

        activate();

        function activate() {

        }

    }
})();
