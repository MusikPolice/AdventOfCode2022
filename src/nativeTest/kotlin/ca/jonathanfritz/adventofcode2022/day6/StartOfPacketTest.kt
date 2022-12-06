package ca.jonathanfritz.adventofcode2022.day6

import ca.jonathanfritz.adventofcode2022.readResourcesFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class StartOfPacketTest {

    private val startOfPacket = StartOfPacket()

    @Test
    fun startOfPacketTest() {
        assertEquals(7, startOfPacket.part1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    }

    @Test
    fun startOfPacketTest2() {
        assertEquals(5, startOfPacket.part1("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    }

    @Test
    fun startOfPacketTest3() {
        assertEquals(6, startOfPacket.part1("nppdvjthqldpwncqszvftbrmjlhg"))
    }

    @Test
    fun startOfPacketTest4() {
        assertEquals(10, startOfPacket.part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    }

    @Test
    fun startOfPacketTest5() {
        assertEquals(11, startOfPacket.part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }

    @Test
    fun startOfPacket() {
        val line = readResourcesFile("day6/datastream.txt")[0]
        assertEquals(1287, startOfPacket.part1(line))
    }

    @Test
    fun startOfMessageTest() {
        assertEquals(19, startOfPacket.part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    }

    @Test
    fun startOfMessageTest2() {
        assertEquals(23, startOfPacket.part2("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    }

    @Test
    fun startOfMessageTest3() {
        assertEquals(23, startOfPacket.part2("nppdvjthqldpwncqszvftbrmjlhg"))
    }

    @Test
    fun startOfMessageTest4() {
        assertEquals(29, startOfPacket.part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    }

    @Test
    fun startOfMessageTest5() {
        assertEquals(26, startOfPacket.part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))
    }

    @Test
    fun startOfMessage() {
        val line = readResourcesFile("day6/datastream.txt")[0]
        assertEquals(3716, startOfPacket.part2(line))
    }
}