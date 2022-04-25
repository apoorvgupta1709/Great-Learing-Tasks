var arr= [ 1,2,3,4,5,6,7,8,9];

function sum(){
    let s=0;

    for (let i = 0; i < arr.length; i++)
    {
        s+=arr[i];
    }
    console.log(s);
    console.log(s/arr.length);
}
sum();