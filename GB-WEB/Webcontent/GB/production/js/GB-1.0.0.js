'use strict';
// Source: dest/app.mdl.js
(function() {
angular.module('app', [
        /*
         * Order is not important. Angular makes a
         * pass to register all of the modules listed
         * and then when app.dashboard tries to use app.data,
         * its components are available.
         */

        /*
         * Everybody has access to these.
         * We could place these under every feature area,
         * but this is easier to maintain.
         */
        'app.core',
        /*
         * Feature areas
         */
        'app.layout',
        'app.widgets'
    ]);

})();

// Source: dest/app.route.js
/*
this route provider for gust and member users
*/

(function() {
angular.module('app')

    .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: 'GB/app/layout/screens/home/home.tmpl.html',
                controller: 'homeCtr',
                controllerAs: 'vm',
            })
            .when('/login', {
                templateUrl: 'GB/app/layout/screens/login/login.tmpl.html',
                controller: 'loginCtr',
                controllerAs: 'vm',
            })
            .otherwise({
                redirectTo: '/home'
            });

    }]).config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.headers.common = {
            "Accept": "application/json;charset=utf-8",
            'Content-Type': 'application/json; charset=utf-8',
            /*  'X-Frame-Options': 'ALLOW-FROM SAMEORIGIN'*/
        };
    }]);


})();

// Source: dest/common/widgets.mdl.js
(function() {
angular.module('app.widgets', []);
})();

// Source: dest/core/core.mdl.js
(function() {
angular.module('app.core', [
        /*
         * Angular modules
         */
        'ngAnimate', 'ngRoute', 'ngSanitize', 'ui.bootstrap', 'ngCookies', 'ngTouch', 'ngMaterial', 'smart-table'
        /*
         * Our reusable cross app code modules
         */
        //'blocks.router',
        /*
         * 3rd Party modules
         */
    ]);
})();

// Source: dest/layout/layout.mdl.js
(function() {
angular.module('app.layout', []);
})();

// Source: dest/common/directive/modalBox.drv.js
(function() { //code for open modal window
angular
        .module('app.widgets')
        .directive('modelBox', modelBox);

    /* @ngInject */
    modelBox.$inject = ['$q', '$uibModal', '$location'];

    function modelBox($q, $uibModal, $location) {

        var directive = {
            link: link,
            restrict: 'A',
            scope: {
                "modaldata": "=",
                "options": "="
            }
        };
        return directive;

        function link(scope, element, attrs) {

            var delay = 500,
                clicks = 0,
                timer = null;

            element.on('click', function(event) {
                clicks++; //count clicks
                if (clicks === 1) {
                    timer = setTimeout(function() {
                        scope.$apply(function() {
                            openmodel(event);
                        });
                        clicks = 0; //after action performed, reset counter
                    }, delay);
                } else {
                    clearTimeout(timer); //prevent single-click action
                    clicks = 0; //after action performed, reset counter
                }
            });

            function openmodel(e) {


                var options = scope.options;

                var modalInstance = $uibModal.open({
                    animation: options.animation,
                    templateUrl: options.templateUrl,
                    controller: ['$scope', '$document', '$rootScope', '$window', '$uibModalInstance', 'config', '$timeout', function($scope, $document, $rootScope, $window, $uibModalInstance, config, $timeout) {
                        $scope.isShow = true;
                        $scope.modalData = scope.modaldata;
                        $scope.modalopt = options;
                        $scope.image = $rootScope.image;
                        $scope.typeImage = false;
                        $scope.typeVideo = false;
                        if ($rootScope.type === 'image') {
                            $scope.typeImage = true;
                        }
                        var cleanUp = function() {
                            window.angular.element($window).off('keydown click');
                        };
                        $scope.cancelModal = function() {
                            //dismiss modal
                            $uibModalInstance.dismiss('cancel');

                        };

                        $scope.$on("cancelModal", function(evt, data) {
                            $uibModalInstance.dismiss('cancel');
                        });

                        $scope.$on('$locationChangeStart', function(event, next, current) {
                            $uibModalInstance.dismiss('cancel');
                        });


                    }],
                    size: options.size,
                    resolve: {

                    }
                });

            }

        }
    }

})();

