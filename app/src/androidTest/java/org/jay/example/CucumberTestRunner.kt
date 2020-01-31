package org.jay.example

import android.os.Bundle
import android.util.Log
import cucumber.api.android.CucumberAndroidJUnitRunner

const val CUCUMBER_TAGS_KEY = "cucumberTagsKey"

const val CUCUMBER_SCENARIO_KEY = "cucumberScenarioKey"

/**
 * Cucumber 测试运行器
 */
class CucumberTestRunner : CucumberAndroidJUnitRunner() {

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
}