package subtask6

class FindLastWithChildrenVisitor: Visitor {
    var last: Node? = null
        private set

    override fun visit(el: Node?) {
        if(el != null) {
            if(el.hasChildren) {
                last = el
            }
            visit(el.leftChild)
            visit(el.rightChild)
        }
    }
}