package day01

import println
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

fun main() {
    fun part1(input: MutableList<Int>): Int {
        return input.stream().max { obj: Int, anotherInteger: Int? -> obj.compareTo(anotherInteger!!) }.orElse(0)
    }

    fun part2(input: MutableList<Int>): Int {
        return input.stream()
            .sorted { f1: Int, f2: Int -> f2 - f1 }
            .limit(3)
            .reduce(0) { a: Int, b: Int -> Integer.sum(a, b) }
    }

    @Throws(IOException::class)
    fun readSums(filename: String): MutableList<Int> {
        var sums: MutableList<Int>
        BufferedReader(FileReader(filename)).use { br ->
            var line: String?
            sums = ArrayList()
            var currentSum = 0
            while (br.readLine().also { line = it } != null) {
                if (line!!.isBlank()) {
                    sums.add(currentSum)
                    currentSum = 0
                } else {
                    currentSum += line!!.toInt()
                }
            }
            sums.add(currentSum)
        }
        return sums
    }

    val testInput = readSums("src/day01/example_input")
    check(part1(testInput) == 24000)

    val input = readSums("src/day01/input")
    part1(input).println()
    part2(input).println()
}
