
function dupli() {
    let arry = [1,2,3,4,5,1,2];

    for (let i = 0; i < arry.length; i++)
     {
        for (let j = i; j < arry.length; j++) {
            if (i !== j) {
                if (arry[i] === arry[j]) {
                    console.log(arry[i]);
                    break;
                }
            }
        }
       
    }
}

dupli();


