package com.company.layers

import com.company.neural.Connection
import com.company.neural.Node

class Layer() {
    var nodes: List<Node> = ArrayList()
    var prev: Layer? = null
    var next: Layer? = null

    constructor(nodesList: List<Node>) : this() {
        nodes = nodesList
    }

    fun clear(){
        for (node in nodes){
            node.clear()
        }
        prev?.clear()
    }
    fun connectPrev(layer: Layer) {
        prev = layer
        for (nodeP in layer.nodes) {
            for (nodeT in nodes) {
                Connection(nodeP, nodeT)
            }
        }
        layer.next = this
    }

    fun guess() {
        for (node in nodes) {
            node.guess()
            println("Node activation: " + node.value)
        }
        next?.guess()
    }

    fun learn() {
        for (node in nodes) {
            node.learn(node.guessForBackPropagnation())
        }
        prev?.learn()
    }

    fun learn(wanted: String) {
        for (i in wanted.indices) {
            nodes[nodes.size - i - 1].learn(wanted.reversed()[i].toDouble())
        }
        prev?.learn()
    }

    fun start(binary: String) {
        for (i in binary.indices) {
            nodes[nodes.size - i - 1].value = (binary.reversed()[i].toDouble() - 48).also { println(it) }
        }
        next?.guess()
    }

    fun getResult(): String {
        return String(nodes.map { node ->
            if (node.value > 0.8) {
                '1'
            } else {
                '0'
            }
        }.toCharArray())
    }
}
