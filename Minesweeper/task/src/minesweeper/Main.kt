package minesweeper
import java.util.*
import javax.xml.stream.events.Characters
import kotlin.random.Random

val width = 9
val height = 9

object minefield {

    var marked = mutableListOf<Int>()
    var freed = mutableListOf<Int>()
    var field = Array(height) {Array(height) {'.'}}
    val digits = arrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
    var minesList = mutableListOf<Int>()
    var explosion = false


    fun getNewField(totalMines: Int) {
        minesList = (0..width * height - 1).shuffled().subList(0, totalMines).toMutableList()
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (minesList.contains(i * height + j)) field[i][j] = 'X'
                else field[i][j] = '.'
            }
        }
    }

    fun calcAround(){
        for (i in 0 until width) {
            for (j in 0 until height) {
                var counter = 0
                if (field[i][j] != 'X') {
                    if (i == 0) {
                        if (j == 0) {
                            if (field[i][j + 1] == 'X') counter++
                            if (field[i + 1][j + 1] == 'X') counter++
                            if (field[i + 1][j] == 'X') counter++
                        } else if (j == height - 1) {
                            if (field[i][j - 1] == 'X') counter++
                            if (field[i + 1][j - 1] == 'X') counter++
                            if (field[i + 1][j] == 'X') counter++
                        } else {
                            if (field[i + 1][j] == 'X') counter++
                            if (field[i][j - 1] == 'X') counter++
                            if (field[i + 1][j - 1] == 'X') counter++
                            if (field[i][j + 1] == 'X') counter++
                            if (field[i + 1][j + 1] == 'X') counter++
                        }
                    } else if (i == width - 1) {
                        if (j == 0) {
                            if (field[i][j + 1] == 'X') counter++
                            if (field[i - 1][j + 1] == 'X') counter++
                            if (field[i - 1][j] == 'X') counter++
                        } else if (j == height - 1) {
                            if (field[i][j - 1] == 'X') counter++
                            if (field[i - 1][j - 1] == 'X') counter++
                            if (field[i - 1][j] == 'X') counter++
                        } else {
                            if (field[i - 1][j] == 'X') counter++
                            if (field[i][j - 1] == 'X') counter++
                            if (field[i - 1][j - 1] == 'X') counter++
                            if (field[i][j + 1] == 'X') counter++
                            if (field[i - 1][j + 1] == 'X') counter++
                        }
                    } else {
                        if (j == 0) {
                            if (field[i][j + 1] == 'X') counter++
                            if (field[i - 1][j + 1] == 'X') counter++
                            if (field[i - 1][j] == 'X') counter++
                            if (field[i + 1][j + 1] == 'X') counter++
                            if (field[i + 1][j] == 'X') counter++
                        } else if (j == height - 1) {
                            if (field[i][j - 1] == 'X') counter++
                            if (field[i - 1][j - 1] == 'X') counter++
                            if (field[i - 1][j] == 'X') counter++
                            if (field[i + 1][j - 1] == 'X') counter++
                            if (field[i + 1][j] == 'X') counter++
                        } else {
                            if (field[i - 1][j] == 'X') counter++
                            if (field[i][j - 1] == 'X') counter++
                            if (field[i - 1][j - 1] == 'X') counter++
                            if (field[i][j + 1] == 'X') counter++
                            if (field[i - 1][j + 1] == 'X') counter++
                            if (field[i + 1][j] == 'X') counter++
                            if (field[i + 1][j - 1] == 'X') counter++
                            if (field[i + 1][j + 1] == 'X') counter++
                        }
                    }
                    if (counter > 0) field[i][j] = digits[counter - 1]
                }

            }
        }
    }


    fun maskMines() {
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (field[i][j] == 'X') field[i][j] = ('.')
            }
            println()
        }
    }

    fun printField(showMines: Boolean) {
        println(" │123456789│")
        println("—│—————————│")
        for (i in 0 until width) {
            print("${i + 1}│")
            for (j in 0 until height) {
                if (marked.contains(i * height + j) && !showMines) {
                    print('*')
                } else if (minesList.contains(i * height + j) && showMines) {
                    print('X')
                } else if (freed.contains(i * height + j)) {
                    if (field[i][j] == '.') print('/')
                    else print(field[i][j])
                }
                else print('.')
            }
            println("│")
        }
        println("—│—————————│")
    }

    fun mark(x: Int, y: Int) {
        if (x > 0 && x < 10 && y > 0 && y < 10) {
            if (!freed.contains((x - 1) * height + y - 1)) {
                if (marked.contains((x - 1) * height + y - 1)) marked.remove((x - 1) * height + y - 1)
                else marked.add(marked.lastIndex + 1, (x - 1) * height + y - 1)
            }
        }
    }

    fun free(x: Int, y: Int) {
        if (x > 0 && x < 10 && y > 0 && y < 10 && !freed.contains((x - 1) * height + y - 1)) {
            if (minesList.contains((x - 1) * height + y - 1)) {
                explosion = true
            } else if (Character.isDigit(field[x - 1][y -  1])
                    && Character.getNumericValue(field[x - 1][y -  1]) != 0
                    && Character.getNumericValue(field[x - 1][y -  1]) != 9) {
                if (marked.contains((x - 1) * height + y - 1)) marked.remove((x - 1) * height + y - 1)
                freed.add(freed.lastIndex + 1, (x - 1) * height + y - 1)
            } else {
                fill(x, y)
            }
        }
    }

    fun fill(x: Int, y: Int) {
        if (x > 0 && x < 10 && y > 0 && y < 10 && !freed.contains((x - 1) * height + y - 1)) {
            if (marked.contains((x - 1) * height + y - 1)) marked.remove((x - 1) * height + y - 1)
            if (field[x - 1][y -  1] == '.') {
                freed.add(freed.lastIndex + 1, (x - 1) * height + y - 1)
                fill(x + 1, y)
                fill(x - 1, y)
                fill(x, y - 1)
                fill(x, y + 1)
                fill(x + 1, y + 1)
                fill(x - 1, y - 1)
                fill(x + 1, y - 1)
                fill(x - 1, y + 1)
            } else if (Character.isDigit(field[x - 1][y -  1])
                    && Character.getNumericValue(field[x - 1][y -  1]) != 0
                    && Character.getNumericValue(field[x - 1][y -  1]) != 9) {
                freed.add(freed.lastIndex + 1, (x - 1) * height + y - 1)
            }
        }
    }

}


