package subtask6

import java.lang.StringBuilder

class FullBinaryTrees {
    fun stringForNodeCount(count: Int): String {
        val trees = mutableListOf<String>()
        if(count % 2 != 0) {
            val tree = Node()
            var numberOfNodes = 1
            var lastRightNode: Node = tree
            while(numberOfNodes < count) {
                val leftNode = Node()
                val rightNode = Node()
                lastRightNode.addChildren(leftNode, rightNode)
                lastRightNode = rightNode
                numberOfNodes += 2
            }
            trees.add(stringRepresentation(tree.leftToRightArray))
            while (mutateTree(tree)) {
                trees.add(stringRepresentation(tree.leftToRightArray))
            }
        }

        return toString(trees)
    }

    private fun mutateTree(tree: Node): Boolean {
        val finder = FindLastWithChildrenVisitor()
        finder.visit(tree)
        val lastWithChildren: Node? = finder.last
        if(lastWithChildren != null) {
            val collector = PreOrderCollector()
            collector.visit(tree)
            val lastIndex = collector.list.indexOf(lastWithChildren)
            lastWithChildren.removeChildren()
            for(i in lastIndex-1 downTo 0) {
                val node = collector.list[i]
                if(!node.hasChildren) {
                    node.addChildren(Node(), Node())
                    return true
                }
            }
            return false
        }
        return false
    }

    private fun stringRepresentation(nodes: Array<Node>): String {
        val builder = StringBuilder("[")
        nodes.forEachIndexed { index, node ->
            if(index > 0) {
                builder.append(',')
            }
            builder.append(node.toString())
        }
        builder.append(']')
        return builder.toString()
    }

    private fun toString(list: List<String>): String {
        val builder = StringBuilder("[")
        list.forEachIndexed { index, s ->
            if(index > 0) {
                builder.append(",")
            }
            builder.append(s)
        }
        builder.append("]")
        return builder.toString()
    }
}
