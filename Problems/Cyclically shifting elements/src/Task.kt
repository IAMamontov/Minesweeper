import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val n = scanner.nextInt()
    var A = IntArray(n)
    for (i in A.indices) {
        A[i] = scanner.nextInt()
    }
    val alast = A[A.lastIndex]
    for (i in A.lastIndex downTo 1) {
        A[i] = A[i - 1]
    }
    A[0] = alast
    for (i in A) {
        print("$i ")
    }
}
