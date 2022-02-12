import "mock/problemCreate";
var problemCreate = require("./problemCreate");
module.exports = function() {
  return {
    problemCreate: problemCreate
  };
};
