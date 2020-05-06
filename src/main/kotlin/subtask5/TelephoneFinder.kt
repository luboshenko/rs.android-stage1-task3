package subtask5

class TelephoneFinder {
    private val digitNeighbors = mapOf<Char, Array<Char>>(
        '1' to arrayOf('2', '4'),
        '2' to arrayOf('1', '3', '5'),
        '3' to arrayOf('2', '6'),
        '4' to arrayOf('1', '5', '7'),
        '5' to arrayOf('2', '4', '6', '8'),
        '6' to arrayOf('3', '5', '9'),
        '7' to arrayOf('4', '8'),
        '8' to arrayOf('5', '7', '9', '0'),
        '9' to arrayOf('6', '8'),
        '0' to arrayOf('8')
    )

    fun findAllNumbersFromGivenNumber(number: String): Array<String>? {
        if(number.isEmpty() || number[0] == '-') {
            return null
        }
        val length = number.length
        val result = mutableListOf<String>()
        for (n in number.indices) {
            val before = number.take(n)
            val after = number.takeLast(length - (n+1) )
            val neighbors = digitNeighbors[number[n]]
            if(neighbors != null) {
                for(i in neighbors.indices) {
                    val neighbor = "$before${neighbors[i]}$after"
                    result.add(neighbor)
                }
            }
        }
        return result.toTypedArray()
    }
}
