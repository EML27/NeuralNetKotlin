package com.company.neural

import kotlin.math.pow

class Connection() {
    lateinit var fromNode: Node
    lateinit var toNode: Node

    constructor(fromNode: Node, toNode: Node) : this() {
        this.fromNode = fromNode
        this.toNode = toNode
        fromNode.addOutConnection(this)
        toNode.addInputConnection(this)
    }

    var weight: Double = roundTo100th(Math.random() * 2 - 1)
    var learnRate: Double = 1.0
    fun learn(y_wanted: Double) {
        println("Old weight: $weight")
        weight -= (learnRate * errFun(y_wanted))
        weight = roundTo100th(weight)
        println("New weight: $weight")
    }

    private fun errFun(wanted: Double): Double {
        val mem = this.weight
        this.weight = 0.0
        val a0 = toNode.guess()
        this.weight = 0.1
        val a1 = toNode.guess()

        val res = (lossFun(a1, wanted) - lossFun(a0, wanted)) / 0.1
        weight = mem
        return res
    }
}


fun lossFun(y_actual: Double, y_wanted: Double): Double {
    return (y_actual - y_wanted).pow(2)
}

