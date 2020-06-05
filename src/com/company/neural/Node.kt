package com.company.neural

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Node {
    var value: Double = 0.0

    var inputConnections = ArrayList<Connection>()
    var outputConnections = ArrayList<Connection>()

    fun addOutConnection(connection: Connection) {
        outputConnections.add(connection)
    }

    fun addInputConnection(connection: Connection) {
        inputConnections.add(connection)
    }

    fun guess(): Double {
        value = 0.0
        for (connection in inputConnections) {
            value += connection.fromNode.value * connection.weight
        }
        value = sigmoid(value)
        value = roundTo100th(value)
        return value
    }

    fun learn(wanted: Double) {
        for (connection in inputConnections) {
            connection.learn(wanted)
        }
        value = wanted
    }

    fun clear() {
        value = 0.0
    }

    fun guessForBackPropagnation(): Double {
        var res = 0.0
        for (connection in outputConnections) {
            res += connection.toNode.value * connection.weight
        }
        res = sigmoid(res)
        res = roundTo100th(res)
        println("Guessed for back propagnation: $res")
        return res
    }
}

fun sigmoid(x: Double): Double {
    return (1 / (1 + Math.E.pow(-x)))
}

fun roundTo100th(x: Double): Double {
    return (x * 100).roundToLong() / 100.0
}
