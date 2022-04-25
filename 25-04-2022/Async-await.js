function incrementEmployeeSalary(baseSalary,increment){
    const newSalary = baseSalary+increment;
    console.log(`New Salary : ${newSalary}`);
    return newSalary;
}

function slowIncrement(n1,n2){
    return new Promise(resolve =>{
        setTimeout(() => resolve(n1+n2),3000);
    });
}

async function incrementEmployeeSalaryWithAsync(baseSalary,increment){
    const newSalary = await slowIncrement(baseSalary,increment);
    console.log(`New Salary : ${newSalary}`);
    return newSalary;
}

incrementEmployeeSalaryWithAsync(60000,6000);
