package com.company.initialization

import com.company.layers.Layer
import com.company.neural.Node

class NetworkCreator {
    fun create(): Network {
        val inputs = ArrayList<Node>()
        for (i in 0..8) {
            inputs.add(Node())
        }
        val hidden1 = ArrayList<Node>()
        for (i in 0..6) {
            hidden1.add(Node())
        }

        val hidden2 = ArrayList<Node>()
        for (i in 0..6) {
            hidden2.add(Node())
        }

        val outputs = ArrayList<Node>()
        for (i in 0..2) {
            outputs.add(Node())
        }

        val inputLayer = Layer(inputs)
        val hiddenLayer1 = Layer(hidden1)
        val hiddenLayer2 = Layer(hidden2)
        val outputLayer = Layer(outputs)

        hiddenLayer1.connectPrev(inputLayer)
        hiddenLayer2.connectPrev(hiddenLayer1)
        outputLayer.connectPrev(hiddenLayer2)

        val res = Network(inputLayer, outputLayer)

        return res
    }
}
