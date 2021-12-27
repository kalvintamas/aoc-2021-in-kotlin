package day02

import java.io.File

fun readFile(): List<String> {
    return File("input.txt").readLines()
}

val lines = readFile().onEach {
    println(it)
}

var horizontal = 0
var depth = 0
var aim = 0

fun calculate(command: String) {
    val (direction, quantity) = command.split(" ")
    val q = quantity.toInt()
    when (direction) {
        "forward" -> horizontal += q
        "down" -> depth += q
        "up" -> depth -= q
    }
    println("$horizontal $depth")
}

fun calculate2(command: String) {
    val (direction, quantity) = command.split(" ")
    val q = quantity.toInt()
    when (direction) {
        "forward" -> {
            horizontal += q
            depth += aim * q
        }
        "down" -> aim += q
        "up" -> aim -= q
    }
    println("$horizontal $depth")
}

lines.forEach {
    calculate2(it)
}

println(horizontal * depth)