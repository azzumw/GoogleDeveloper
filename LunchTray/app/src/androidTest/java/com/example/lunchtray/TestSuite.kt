package com.example.lunchtray

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MenuContentTests::class,
    NavigationTests::class,
    OrderFunctionalityTests::class
)
class TestSuite {
}