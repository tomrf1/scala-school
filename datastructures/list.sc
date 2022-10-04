sealed trait IntList
case class Element(n: Int, tail: IntList) extends IntList
case object End extends IntList

val l = Element(1, Element(4, End)) // (1, 4)

def size(list: IntList): Int = list match {
  case Element(_, tail) => size(tail) + 1
  case End => 0
}

size(l)

def sum(list: IntList): Int = ???

sum(l)
