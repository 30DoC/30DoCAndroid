package com.palzzak.blur

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import org.junit.Test

/**
 * Created by jaeyoonyoo on 2018. 1. 27..
 */

class CoroutineTest {
    @Test fun launch_with_context() {
        val background = newFixedThreadPoolContext(3, "background")
        launch(UI) {

        }
    }
}