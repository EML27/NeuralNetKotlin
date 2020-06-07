package com.company.neural

import com.company.initialization.Network
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

    fun learn(y_wanted: String, net: Network) {
        println("Old weight: $weight")
        weight -= (learnRate * errFun(y_wanted, net))
        weight = roundTo100th(weight)
        println("New weight: $weight")
    }

    private fun errFun(wanted: String, net: Network): Double {
        val mem = this.weight

        net.startWithCurrentInput()
        val e0 = net.outputLayer.costFun(wanted)
        this.weight = weight + 0.1
        net.startWithCurrentInput()
        val e1 = net.outputLayer.costFun(wanted)
        val res = (e1 - e0) / 0.1
        weight = mem
        return res
    }
}


fun lossFun(y_actual: Double, y_wanted: Double): Double {
    return (y_actual - y_wanted).pow(2)
}

