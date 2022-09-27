sealed trait BinaryTree
case class Node(value: Int, left: BinaryTree, right: BinaryTree) extends BinaryTree
case class Leaf(value: Int) extends BinaryTree
case object Empty extends BinaryTree

val t = Node(
  4,
  Node(
    2,
    left = Leaf(1),
    right = Empty
  ),
  Node(
    5,
    Empty,
    Empty
  )
)

def size(tree: BinaryTree): Int = tree match {
  case Empty => 0
  case Leaf(_) => 1
  case Node(_, l, r) => 1 + size(l) + size(r)
}

size(t)

def sum(tree: BinaryTree): Int = ???

def map(f: Int => Int, tree: BinaryTree): BinaryTree = ???
