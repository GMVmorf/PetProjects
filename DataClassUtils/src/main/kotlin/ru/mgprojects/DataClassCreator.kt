package ru.mgprojects

import java.lang.StringBuilder
import java.lang.reflect.Field
import kotlin.reflect.jvm.kotlinProperty

class DataClassCreator {
    val soure: Array<String> = arrayOf()

    fun <T> createDtoFromDataClass(clazz: Class<T>): String {
        val packageName = clazz.`package`.name
        val packageList: MutableSet<String> = mutableSetOf<String>()


        val result = StringBuilder()
        val className = clazz.simpleName.takeLastWhile { it != '$' }
        result.append("data class ${className}Dto(\n")
        clazz.declaredFields.forEach { field ->
            result.append("    val ${field.name}: ${getFieldType(field)}\n")

            if (isNotSamePackage(packageName, field)) {
                packageList.add(field.annotatedType.type.typeName.toString())
            }
//            result.append("    ${field.type.name.takeLastWhile { it != '.' }}\n")
//            result.append("    \n")
        }
        result.append(")\n")

        val packages = packageList.joinToString(separator = "") { "import $it\n" }

        return packages + result.toString()
    }
}

fun isNotSamePackage(packageName: String, field: Field): Boolean {
    return when {
        field.type.`package` == null -> false
        field.type.`package`.name.toString() != packageName -> true
        else -> false
    }
}

fun getFieldType(field: Field): String {
    return if (field.kotlinProperty != null) {
        field.kotlinProperty!!.returnType.classifier.toString()
    } else {
        field.type.name
    }.takeLastWhile { it != '.' }
}