fun main() {
	fun part1(input: List<String>): Int {
		fun checkColor(g: String, limit: Int, color: String): Boolean {
			val colorRegex = ("(\\d+)\\s" + color).toRegex()
			var match = colorRegex.find(g) ?: return true
			while (true) {
				val count = match.value.substring(0, match.value.indexOf(" ")).toInt()
				if (count > limit) return false
				match = match.next() ?: return true
			}
		}

		fun isValidGame(g: String): Boolean {
			return checkColor(g, 12, "red") &&
				checkColor(g, 13, "green") &&
				checkColor(g, 14, "blue")
		}

		fun getGameNumber(g: String): Int {
			return g.substring(5, g.indexOf(":")).toInt()
		}

		var gameCounter = 0
		input.forEach {
			if (isValidGame(it)) {
				gameCounter += getGameNumber(it)
			}
		}
		return gameCounter
	}

	fun part2(input: List<String>): Int {
		fun maxColor(g: String, color: String): Int {
			val colorRegex = ("(\\d+)\\s" + color).toRegex()
			var match = colorRegex.find(g) ?: return 0
			var max = 0
			while (true) {
				val count = match.value.substring(0, match.value.indexOf(" ")).toInt()
				if (count > max) {
					max = count
				}
				match = match.next() ?: return max
			}
		}

		var sumOfPower = 0
		input.forEach {
			sumOfPower += maxColor(it, "red") *
				maxColor(it, "blue") *
				maxColor(it, "green")
		}

		return sumOfPower
	}

	// test if implementation meets criteria from the description, like:
	val testInput = readInput("day2p1_test")
	check(part1(testInput) == 8)

	val testInput2 = readInput("day2p2_test")
	check(part2(testInput) == 2286)
	val input = readInput("day2p1")
	part1(input).println()
	part2(input).println()
}
