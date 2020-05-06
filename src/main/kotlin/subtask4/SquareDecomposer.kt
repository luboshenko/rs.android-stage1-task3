package subtask4

import java.util.*
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.sqrt

class SquareDecomposer {
    fun decomposeNumber(number: Int): Array<Int>? {
        if(number < 0) {
            return null
        }
        val result = ArrayDeque<Int>()
        result.addLast(number - 1)
        tryToDecompose(number * number - (number - 1) * (number - 1), result)

        while (!isValid(number, result)) {
            filterResult(result)
            if(result.isEmpty()) {
                break
            }
            val last = result.removeLast()
            result.addLast(last - 1)
            val sumOfSquares = result.fold(0){ sum, el -> sum + el * el }
            tryToDecompose(number * number - sumOfSquares, result)
        }
        return if(result.isEmpty()) {
            null
        } else {
            result.sorted().toTypedArray()
        }
    }

    private fun filterResult(result: ArrayDeque<Int>): Unit {
        val lastIndex = result.size - 1
        for(i in lastIndex downTo 0) {
            val cur = result.elementAt(i)
            if(cur == 1 || cur == 0) {
                result.removeLast()
                continue
            }
            if(i > 0 && cur >= result.elementAt(i-1)) {
                result.removeLast()
            }
        }
    }

    private fun isValid(number: Int, result: ArrayDeque<Int>): Boolean {
        var prev = result.elementAt(0)
        var checkValue = prev * prev
        for(i in 1 until result.size) {
            val cur = result.elementAt(i)
            if(cur >= prev) {
                return false
            }
            checkValue += cur * cur
            prev = cur
        }
        return number * number == checkValue
    }

    private fun tryToDecompose(rest: Int, numbers: ArrayDeque<Int>): Unit {
        if(rest == 0) {
            return
        }
        if(rest == 1) {
            numbers.addLast(1)
            return
        }
        var next = min(sqrt(rest.toDouble()).roundToInt(), numbers.last-1)
        if(next == 0) {
            return
        }
        if(next * next > rest) {
            next--
        }
        numbers.addLast(next)
        if(next > 0) {
            tryToDecompose(rest - next * next, numbers)
        }
        return
    }
}
