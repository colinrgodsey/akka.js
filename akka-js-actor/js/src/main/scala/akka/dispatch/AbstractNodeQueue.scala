package akka.dispatch

import scala.collection.mutable.Queue

class AbstractNodeQueue[A >: Null] extends Queue[A] {
  private[dispatch] def add(value: A) = super.enqueue(value)

  private[dispatch] def addFirst(value: A): Unit = {
    val tailPart = dequeueAll(_ => true)

    add(value)
    tailPart foreach add
  }

  private[dispatch] def poll(): A = if(isEmpty) null else super.dequeue()

  private[dispatch] def count() = size
}
