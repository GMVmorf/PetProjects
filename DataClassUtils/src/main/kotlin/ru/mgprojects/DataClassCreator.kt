package ru.mgprojects

import java.lang.StringBuilder

class DataClassCreator {
    fun <T> createDtoFromDataClass(clazz: Class<T>): String {
        val result = StringBuilder()
        val className = clazz.simpleName.takeLastWhile { it != '$' }
        result.append("data class ${className}Dto(\n")
        result.append("\n")
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
//        clazz.simpleName + "_" + clazz.typeName + "_" + clazz.name + "_" + clazz.typeName

        result.append(")\n")
        return result.toString()
    }
}
