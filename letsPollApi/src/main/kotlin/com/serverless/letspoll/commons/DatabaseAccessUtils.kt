package com.serverless.letspoll.commons

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager


object DatabaseAccessUtils {
    private var dslContext: DSLContext? = null

    fun getDatabaseConnection(): DSLContext? {
        dslContext?.let {
            return dslContext
        } ?: kotlin.run {
            val userName = LambdaEnvironmentUtils.getValue("databaseUsername")
            val password = LambdaEnvironmentUtils.getValue("databasePassword")
            val databaseUrl = LambdaEnvironmentUtils.getValue("databaseUrl")
            val databasePort = LambdaEnvironmentUtils.getValue("databasePort")
            val databaseName = LambdaEnvironmentUtils.getValue("databaseName")

            val url = StringBuilder().apply {
                append("jdbc:postgresql://")
                append(databaseUrl)
                append(":")
                append(databasePort)
                append("/")
                append(databaseName)
            }.toString()

            println(url)
            return try {
                println(url)
                println(userName)
                println(password)
                val conn = DriverManager.getConnection(url, userName, password)
                DSL.using(conn, SQLDialect.POSTGRES)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
