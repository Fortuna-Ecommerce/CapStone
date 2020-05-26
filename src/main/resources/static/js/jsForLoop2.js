"use strict";

/*
will create a function that prints multiplication table of a number based on the input which should be within 1 - 10.

 */
console.log('Helllo');
function showMultiplicationTable(input){
    for(var i = 1; i <= 10; i++){
        var equals = input * i;

        var all1 = input + " X " + i + " = " + equals;

        console.log(all1);
    }
}
/*
    min = Math.ceil(min);

console.log(showMultiplicationTable(7));
function randomNumber(min, max) {
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min; //The maximum is inclusive and the minimum is inclusive
}

for(var i = 0; i < 10; i++){


    var randomNumber = Math.floor(Math.random() * 180) + 20;

    if(randomNumber % 2 === 0){
        console.log(randomNumber + ' is even');
    } else {
        console.log(randomNumber + ' is odd');
    }

}

for( var v = 0; v < 10; v++ ){
    var randomNumber = Math.floor(Math.random() * 180) +20;
    if(randomNumber % 2 === 0){
        console.log(randomNumber + ' is even');
    }else{
        console.log(randomNumber + " is odd");
    }
}


for ( var i = 0; i < 10; i++){
    console.log(i.toString().repeat(i));
}

for ( var i = 100; i > 0; i-=5){
    console.log(i);
}





*/
