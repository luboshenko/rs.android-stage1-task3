package subtask3

import kotlin.math.abs

class ArrayCalculator {
    private val descComparator = Comparator { n1: Int, n2: Int -> abs(n2) - abs(n1) }

    fun maxProductOf(numberOfItems: Int, itemsFromArray: Array<Any>): Int {
        val sortedItems = itemsFromArray.filterIsInstance<Int>().sortedWith(descComparator)
        if(sortedItems.isEmpty()) {
            return 0
        }
        var prod = 1
        var lastNegative = 0
        var i = 0
        while (i < numberOfItems && i < sortedItems.size) {
            val item = sortedItems[i++]
            prod *= item
            if(item < 0) {
                lastNegative = item
            }
        }
        if(prod < 0 && i < sortedItems.size) {
            while (i < sortedItems.size) {
                val item = sortedItems[i++]
                if(item >= 0) {
                    prod = prod / lastNegative * item
                    break
                }
            }
        }
        return prod
    }
}
