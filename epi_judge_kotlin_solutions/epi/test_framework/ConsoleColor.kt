package epi.test_framework

import kotlin.Throws
import kotlin.jvm.JvmOverloads

object ConsoleColor {
    private fun getColorCodeWin(color: Color): Int {
        return when (color) {
            Color.FG_RED -> 4 or 8
            Color.FG_GREEN -> 2 or 8
            Color.FG_BLUE -> 1 or 8
            Color.FG_YELLOW -> 6 or 8
            Color.FG_DEFAULT -> 7
            else -> 7
        }
    }

    private fun getColorCodeUnix(color: Color): String {
        return when (color) {
            Color.FG_RED -> "\u001b[31m"
            Color.FG_GREEN -> "\u001b[32m"
            Color.FG_BLUE -> "\u001b[34m"
            Color.FG_YELLOW -> "\u001b[33m"
            Color.FG_DEFAULT -> "\u001b[39m"
            else -> "\u001b[39m"
        }
    }

    fun printStdOutColored(color: Color, value: Object?) {
        if (!Platform.useColorOutput()) {
            System.out.print(value)
            return
        }
        if (Platform.runningOnWin()) {
            System.out.flush()
            val oldTextAttr: Int = Platform.winSetConsoleTextAttribute(getColorCodeWin(color))
            System.out.print(value)
            System.out.flush()
            Platform.winSetConsoleTextAttribute(oldTextAttr)
        } else {
            System.out.printf("%s%s%s", getColorCodeUnix(color),
                    String.valueOf(value),
                    getColorCodeUnix(Color.FG_DEFAULT))
        }
    }

    enum class Color {
        FG_RED, FG_GREEN, FG_BLUE, FG_YELLOW, FG_DEFAULT
    }
}