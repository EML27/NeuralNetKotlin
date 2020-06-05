package com.company.initialization

import com.company.layers.Layer
import sun.rmi.runtime.Log

class Network() {
    lateinit var inputLayer: Layer
    lateinit var outputLayer: Layer

    constructor(inputLayer: Layer, outputLayer: Layer): this() {
        this.inputLayer = inputLayer
        this.outputLayer = outputLayer
    }

    fun learn(input: Int, wanted: Int) {
        start(input)
        outputLayer.learn(intToBinaryString(wanted))
    }

    fun start(number: Int) {
        inputLayer.start(intToBinaryString(number))
    }

    fun clear() {
        outputLayer.clear()
    }

    fun getResult(): Int = binaryStringToInt(outputLayer.getResult())


    private fun intToBinaryString(int: Int): String {
        return int.toString(2).also { println(it) }
    }

    private fun binaryStringToInt(string: String): Int {
        return string.toInt(2).also { println(it) }
    }
}
