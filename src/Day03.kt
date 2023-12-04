
fun main() {
	val numberRegex = "(\\d+)".toRegex()

	fun part1(input: List<String>): Int {
		fun checkForSymbol(s: String, start: Int, end: Int): Boolean {
			val candidateStr = s.substring(start..end)
			candidateStr.forEach {
				if ((it != '.') && !(it.isDigit())) {
					return true
				}
			}
			return false
		}

		var currentRow = 0
		var result = 0
		input.forEach {
			var match = numberRegex.find(input[currentRow])
			while (match != null) {
				val digit = match.value.toInt()
				val range = match.range

				val start = if (range.first == 0) 0 else range.first - 1
				val end = if(range.last == input[currentRow].length - 1) range.last else range.last + 1

				var include = false
				// check prev row
				if (currentRow != 0) {
					include = checkForSymbol(input[currentRow-1], start, end)
				}
				// check current row
				include = include || checkForSymbol(input[currentRow], start, start)
				include = include || checkForSymbol(input[currentRow], end, end)
				// check next row
				if (currentRow != input.size - 1) {
					include = include || checkForSymbol(input[currentRow+1], start, end)
				}

				if (include) {
					result += digit
				}
				match = match.next()
			}
			currentRow++
		}

		return result
	}

	fun part2(input: List<String>): Int {
		fun getDigitsInRange(s: String, range: IntRange): List<Int> {
			val resultList = mutableListOf<Int>()
			var m = numberRegex.find(s)
			while (m != null) {
				val d = m.value.toInt()
				val r = m.range
				if (range.intersect(r).isNotEmpty()) {
					resultList.add(d)
				}
				m = m.next()
			}
			return resultList
		}

		// Find * that are adjacent to exactly two numbers
		// Approach:
		// Find *, for each one determine if there is a digit surrounding it
		// If yes, find the value of those digits and multiple them together
		var currentRow = 0
		var result = 0
		input.forEach {
			var nextIndex = it.indexOf("*")
			while (nextIndex >= 0) {
				val start = if (nextIndex == 0) 0 else nextIndex - 1
				val end = if (nextIndex == it.length - 1) nextIndex else nextIndex + 1
				// candidate *
				val numberList = mutableListOf<Int>()
				if (currentRow != 0) {
					// check above start .. end
					numberList.addAll(getDigitsInRange(input[currentRow - 1], start .. end))
				}
				numberList.addAll(getDigitsInRange(input[currentRow], start .. start))
				numberList.addAll(getDigitsInRange(input[currentRow], end .. end))
				if (currentRow != input.size - 1) {
					numberList.addAll(getDigitsInRange(input[currentRow + 1], start .. end))
				}
				if (numberList.size == 2) {
					result += numberList[0] * numberList[1]
				}
				nextIndex = it.indexOf("*", nextIndex + 1)
			}
			currentRow++
		}
		return result
	}

	// test if implementation meets criteria from the description, like:
	val testInput = readInput("day3p1_test")
	check(part1(testInput) == 4361 + 55 + 27)
	check(part2(testInput) == 467835 + (55 * 27))

	val input = readInput("day3")
	part1(input).println()
	part2(input).println()
}