fun main() {
    //print("Hello, World!")
    val scanner = Scanner(System.`in`)
    var mField = minefield
    print("How many mines do you want on the field? ")
    val cMines = scanner.nextInt()
    mField.getNewField(cMines)
    mField.calcAround()
    mField.maskMines()
    mField.printField(false)
    print("Set/unset mines marks or claim a cell as free: ")
    val y = scanner.nextInt()
    val x = scanner.nextInt()
    val s = scanner.next()
//    println(mField.minesList.sorted())
//    println((x - 1) * height + y - 1)
//    println(mField.minesList.contains((x - 1) * height + y - 1))
    if (s == "free") {
        if (mField.minesList.contains((x - 1) * height + y - 1)) {
            mField.getNewField(cMines)
            mField.calcAround()
            mField.maskMines()
        }
        mField.free(x, y)
    } else if (s == "mine") {
        mField.mark(x, y)
    }
    while (minefield.marked.sorted() != minefield.minesList.sorted()
            && !mField.explosion && mField.freed.lastIndex != height * width - 1) {
        mField.printField(false)
        print("Set/unset mines marks or claim a cell as free: ")
        val y = scanner.nextInt()
        val x = scanner.nextInt()
        val s = scanner.next()
        if (s == "free") {
            mField.free(x, y)
        } else if (s == "mine") {
            mField.mark(x, y)
        }
        /*
        println(mField.freed)
        println(mField.marked.sorted())
        println(mField.minesList.sorted())
        println(mField.marked.sorted() != mField.minesList.sorted())
        println(mField.freed.lastIndex != height * width - 1)
        println(mField.explosion)
         */
    }
    if (mField.explosion) {
        mField.printField(true)
        println("You stepped on a mine and failed!")
    } else {
        mField.printField(false)
        println("Congratulations! You found all the mines!")
    }
}
