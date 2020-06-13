import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val n = scanner.nextInt()
    var min = scanner.nextInt()
    for (i in 2..n) {
        val m = scanner.nextInt()
        if (m < min) {
            min = m
        }
    }
    print(min)
}