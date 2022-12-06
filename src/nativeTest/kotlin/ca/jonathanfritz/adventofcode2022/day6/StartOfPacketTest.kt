package ca.jonathanfritz.adventofcode2022.day6

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class StartOfPacketTest {

    private val startOfPacket = StartOfPacket()

    @Test
    fun offsetTest() {
        assertEquals(7, startOfPacket.part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    }

    @Test
    fun offsetTest2() {
        assertEquals(5, startOfPacket.part1("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    }

    @Test
    fun offsetTest3() {
        assertEquals(6, startOfPacket.part1("nppdvjthqldpwncqszvftbrmjlhg"))
    }

    @Test
    fun offsetTest4() {
        assertEquals(10, startOfPacket.part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    }

    @Test
    fun offsetTest5() {
        assertEquals(11, startOfPacket.part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }

    @Test
    fun offset() {
        val line = readResourcesFile("day6/datastream.txt")[0]
        assertEquals(1287, startOfPacket.part1(line))
    }
}