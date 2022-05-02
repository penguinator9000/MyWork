def counter(lis):
    '''count values in a list and returns a dict'''
    val_dic = {}
    for val in lis:
        if val in val_dic:
            val_dic[val] = val_dic[val] + 1
        else:
            val_dic[val] = 1
    return val_dic

def count_values(lis):
    val_dic = counter(lis)
    for key, item in val_dic.items():
        print('{}: {}'.format(key, item))

class ValueCounter:
    def __init__(self):
        self.ls = [] #unused but i imagine we would want to keep the original ls too
        self.count = {}

    def set_items(self, ls):
        self.ls = ls
        #when items are set count each value so we dont have to to it again any time we want to use that data
        self.count = counter(ls)


    def print_count(self):
        for key, item in self.count.items():
            print('{}: {}'.format(key, item))