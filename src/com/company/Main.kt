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
    train(net, 100)
//    writeNet(net)

//    val net = readNet()
//    net.start(88)
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
    }
}
