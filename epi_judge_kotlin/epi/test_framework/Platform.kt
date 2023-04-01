package epi.test_framework

import kotlin.Throws
import kotlin.jvm.JvmOverloads

object Platform {
    private var isWindows: Boolean? = null
    private var is64Bit: Boolean? = null
    private var dllLoaded: TriBool = TriBool.INDETERMINATE
    private var enableTtyOutput = false
    private var enableColorOutput = false
    fun stdOutClearLine() {
        System.out.print("\r")
    }

    fun setOutputOpts(ttyMode: TriBool, colorMode: TriBool) {
        enableTtyOutput = ttyMode.getOrDefault(System.console() != null)
        enableColorOutput = colorMode.getOrDefault(enableTtyOutput)
        initColorOutput()
    }

    fun useTtyOutput(): Boolean {
        return enableTtyOutput
    }

    fun useColorOutput(): Boolean {
        return enableColorOutput
    }

    fun runningOnWin(): Boolean {
        if (isWindows == null) {
            isWindows = System.getProperty("os.name").startsWith("Windows")
        }
        return isWindows!!
    }

    fun runningOn64BitVM(): Boolean {
        if (is64Bit == null) {
            val bits: String = System.getProperty("sun.arch.data.model", "?")
            when (bits) {
                "64" -> is64Bit = true
                "?" -> is64Bit = System.getProperty("java.vm.name").toLowerCase().contains("64")
                else -> is64Bit = false
            }
        }
        return is64Bit!!
    }

    private fun initColorOutput() {
        if (runningOnWin() && useColorOutput() && dllLoaded === TriBool.INDETERMINATE) {
            val dllName = if (runningOn64BitVM()) "console_color_64" else "console_color_32"
            try {
                System.loadLibrary(dllName)
                dllLoaded = TriBool.TRUE
            } catch (ex: UnsatisfiedLinkError) {
                dllLoaded = TriBool.FALSE
                System.out.printf("""
    Warning: %s.dll was not found. Colored output is disabled.
    In order to enable it, pass -Djava.library.path=<path to EPIJudge>/epi_judge_java/epi/test_framework option to java.
    
    """.trimIndent(),
                        dllName)
            }
        }
    }

    fun winSetConsoleTextAttribute(attr: Int): Int {
        return if (dllLoaded === TriBool.TRUE) {
            winSetConsoleTextAttributeImpl(attr)
        } else 0
    }

    /**
     * Interface to the native wrapper of WinAPI.
     * Set CONSOLE_SCREEN_BUFFER_INFO.wAttributes to attr for the stdout handle.
     * @param attr - new value for wAttributes
     * @return previous value of wAttributes
     */
    private external fun winSetConsoleTextAttributeImpl(attr: Int): Int
}