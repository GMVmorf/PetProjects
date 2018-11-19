package ru.mgprojects

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.sql.Date

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

        var actual : String? = null
//        actual = dataClassCreator.createDtoFromDataClass(InnerData.SecondLevelData::class.java)
//        actual = dataClassCreator.createDtoFromDataClass(InnerData::class.java)
//        actual = dataClassCreator.createDtoFromDataClass(MySuperData::class.java)
        actual = dataClassCreator.createDtoFromDataClass(DataClassCreator::class.java)


//region Comment: results of clazz.simpleName + "_" + clazz.typeName + "_" + clazz.getName() + "_" + clazz.typeName

//        InnerData.SecondLevelData
//        clazz.simpleName -    SecondLevelData
//        clazz.typeName -      ru.mgprojects.DataClassCreatorTest$createDtoFromDataClass$InnerData$SecondLevelData
//        clazz.name -          ru.mgprojects.DataClassCreatorTest$createDtoFromDataClass$InnerData$SecondLevelData
//        clazz.typeName -      null

//        InnerData
//        clazz.simpleName -    createDtoFromDataClass$InnerData
//        clazz.typeName -      ru.mgprojects.DataClassCreatorTest$createDtoFromDataClass$InnerData
//        clazz.name -          ru.mgprojects.DataClassCreatorTest$createDtoFromDataClass$InnerData
//        clazz.typeName -      ru.mgprojects.DataClassCreatorTest$createDtoFromDataClass$InnerData

//        MySuperData
//        clazz.simpleName -    MySuperData
//        clazz.typeName -      ru.mgprojects.MySuperData
//        clazz.name -          ru.mgprojects.MySuperData
//        clazz.typeName -      ru.mgprojects.MySuperData
//endregion
        println (actual)
        assertTrue(true)
    }
}

data class MySuperData(val id: Long, val struct: Struct)

data class Struct(val id: Long, val name: String, val date: Date)
