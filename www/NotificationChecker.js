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

NotificationChecker.prototype.openSettings = function(success, fail, options) {
  return cordova.exec(
    success, 
    fail, 
    "NotificationChecker", 
    "openSettings",
    []);
}

window.NotificationChecker = new NotificationChecker();
