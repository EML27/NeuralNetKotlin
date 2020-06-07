package com.company

import com.company.initialization.Network
import com.company.initialization.NetworkCreator
import java.beans.XMLDecoder
import java.beans.XMLEncoder
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
//    var net = NetworkCreator().create()
//    writeNet(net)

    val net = readNet()
    train(net, 10000)
    writeNet(net)
    test(net)
//    val net = readNet()
//    net.start(1)
//    println(net.getResult())
}

fun writeNet(net: Network) {
    val xmle = XMLEncoder(BufferedOutputStream(FileOutputStream("net.xml")))
    xmle.writeObject(net)
    xmle.close()
}

fun readNet(): Network {
    val xmld = XMLDecoder(BufferedInputStream(FileInputStream("net.xml")))
    val res = xmld.readObject() as Network
    xmld.close()
    return res
}

fun test(net: Network) {
    net.start(25)
    if (net.getResult() == 0) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(63)
    if (net.getResult() == 1) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(6)
    if (net.getResult() == 1) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(93)
    if (net.getResult() == 1) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(69)
    if (net.getResult() == 2) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(84)
    if (net.getResult() == 2) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(68)
    if (net.getResult() == 3) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
    net.start(103)
    if (net.getResult() == 1) {
        println("Pass")
    } else {
        println("Not passed")
    }
    net.clear()
}

fun train(net: Network, repeat: Int) {
    for (i in 1..repeat) {
        val number = (Math.random() * 510).toInt()
        val numberAsString = number.toString()
        var wanted = 0
        for (letter in numberAsString) {
            when (letter) {
                '8' -> wanted += 2
                '6' -> wanted += 1
                '9' -> wanted += 1
                '0' -> wanted += 1
            }
        }
        println("==================\nlearning: $number, $wanted ")
        net.learn(number, wanted)
        net.clear()
        net.start(number)
        println("starting after learn: num: $number, result: ${net.getResult()}, waited: $wanted")
        net.clear()
    }
}
