package day01

import readInput

fun main() {
    fun processInput(input: List<String>): MutableList<Int> {
        return input.map { it.toInt() }.toMutableList()
    }

    fun part1(input: MutableList<Int>): Int {
        var previous = input.removeFirst()
        var numbersOfLarger = 0
        for (measurement in input) {
            if (measurement > previous) {
                numbersOfLarger++
            }
            previous = measurement
        }
        return numbersOfLarger
    }

    fun part2(measurements: MutableList<Int>): Int {
        var numbersOfLarger = 0
        var previous = measurements.slice(0 until 3).sum()
        measurements.removeFirst()
        for (i in 0..measurements.size - 3) {
            val measurement = measurements.slice(i until i+3).sum()
            if (measurement > previous)
                numbersOfLarger++
            previous = measurement
        }
        return numbersOfLarger
    }

    val testInput = readInput("day01", "test")
    val input = readInput("day01", "input")

    // test if implementation meets criteria from the description, like:
    check(part1(processInput(testInput)) == 7)
    println(part1(processInput(input)))

    check(part2(processInput(testInput)) == 5)
    println(part2(processInput(input)))
}
