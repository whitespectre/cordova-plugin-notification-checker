var exec = require('cordova/exec');

var NotificationChecker = function() {};

NotificationChecker.prototype.checkStatus = function(success, fail, options) {
  return cordova.exec(
    success, 
    fail, 
    "NotificationChecker", 
    "checkStatus",
    []);
}

window.NotificationChecker = new NotificationChecker();
