class BinTreeNode():
    def __init__(self, value, child1 = None, child2 = None):
        self.value = value
        self.child1 = child1
        self.child2 = child2
    def set_children(self, child1 , child2):
        self.child1 = child1
        self.child2 = child2
    def __str__(self):
        return self.value
class GeneralTreeNode():
    def __init__(self, value, children = None):
        self.value = value
        self.children = children
    def set_child(self, num, child):
        pass

class BinaryTree():
    def __init__(self, root = None):
        self.root = root
    def populate_tree(self, side = None, level = 0):
        if self.root:
            indentarion = ['---|']*level
            node = input(''.join(indentarion)+side+": ")
            if node:
                level +=1
                node = BinTreeNode(node, self.populate_tree('Left', level ), self.populate_tree('Right',level))
                return node
            return None
        else:
            self.root = BinTreeNode(input('input root val:'))
            self.root.set_children(self.populate_tree('Left', 1),self.populate_tree('Right',1))
        return
    def print_inorder(self, side = 0,level = 0, node=0):
        pass

    def print_preorder(self, node = 0):
        if node:
            bam = ''
            if node.child1:
                bam += str(node.child1) + ' ' +self.print_preorder(node.child1)
            if node.child2:
                bam += str(node.child2) + ' ' +self.print_preorder(node.child2)
            return bam    
        else:
            print(str(self.root)+' '+ self.print_preorder(self.root))

    def print_pastorder(self):
        pass


class GeneralTree():
    def __init__(self):
        pass
    def populate_tree(self):
        pass

tree = BinaryTree(BinTreeNode('A',BinTreeNode('B',BinTreeNode('C'),BinTreeNode('D') ),BinTreeNode('E',BinTreeNode('F'),BinTreeNode('E',None,BinTreeNode('H')))))
#tree.populate_tree()
tree.print_preorder()