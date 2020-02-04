package org.jay.example.test

import cucumber.api.CucumberOptions

@CucumberOptions(
    features = ["features"],
    glue = ["org.jay.example.steps"],
    tags = ["@e2e", "@smoke"],
    strict = true
)
class CucumberTestCase






