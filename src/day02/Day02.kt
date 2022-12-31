package day02

import println
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.Map

fun main() {
    val figuresMap = Map.of("X", "A", "Y", "B", "Z", "C")

    val loseMap = Map.of("A", "C", "B", "A", "C", "B")
    val winsMap = Map.of("A", "B", "B", "C", "C", "A")

    data class Strategy(val opponentsMove: String, val secondColumn: String) {
    }


    @Throws(IOException::class)
    fun readStrategy(fileName: String): List<Strategy> {
        BufferedReader(FileReader(fileName)).use { br ->
            var line: String?
            val strategies: MutableList<Strategy> = ArrayList()
            while (br.readLine().also { line = it } != null) {
                val strategy = line!!.split(" ").toTypedArray()
                strategies.add(Strategy(strategy[0], strategy[1]))
            }
            return strategies
        }
    }

    fun evaluateFigure(figure: String): Int {
        return when (figure) {
            "A" -> 1;
            "B" -> 2;
            "C" -> 3;
            else -> 0;
        };
    }

    fun calculateTotalStrategyScoreForResults(moves: List<Strategy>): Int {
        var result = 0;
        for( move in moves) {
            when(move.secondColumn) {
                "X" -> { result += evaluateFigure(loseMap.get(move.opponentsMove)!!); }
                "Y" -> {
                    result += 3;
                    result += evaluateFigure(move.opponentsMove);
                }
                "Z" -> {
                    result += 6;
                    result += evaluateFigure(winsMap.get(move.opponentsMove)!!);
                }
            }
        }
        return result;
    }

    fun calculateTotalStrategyScoreForMoves(moves: List<Strategy>): Int {
        var result = 0
        for (strategy in moves) {
            val myMoveMapped: String = figuresMap.get(strategy.secondColumn)!!
            result += evaluateFigure(myMoveMapped)
            if (myMoveMapped == strategy.opponentsMove) {
                result += 3
            } else if (winsMap[strategy.opponentsMove] == myMoveMapped) {
                result += 6
            }
        }
        return result
    }

    fun part1(input: List<Strategy>): Int {
        return calculateTotalStrategyScoreForMoves(input)
    }

    fun part2(input: List<Strategy>): Int {
        return calculateTotalStrategyScoreForResults(input)
    }

    val testInput = readStrategy("src/day02/example_input")
    check(part1(testInput) == 15)

    val input = readStrategy("src/day02/input")
    part1(input).println()
    part2(input).println()
}
