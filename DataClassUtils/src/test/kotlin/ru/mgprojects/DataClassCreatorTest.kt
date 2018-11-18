package ru.mgprojects

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DataClassCreatorTest {

    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
    }

    @Test
    fun `createDtoFromDataClass`() {
        data class InnerData(
            val name: String
        ) {
            inner class SecondLevelData(val description: String)
            fun innerDataInnerFun() : SecondLevelData {
                return SecondLevelData("description")
            }
        }
        val dataClassCreator = DataClassCreator()

        var actual = dataClassCreator.createDtoFromDataClass(InnerData.SecondLevelData::class.java)
        actual = dataClassCreator.createDtoFromDataClass(InnerData::class.java)
//        actual = dataClassCreator.createDtoFromDataClass(MySuperData::class.java)
//        clazz.simpleName + "_" + clazz.typeName + "_" + clazz.getName() + "_" + clazz.typeName
        assertEquals(actual, "")
        assertTrue(true)
    }
}

data class MySuperData(val id: Long)