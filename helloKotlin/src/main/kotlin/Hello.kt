package ru.mgprojects

fun hello() {
    listOf("Hello", "world", "of", "Kotlin")
}

interface Expr
class Num(val value: Long) : Expr
class Sum(val left: Expr, val rigth: Expr) : Expr
class Sub(val left: Expr, val rigth: Expr) : Expr
class Mul(val left: Expr, val rigth: Expr) : Expr
class Dev(val left: Expr, val rigth: Expr) : Expr

fun eval(e: Expr) : Long =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.rigth)
        is Sub -> eval(e.left) - eval(e.rigth)
        is Mul -> eval(e.left) * eval(e.rigth)
        is Dev -> eval(e.left) / eval(e.rigth)
        else -> throw java.lang.IllegalArgumentException("Unknown expression.")
    }


//operator fun Expr.plus(that: Expr) = this + eval(that)
//operator fun Num.plus(that: Num) = this.value + that.value


fun main(args: Array<String>) {
    println(eval(
//            Dev(
                    Sum(Num(1), Num(33))
//                    Mul(Num(33),
//                            Sub(Num(54), Num(12))))
    ))
}
