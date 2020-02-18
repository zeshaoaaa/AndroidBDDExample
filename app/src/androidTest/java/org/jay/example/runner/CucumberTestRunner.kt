package org.jay.example.runner

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import cucumber.api.android.CucumberAndroidJUnitRunner
import io.mockk.every
import io.mockk.mockk
import org.jay.example.BuildConfig
import org.jay.example.MyApplication
import org.jay.example.login.LoginContract
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

const val CUCUMBER_TAGS_KEY = "cucumberTagsKey"

const val CUCUMBER_SCENARIO_KEY = "cucumberScenarioKey"

/**
 * Cucumber 测试运行器
 */
open class CucumberTestRunner : CucumberAndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        Log.e("CucumberTestRunner", "onCreate")
        val tags = BuildConfig.TEST_TAGS
        if (tags.isNotEmpty()) {
            val tagsValue = tags.replace("\\s", "")
            arguments?.putString(CUCUMBER_TAGS_KEY, tagsValue)
        }

        val scenario = BuildConfig.TEST_SCENARIO
        if (scenario.isNotEmpty()) {
            val scenarioValue = scenario.replace(" ", "\\\\s")
            arguments?.putString(CUCUMBER_SCENARIO_KEY, scenarioValue)
        }

        super.onCreate(arguments)

    }

    override fun onStart() {
        super.onStart()
        Log.e("CucumberTestRunner", "onStart")
        println("hooks---onStart")

    }

}

