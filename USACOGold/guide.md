# Usago Gold Guide 

## Divisibility 
1. Prime Factorization 
```python
import math
def factor(n : int){
    ret =[]
    for i in range(2, int(math.sqrt(n))+1):
        while n % i ==0:
            ret.append(i)
            n /= i
    if n>1 :
        ret.append(n)
    return n
}
```
2.gcd
```python
import math 
math.gcd(10,20)
math.lcm(12,30)

``` 
## Modular Arithmetic 