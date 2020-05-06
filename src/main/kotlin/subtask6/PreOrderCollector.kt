package subtask6

class PreOrderCollector: Visitor {
    val list: MutableList<Node> = mutableListOf()

    override fun visit(el: Node?) {
        if(el != null) {
            list.add(el)
            visit(el.leftChild)
            visit(el.rightChild)
        }
    }
}