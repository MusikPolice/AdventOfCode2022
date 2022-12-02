package ca.jonathanfritz.adventofcode2022

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen
import platform.posix.getenv

fun readResourcesFile(fileName: String): List<String> {
    return readFile(resourcesPath() + fileName)
}

fun readTestResourcesFile(fileName: String): List<String> {
    return readFile(testResourcesPath() + fileName)
}

// stolen from https://www.nequalsonelifestyle.com/2020/11/16/kotlin-native-file-io/
private fun readFile(filePath: String): List<String> {
    val returnBuffer = StringBuilder()
    val file = fopen(filePath, "r") ?:
    throw IllegalArgumentException("Cannot open input file $filePath")

    try {
        memScoped {
            val readBufferLength = 64 * 1024
            val buffer = allocArray<ByteVar>(readBufferLength)
            var line = fgets(buffer, readBufferLength, file)?.toKString()
            while (line != null) {
                returnBuffer.append(line)
                line = fgets(buffer, readBufferLength, file)?.toKString()
            }
        }
    } finally {
        fclose(file)
    }

    return returnBuffer.toString().split(newlineSeparator())
}

fun resourcesPath(): String {
    return getenv("RESOURCES_PATH")?.toString() ?: "C:/Users/jonfr/Documents/GitHub/AdventOfCode2022/src/nativeMain/resources/"
}

fun testResourcesPath(): String {
    return getenv("TEST_RESOURCES_PATH")?.toString() ?: "C:/Users/jonfr/Documents/GitHub/AdventOfCode2022/src/nativeTest/resources/"
}

fun newlineSeparator(): String {
    return getenv("NEWLINE_SEPARATOR")?.toString() ?: "\n"
}