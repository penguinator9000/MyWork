import random
def power(x, n):
    res = x**n
    #or
    res = x 
    for i in range (1,n):
        res =  res * x 
    return res
    #complexity O(1) or O(n)

def multp(x, n):
    if x < n:
        tmp = x
        x = n
        n = x

    for i in range(1,n):
       res = res + x
    return res
    #complexity O(n)

def rando_calrisian(size):
    ls = [0] * size
    for each in ls:
        rand = random.randint(1,6)
        each = rand
    return ls
    #complexity O(n) i think

def print_list(size):
    ls = []
    for i in range(size):
        ls.append(i)

    choice = random.randint(0,size-2)
    tmp = ls[choice]
    ls[choice] = ls[choice+1]
    ls[choice+1] = tmp

    choice1 = random.randint(0,size-1)
    choice2 = random.randint(0,size-1)
    tmp = ls[choice1]
    ls[choice1] = ls[choice2]
    ls[choice2] = tmp
    # complexity at least O(n) or O(1) dependant if the list creation is calculated with it
    return ls

def ordering():
    ls = [1,2,3,4,5,6]
    size = len(ls)
    rand = random.randint(1,6)
    count= 0
    for each in ls:
        if rand < each:
            ls.insert(count, rand)
            break
    return ls
    #O(n) cuz insert

def insertion_N_ordering():
    ls = [random.randint(1,10) for i in range(20)]
    ls = []
    for each in ls:


