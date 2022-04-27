var Rectangle = /** @class */ (function () {
    function Rectangle() {
    }
    Rectangle.prototype.draw = function () {
        console.log("drawing rectangle");
    };
    return Rectangle;
}());
var Circle = /** @class */ (function () {
    function Circle() {
    }
    Circle.prototype.draw = function () {
        console.log("drawing circle");
    };
    return Circle;
}());
var objec = new Circle();
console.log(objec.draw);
var objec2 = new Rectangle();
console.log(objec2.draw);
