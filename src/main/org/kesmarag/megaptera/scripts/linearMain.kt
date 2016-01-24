import org.kesmarag.megaptera.ann.MixtureDensity
import org.kesmarag.megaptera.ann.MixtureDensityNetwork
import org.kesmarag.megaptera.linear.ColumnVector
import org.kesmarag.megaptera.linear.columnVectorOf

fun main(args: Array<String>) {
    val a = ColumnVector(4)
    val a1 = ColumnVector(4)
    val out = ColumnVector(2)
    val out1 = ColumnVector(2)
    val mdn = MixtureDensityNetwork(4, 25, 2, 2)
    val sample1 = columnVectorOf(-0.1, 0.2, 0.1, 0.2)
    val sample2 = columnVectorOf(0.5, 0.5, 0.5, 0.5 )
    val md = MixtureDensity(2, 2)
    var lambda = 0.01
    for (i in 0..5000) {
        a.randomize(0.09, 0.11)
        a1.randomize(0.49, 0.51)
        out.randomize(0.0, 0.5)
        out1.randomize(0.5, 1.5)
        var par = lambda * (5000.0 / (5000.0 + i.toDouble()))
        mdn.adaptOne(a, out, par)
        mdn.adaptOne(a1, out1, par)
    }
    md.hyperParameters(mdn(sample1))
    println(md)
    md.hyperParameters(mdn(sample2))
    println(md)
}