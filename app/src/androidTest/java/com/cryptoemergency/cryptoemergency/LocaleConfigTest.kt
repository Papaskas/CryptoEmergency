package com.cryptoemergency.cryptoemergency

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class LocaleConfigTest {

    @Disabled
    @Test
    fun localeEqualsConf() {
        val locales = readLocalesFromXml()
        assert(locales.isNotEmpty())
    }

    private fun readLocalesFromXml(): List<String> {
        val locales = mutableListOf<String>()
        val resDir = File("src/main/res")
        val localeDirs = resDir.listFiles { file -> file.isDirectory && file.name.startsWith("values-") }

        localeDirs?.forEach { dir ->
            val locale = dir.name.removePrefix("values-")
            locales.add(locale)
        }

        return locales
    }


    interface BuildScriptModel {
        val buildScript: String
    }
}
