val forwardRegex = "(\\d|one|two|three|four|five|six|seven|eight|nine)".toRegex()
val backwardsRegex = "(\\d|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin)".toRegex()
val forwardMap = mapOf(
	"one" to "1",
	"two" to "2",
	"three" to "3",
	"four" to "4",
	"five" to "5",
	"six" to "6",
	"seven" to "7",
	"eight" to "8",
	"nine" to "9"
)
val backwardMap = mapOf(
	"one".reversed() to "1",
	"two".reversed() to "2",
	"three".reversed() to "3",
	"four".reversed() to "4",
	"five".reversed() to "5",
	"six".reversed() to "6",
	"seven".reversed() to "7",
	"eight".reversed() to "8",
	"nine".reversed() to "9"
)

val intRegex = "\\d".toRegex()
fun firstDigitChar(s: String, forwards: Boolean = true): String {1
	val match = if (forwards) forwardRegex.find(s)!! else backwardsRegex.find(s)!!
	return match.value
}

fun main() {
	fun part1(input: List<String>): Int {
		var counter = 0
		input.forEach {
			var d1 = firstDigitChar(it)
			var d2 = firstDigitChar(it.reversed(), false)
			if (!intRegex.matches(d1)) {
				d1 = forwardMap.get(d1)!!
			}
			if (!intRegex.matches(d2)) {
				d2 = backwardMap.get(d2)!!
			}
			counter += (d1 + d2).toInt()
		}
		return counter
	}

	fun part2(input: List<String>): Int {
		return input.size
	}


	// test if implementation meets criteria from the description, like:
	val testInput = readInput("day1p2_test")
	check(part1(testInput) == 142)

	val input = readInput("day1p1")
	part1(input).println()
	//part2(input).println()
}
