package org.test.backend.util

class FirstMyKotlin ()  {
    val a: Int = 1
    val b = 1   // Тип `Int` выведен автоматически
    val PI = 3.14
    var x2 = 0

    fun incrementX() {
        x2 += 1
        println(x2)
    }
    fun getNumberFive() : Int {
        val c: Int  // Тип обязателен, когда значение не инициализируется
        c = 1
        var x = 5 // Тип `Int` выведен автоматически
        x += 1
      return 5
    }
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
    fun sum2(a: Int, b: Int) = a + b

    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }
    fun replaceAB(){
        var a = 1
// просто имя переменной в шаблоне:
        val s1 = "a равно $a"

        a = 2
// произвольное выражение в шаблоне:
        val s2 = "${s1.replace("равно", "было равно")}, но теперь равно $a"
    println(s2)
    }
    fun max(a: Int, b: Int): Int {
        if (a > b)
            return a
        else
            return b
    }
    fun parseInt(str: String): Int? {
        return str.toInt()
    }
    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // в этом блоке `obj` автоматически преобразован в `String`
            return obj.length
        }

        // `obj` имеет тип `Any` вне блока проверки типа
        return null
    }
}
fun main(args: Array<String>){
    /*println("Hello Kotlin")
    println(FirstMyKotlin().getNumberFive())
    println(FirstMyKotlin().sum(7,9))
    println(FirstMyKotlin().sum2(8,9))
    FirstMyKotlin().printSum(2,9)
    println()
    println(999)
    FirstMyKotlin().incrementX()
    FirstMyKotlin().replaceAB()*/
    println(FirstMyKotlin().max(15,13))
    println(FirstMyKotlin().parseInt("567"))
    println(FirstMyKotlin().getStringLength("567"))
}

