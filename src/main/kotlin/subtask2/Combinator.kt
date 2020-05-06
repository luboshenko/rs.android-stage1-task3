package subtask2

class Combinator {
    fun checkChooseFromArray(array: Array<Int>): Int? {
        val posters = array[0]
        val colors = array[1]
        return getMinColorsNumber(posters, colors)
    }

    private fun factorial(num: Int) : Long {
        var prod: Long = 1
        for(i in 1..num) {
            prod *= i
        }
        return prod
    }

    private fun getMinColorsNumber(posters: Int, colors: Int): Int? {
        for (n in 1..colors) {
            var combinations = factorial(colors) / (factorial(n) * factorial(colors - n))
            if(combinations >= posters) {
                return n
            }
        }
        return null
    }
}
