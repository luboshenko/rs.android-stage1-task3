package subtask6

import java.util.*

class Node(private val name: String = "0") {

    var leftChild: Node? = null
        private set
    var rightChild: Node? = null
        private set
    var parent: Node? = null
        private set

    val hasChildren get() = leftChild != null && rightChild != null

    fun addChildren(left: Node, right: Node) {
        leftChild = left
        leftChild!!.parent = this
        rightChild = right
        rightChild!!.parent = this
    }

    fun removeChildren() {
        if(hasChildren) {
            leftChild?.parent = null
            leftChild = null
            rightChild?.parent = null
            rightChild = null
        }
    }

    val leftToRightArray get(): Array<Node> {
        val list = mutableListOf<Node>()
        val queue = ArrayDeque<Node>()
        queue.addLast(this)
        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()
            list.add(currentNode)
            if(currentNode != NullNode) {
                if(currentNode.hasChildren) {
                    queue.add(currentNode.leftChild)
                    queue.add(currentNode.rightChild)
                } else {
                    queue.add(NullNode)
                    queue.add(NullNode)
                }
            }
        }
        return removeTrailingNulls(list).toTypedArray()
    }

    public fun accept(v: Visitor) {
        v.visit(this)
    }

    override fun toString(): String {
        return name
    }

    private fun removeTrailingNulls(nodes: MutableList<Node>): MutableList<Node> {
        for(i in nodes.size-1 downTo  0) {
            if(nodes[i] != NullNode) {
                return nodes
            }
            nodes.removeAt(i)
        }
        return nodes
    }

    companion object {
        val NullNode = Node("null")
    }
}