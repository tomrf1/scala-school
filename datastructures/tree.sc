sealed trait BinaryTree
case class Node(data: Int, left: BinaryTree, right: BinaryTree) extends BinaryTree
case object Empty extends BinaryTree

val tree = Node(
  5,
  left = Node(2, Empty, Empty),
  right = Node(7, Empty, Empty)
)

def size(tree: BinaryTree): Int = ???

size(tree)

def sum(tree: BinaryTree): Int = ???

sum(tree)

def map(tree: BinaryTree, f: Int => Int): BinaryTree = ???

map(tree, n => n*2) // double each value
