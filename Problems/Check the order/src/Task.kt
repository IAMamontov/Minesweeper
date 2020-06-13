import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val n = scanner.nextInt()
    var prev = scanner.nextInt()
    var asc = true
    for (i in 2..n) {
        val m = scanner.nextInt()
        if (m < prev) {
            asc = false
        }
        prev = m
    }
    if (asc) {
        print("YES")
    } else {
        print("NO")
    }
}