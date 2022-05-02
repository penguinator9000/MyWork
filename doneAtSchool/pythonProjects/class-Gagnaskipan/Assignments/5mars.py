class BTN():
    '''Binary Tree Node'''
    def __init__(self, val , priority = None, parent = None, left = None, right = None):
        self.val = val
        if priority:
            self.priority = priority
        else:
            self.priority = val
        self.parent = parent
        self.left = left
        self.right = right
    def __str__(self):
        return str(self.val)+' '+str(self.priority)
    def set_parent(self, parent):
        self.parent = parent

    def set_left(self, left):
        self.left = left

    def set_right(self, right):
        self.right = right
    def get_children(self):
        return self.left, self.right


class PriorityHeap():
    def __init__(self, root):
        self.root = root
        self.lastnode = root

    def __str__(self):
        ret = str(self.root)+'\n'
        c = self.getlvlcount()
        for i in range(1, c+1):
            stuff = self.getlvl(i)
            for each in stuff:
                ret = ret + str(each)+ ' -- '
            ret = ret[:-4]+'\n'
        return ret

    def getlvlcount(self, lvl = None):
        if lvl == None:
            if self.root:
                return self.getlvlcount(1)
            return None
        node = self.root
        for i in range(lvl):
            node = node.left
        if node:
            return 1 + self.getlvlcount(lvl+1)
        return 0

    def getlvl(self, lvl):
        ls = []
        ls.append(self.root) 
        for i in range(lvl):
            ls2 = []
            for node in ls:
                if node:
                    left, right = node.get_children()
                    ls2.append(left)
                    ls2.append(right)
                else:
                    ls2.append(None)
                    ls2.append(None)
            ls = ls2
        return ls
    def add(self,node):
        if self.root == None:
            self.root = node
            self.lastnode = self.root


    def remove(self):
        ret = root.val
        root.val = None
       # if root.left.priority <= root.right:


    def compare_parent(self, node):
        if node.priority < node.parent.priority:
            self.switch_node(node, node.parent)
            if node.parent:
                self.compare_parent(node)

    def switch_node(self, node, parent):
        grandpa = parent.parent
        parent.parent = node
        node.parent = grandpa
        if parent.left is node:
            parent.left = node.left
            node.left = parent
        else:
            parent.right = node.right
            node.rigth = parent        



rootboi = BTN(1)
ls = [2,3,4,5,6,7,8,9]
for i in range(len(ls)):
    ls[i] = BTN(ls[i])

rootboi.set_left(ls.pop(0))
#rootboi.set_right(ls.pop(0))
rootboi.left.set_left(ls.pop(0))
rootboi.left.set_right(ls.pop(0))
#rootboi.right.set_left(ls.pop(0))
#rootboi.right.set_right(ls.pop(0))

boi = PriorityHeap(rootboi)

print(boi)