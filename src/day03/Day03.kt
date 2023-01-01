package day03

import println
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.stream.Collectors

fun main() {
    fun evaluatePriority(element: Char): Int {
        return if (Character.isUpperCase(element)) {
            element.code - ('A'.code - 27)
        } else element.code - ('a'.code - 1)
    }

    fun findCommonElement(lines: Array<String>): Char {
        val elementsOfFirstBackpack = lines[0].chars()
            .mapToObj { e: Int -> e.toChar() }
            .collect(Collectors.toSet())
        val commonElementsOfBackpacksOneAndTwo: MutableSet<Char> = HashSet()
        for (i in 0 until lines[1].length) {
            if (elementsOfFirstBackpack.contains(lines[1][i])) {
                commonElementsOfBackpacksOneAndTwo.add(lines[1][i])
            }
        }
        for (i in 0 until lines[2].length) {
            if (commonElementsOfBackpacksOneAndTwo.contains(lines[2][i])) {
                return lines[2][i]
            }
        }
        return ' '
    }

    fun calculateSumOfPrioritiesForBatches(inputLines: List<String>): Int {
        var sumOfElements = 0
        var i = 0
        while (i < inputLines.size) {
            val elfGroup = arrayOf(inputLines[i], inputLines[i + 1], inputLines[i + 2])
            sumOfElements += evaluatePriority(
                findCommonElement(
                    elfGroup
                )
            )
            i += 3
        }
        return sumOfElements
    }

    fun evaluateNumber(line: String, compartmentOneElements: Set<Char>): Int {
        for (i in line.length / 2 until line.length) {
            if (compartmentOneElements.contains(line[i])) {
                return evaluatePriority(line[i])
            }
        }
        return 0
    }

    fun calculateSumOfPriorities(inputLines: List<String>): Int {
        var sumOfElements = 0
        for (line in inputLines) {
            val compartmentOneElements = line.substring(0, line.length / 2).chars()
                .mapToObj { e: Int -> e.toChar() }
                .collect(Collectors.toSet())
            sumOfElements += evaluateNumber(
                line,
                compartmentOneElements
            )
        }
        return sumOfElements
    }

    @Throws(IOException::class)
    fun readInput(fileName: String): List<String> {
        val inputLines: MutableList<String> = ArrayList()
        BufferedReader(FileReader(fileName)).use { br ->
            var line: String?
            while (br.readLine().also { line = it } != null) {
                inputLines.add(line!!)
            }
        }
        return inputLines
    }

    fun part1(input: List<String>): Int {
        return calculateSumOfPriorities(input)
    }

    fun part2(input: List<String>): Int {
        return calculateSumOfPrioritiesForBatches(input)
    }

    val testInput = readInput("src/day03/example_input")
    check(part1(testInput) == 157)

    val input = readInput("src/day03/input")
    part1(input).println()
    part2(input).println()
}
