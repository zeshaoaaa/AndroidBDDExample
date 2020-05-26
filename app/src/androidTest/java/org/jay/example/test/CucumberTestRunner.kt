package org.jay.example.test

import android.os.Bundle
import android.util.Log
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner
import java.io.File

/**
 * Cucumber 测试运行器
 */
@Suppress("unused")
@CucumberOptions(
    features = ["features"],
    plugin = ["junit:/data/data/org.jay.example/JUnitReport.xml",
        "json:/data/data/org.jay.example/JSONReport.json"],
    strict = true,
    glue = ["org.jay.example.test.steps"]
)
open class CucumberTestRunner : CucumberAndroidJUnitRunner() {

    /**
     * @return 测试报告的绝对路径
     */
    private val absoluteFilesPath: String
        get() { //sdcard/Android/data/org.jay.example
            val directory = targetContext.getExternalFilesDir(null)
            Log.e("", "directory: ${directory?.path}")
            return File(directory, "reports").absolutePath
        }

    /**
     * @return 插件配置，包含 XML、HTML 和 JSON 报告的路径
     */
    private val pluginConfigurationString: String
        get() {
            val cucumber = "cucumber"
            val separator = "--"
            return "junit:" + absoluteFilesPath + "/" + cucumber + ".xml" + separator +
                    "html:" + absoluteFilesPath + "/" + cucumber + ".html"
        }

    override fun onCreate(bundle: Bundle) {
        Log.e("", "directory: ${pluginConfigurationString}")
        // 创建插件配置
        bundle.putString("plugin", pluginConfigurationString)
        super.onCreate(bundle)
    }

}