// Source: dest/common/factory/authentication.fact.js
(function() {
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
            getSupplierId: getSupplierId
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
            var url = '/getOrders';
            url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function createOrder(data) {
            var url = '/setOrders';

            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function createSupplier(data) {
            var url = '/createSupplier';

            return $http.post(config.APIurl + url, data)
                .then(getDataComplete);
        }

        function getSupplier(param) {
            var url = '/getSupplier';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
                .then(getDataComplete);
        }

        function getSupplierId(param) {
            var url = '/getSupplierId';
            // url = getParamUrl(url, param);
            return $http.get(config.APIurl + url, param)
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

// Source: dest/common/factory/getFeeds.fact.js
(function() {
angular
        .module('app.widgets')
        .factory('Feeds', Feeds);

    Feeds.$inject = ['$http', '$q', 'config'];

    function Feeds($http, $q, config) {
        var isPrimed = false;
        var primePromise;

        var service = {
            getNews: getNews,
            getBlogs: getBlogs,
            getParamUrl: getParamUrl

        };
        return service;

        function getNews(params) {

            var url = 'http://ajax.googleapis.com/ajax/services/feed/load';

            params.url = config.newsUrl;
            url = getParamUrl(url, params);
            return $http.jsonp(url);
        }

        function getBlogs(params) {

            var url = 'http://ajax.googleapis.com/ajax/services/feed/load';
            params.url = config.blogsUrl;
            url = getParamUrl(url, params);
            return $http.jsonp(url);
        }

        function getParamUrl(url, param) {
            if (param) {

                url = url + "?v=1.0";

                if (param.num) {
                    url = url + "&num=" + param.num;
                }
                url = url + "&callback=JSON_CALLBACK";

                url = url + "&q=" + encodeURIComponent(param.url);
            }
            return url;
        }

    }

})();

// Source: dest/common/factory/remember.fact.js
(function() {
angular
        .module('app.widgets')
        .factory('rememberFact', rememberFact);

    rememberFact.$inject = ['$filter'];

    function rememberFact($filter) {


        function fetchValue(name) {
            var gCookieVal = document.cookie.split("; ");
            for (var i = 0; i < gCookieVal.length; i++) {
                // a name/value pair (a crumb) is separated by an equal sign
                var gCrumb = gCookieVal[i].split("~=");
                if (name === gCrumb[0]) {
                    var value = '';
                    try {
                        value = angular.fromJson(gCrumb[1]);
                    } catch (e) {
                        value = unescape(gCrumb[1]);
                    }
                    return value;
                }
            }
            // a cookie with the requested name does not exist
            return null;
        }
        return function(name, values) {
            if (arguments.length === 1) {
                return fetchValue(name);
            }
            var cookie = name + '~=';
            if (typeof values === 'object') {
                var expires = '';
                cookie += (typeof values.value === 'object') ? angular.toJson(values.value) + ';' : values.value + ';';
                if (values.expires) {
                    var date = new Date();
                    date.setTime(date.getTime() + (values.expires * 24 * 60 * 60 * 1000));
                    expires = date.toGMTString();
                }
                cookie += (!values.session) ? 'expires=' + expires + ';' : '';
                cookie += (values.path) ? 'path=' + values.path + ';' : '';
                cookie += (values.secure) ? 'secure;' : '';
            } else {
                cookie += values + ';';
            }
            document.cookie = cookie;
        };


    }

})();

// Source: dest/core/core.cnst.js
/* global toastr:false, moment:false */
(function() {
angular
        .module('app.core')
        .constant('toastr', toastr)
        .constant('moment', moment);
})();

// Source: dest/core/core.conf.js
(function() {
var core = angular.module('app.core');

    core.config(toastrConfig);

    /* @ngInject */
    toastrConfig.$inject = ['toastr'];

    function toastrConfig(toastr) {
        toastr.options.timeOut = 4000;
        toastr.options.positionClass = 'toast-bottom-right';
        toastr.options.tapToDismiss = true;
    }

    var config = {
        appErrorPrefix: '[APP Error] ', //Configure the exceptionHandler decorator
        appTitle: 'GB',
        version: '1.0.0',
        //development
        APIurl: 'http://localhost:9090'

    };

    core.value('config', config);

    core.run(run);

    /* Inject Run
       App bootstrap settings excute in run time
    */
    run.$inject = ['$rootScope', '$location', 'config', '$cookies', '$window'];

    function run($rootScope, $location, config, $cookies, $window) {

        /*$rootScope.$on("$routeChangeStart", function(event, next, current) {

            $rootScope.isLogin = true;
            var getcookie = $cookies.get('isLogin');

            if (getcookie === 'false' || getcookie === undefined) {
                $rootScope.isLogin = false;
            } else {
                $rootScope.isLogin = true;

                $rootScope.userDetail = $cookies.getObject('userDetail');
            }

        });*/
    }



})();

// Source: dest/layout/layout.ctr.js
(function() {
angular
        .module('app.layout')
        .controller('LayoutCtr', LayoutCtr);

    LayoutCtr.$inject = ['$timeout', '$filter', '$q', 'config'];

    function LayoutCtr($timeout, $filter, $q, config) {
        /*jshint validthis: true */
        var vm = this;

        activate();

        function activate() {
            //logger.success(config.appTitle + ' Shell loaded!', null);
        }

    }
})();

// Source: dest/layout/screens/home/home.ctr.js
(function() {
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

// Source: dest/layout/screens/login/login.ctr.js
(function() {
angular
        .module('app.layout')
        .controller('loginCtr', loginCtr);

    loginCtr.$inject = ['$timeout', '$filter', '$q', 'config', '$rootScope', '$cookies', '$scope', '$location', 'authfactory'];

    function loginCtr($timeout, $filter, $q, config, $rootScope, $cookies, $scope, $location, authfactory) {
        var vm = this;

    }
})();

// Source: dest/layout/screens/modal/addModal.ctrl.js
(function() {
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

// Source: dest/layout/shell.ctrl.js
(function() {
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
