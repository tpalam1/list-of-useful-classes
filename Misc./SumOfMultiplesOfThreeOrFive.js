<!DOCTYPE html>
<html>
<body>

<p id = "demo"> </p>

<script>
/*
Name: Project Euler, Q1. 
Scope: I used this project to help familiarize myself with JavaScript.

Description:
  If we list all the natural numbers below 10 that are multiples of 3 or 5, 
  we get 3, 5, 6 and 9. 
  The sum of these multiples is 23.
  
  Find the sum of all the multiples of 3 or 5 below 1000.
*/

sum = getSumOf(filterListBy(getListOfNumbers(0, 1000),3,5));

document.getElementById("demo").innerHTML = 
  "The first question from Project Euler is find sum of all numbers who are multiples of either 3 or 5 from n = [0, 1000]." 
  + "\nThe answer is " + sum + ".";

function getListOfNumbers(min, max)
{
  if(min >= max)
  {
    throw "getListOfNumbers(...): minimum value greater than or equal to maximum.";
  }
  else 
  {
    const list = new Array(min);
    
    for(let i = min; i <= max; i++)
    {
      list.push(i)
    }
  
    return list;
  }
} // returns a list of numbers, [min, max]; throws error if min >= max.

function filterListBy(list, mod1, mod2)
{
  const output = new Array();
  
  for(var i = 0; i < list.length; i++)
  {
    var curr_value = list[i];
    
    if(curr_value % mod1 == 0 || curr_value % mod2 == 0)
    {
      output.push(curr_value);
    }
  }
  
  return output;
} // returns a list of numbers, such that they are divisible by either mod1 or mod2.

function getSumOf(list)
{
  let cumul_sum = 0;
  
  for(var i = 0; i < list.length; i++)
  {
    curr_value = list[i];
    
    cumul_sum += curr_value;
  }
  
  return cumul_sum;
}

</script>

</body>
</html>
