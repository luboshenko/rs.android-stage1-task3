package subtask1

import java.lang.StringBuilder
import kotlin.math.abs

class PolynomialConverter {

    fun convertToStringFrom(numbers: Array<Int>): String? = if (numbers.isEmpty()) {
        null
    } else {
        buildHumanReadableRepresentation(numbers)
    }

    private fun buildHumanReadableRepresentation(numbers: Array<Int>): String {
        val builder = StringBuilder()
        val maxDegree = numbers.size - 1
        for ((index, number) in numbers.withIndex()) {
            if(number == 0) {
                continue
            }
            builder
                .append(getSign(number, builder.isEmpty()))
                .append(getCoefficientRepresentation(number))
                .append(getIndeterminateRepresentation(maxDegree - index))
        }
        return builder.toString()
    }

    private fun getSign(number: Int, isFirstMember: Boolean) = if(isFirstMember) {
        if(number > 0) {
            ""
        } else {
            "- "
        }
    } else {
        if(number > 0) {
            " + "
        } else {
            " - "
        }
    }

    private fun getCoefficientRepresentation(number: Int) = when(number) {
        -1, 1 -> ""
        else -> abs(number)
    }

    private fun getIndeterminateRepresentation(degree: Int) = when(degree) {
        0 -> ""
        1 -> "x"
        else -> "x^$degree"
    }
}
