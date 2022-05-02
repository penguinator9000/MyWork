def len_of_string(string):
    #O(n) 
    if string:
        return 1+ len_of_string(str[:-1])    
    else:
        return 0

def linear_search(target,ls):
    #O(n)
    if ls:
        if ls[0] == target:
            return True
        return linear_search(target,ls[1:])
    return False

def count_instances(target, ls):
    #O(n)
    if ls:
        if target == ls[0]:
            return 1+ count_instances(target, ls[1:])
        return 0 + count_instances(target, ls[1:])
    return 0

def has_dupl(ls, sort = True):
    #O(n)
    if sort:
        sort(ls)
    if ls[0] == ls[1]:
        return True
    return has_dupl(ls[1:], False)

def rm_dupl(ls,new_ls = []):
    if ls:
        if ls[0] not in new_ls:
            new_ls.append(ls[0])
        return rm_dupl(ls[1:], new_ls)
    return new_ls

def bin_search(target, ls, spot = 'start'):

    if spot == len(ls)-1 or spot == len(ls):
        return False
    if spot  == 'start':
        spot = len(ls)//2
    first = ls[spot]
    second = ls[spot+1]
    if first == target:
        return spot
    elif second == target:
        return spot+1
    if first > target:
        spot = spot//2 
    else:
        spot = int(spot/2 + len(ls)/2)
    return bin_search(target,ls,spot)

def bin_search_it(target,ls):
    mid = len(ls)//2
    ls1 = ls[:mid]
    ls2 = ls[mid:]
    if ls1 and ls2:
        if target >= ls1[-1] and target <= ls2[0]:
            return True
        elif target < ls1[-1]:
            return bin_search_it(target,ls1)
        return bin_search_it(target,ls2)
    return False

def substring(substr, a_str):
    #gangskip true in gangaskipan????
    #anyways time complx = O(a_str)
    if not a_str:
        return False
    elif substr:
        if substr[0] == a_str[0]:
            return substring(substr[1:],a_str[1:])
        return substring(substr,a_str[1:])
    return True

def perf_substring(substr, a_str):
    if len(substr) < len(a_str):
        if substr == a_str[:len(substr)]:
            return True
        return perf_substring(substr, a_str[1:])
    return False
    
    

def x_ish(a_str, x):
    #0(nk)
    if x:
        if linear_search(x[0],a_str):
            return x_ish(a_str, x[1:])
        return False
    return True
    
def palindrome(a_str):
    #O(n/2)
    if a_str:
            if a_str[0] == a_str[-1]:
                return palindrome(a_str[1:-1])
            return False
    return True
