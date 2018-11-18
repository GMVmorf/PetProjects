package ru.mgprojects

import java.lang.StringBuilder

class DataClassCreator {
    fun <reified T> createDtoFromDataClass(clazz: T): String {
        val result = StringBuilder()
        result.append("data class ${clazz::class.simpleName}Dto")

    }
}
