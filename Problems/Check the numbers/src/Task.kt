import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val k = scanner.nextInt()
    var yes = true
    val arr = IntArray(k)
    for (i in 0..arr.lastIndex) {
        arr[i] = scanner.nextInt()
    }
    val n = scanner.nextInt()
    val m = scanner.nextInt()
    for (i in 1..arr.lastIndex) {
        if (arr[i - 1] == m && arr[i] == n || arr[i - 1] == n && arr[i] == m) {
            yes = false
        }
    }
    if (yes) {
        print("YES")
    } else {
        print("NO")
    }
}
