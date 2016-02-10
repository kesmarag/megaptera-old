package org.kesmarag.megaptera.scripts

import org.kesmarag.megaptera.ann.MixtureDensity
import org.kesmarag.megaptera.ann.MixtureDensityNetwork
import org.kesmarag.megaptera.utils.DataSet
import org.kesmarag.megaptera.utils.toColumnVector
import java.util.*


fun main(args: Array<String>){
    val resultData = DataSet("/home/kesmarag/dev/megInv/results.csv");
    val targetData = DataSet("/home/kesmarag/dev/megInv/parameters.csv");
    val data = resultData[0].data
    val targets = targetData[0].data
    val trainingSize = data.size-2
    val mdn = MixtureDensityNetwork(5, 16, 2, 2)
    var lambda = 0.001
    val md = MixtureDensity(2, 2)
    for (i in 0..1000000) {
        var chosen = Random().nextInt(trainingSize)
        val input = data[chosen].data.toColumnVector()
        val output = targets[chosen].data.toColumnVector()
        //println("### $chosen ### ${input.t()} ### ${output.t()} ###")
        var par = lambda * (5000.0 / (5000.0 + i.toDouble()))
        mdn.adaptOne(input,output,lambda)
    }
    val n = mdn.apply(data[1681].data.toColumnVector())
    md.hyperParameters(n)
    println(md)
    println()




}


