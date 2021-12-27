package day03

import readInput
import kotlin.math.pow

fun main() {
    fun processInput(input: List<String>): List<List<Int>> {
        return input.map { it ->
            it.map(Character::getNumericValue)
        }
    }

    fun List<Int>.binaryListToInt(): Int {
        var result = 0
        for (i in indices) {
            result += this[i] * 2.0.pow(size - 1 - i).toInt()
        }
        return result
    }

    fun part1(input: List<List<Int>>): Int {
        if (input.isEmpty()) return 0
        val occurrences = MutableList(input[0].size) { 0 }
        input.forEach { number ->
            for (i in number.indices) {
                occurrences[i] += number[i]
            }
        }
        val gammaList = occurrences.map { if (it < input.size / 2.0) 0 else 1 }
        val epsilonList = occurrences.map { if (it < input.size / 2.0) 1 else 0 }
        val gamma = gammaList.binaryListToInt()
        val epsilon = epsilonList.binaryListToInt()
        println("gamma = $gamma, epsilon = $epsilon, gamma * epsilon = ${gamma * epsilon}")
        return gamma * epsilon
    }

    fun part2(input: List<List<Int>>): Int {
        if (input.isEmpty()) return 0

        fun calculate(commonity: Int): List<Int> {
            val resultList = input.toMutableList()
            for (col in input[0].indices) {
                if (resultList.size == 1)
                    break
                var numberOfOnes = 0
                for (row in resultList.indices) {
                    if (resultList[row][col] == 1)
                        numberOfOnes++
                }
                val numberToKeep = if (numberOfOnes >= resultList.size / 2.0) commonity else 1 - commonity
                resultList.removeIf { it[col] != numberToKeep }
            }
            return resultList[0]
        }

        val mostCommon = calculate(1).binaryListToInt()
        val leastCommon = calculate(0).binaryListToInt()
        println("most common = $mostCommon, least common = $leastCommon, most common * least common = ${mostCommon * leastCommon}")

        return mostCommon * leastCommon
    }

    val testInput = processInput(readInput("day03", "test"))
    println("testInput: $testInput")
    val input = processInput(readInput("day03", "input"))
    println("input: $input")

    check(part1(testInput) == 198)
    part1(input)
    check(part2(testInput) == 230)
    part2(input)
}