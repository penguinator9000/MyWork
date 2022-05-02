class Node():
    def __init__(self, data):
        self.data = data
     def merge_list(self, other):
         '''merge 2 sorted nodes'''
         pass

class Linked_list():
    def __init__(self):
        pass
    def push_front(self, data):
        pass
    def pop_front(self):
        '''return popped'''
        pass
    def push_back(self, data):
        pass
    def pop_back(self):
        '''return popped'''
        pass
    def get_size(self):
        pass
    def __str__(self):
        pass



    class Node:
    def __init__(self, data = None, next = None):
        self.data = data
        self.next = next

def print_list(head):
    if head != None:
        print(head.data, end= " ")
        print_list(head.next)
    else:
        print("")


#Example programs:

# program 1:
head = Node("A", Node("B", Node("C", Node("D", None))))
print_list(head)
# output: A B C D

# program 2:
tail = Node("D", None)
head = Node("A", Node("B", Node("C", tail)))
# output: A B C D
