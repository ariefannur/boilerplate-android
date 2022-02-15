package com.boilerplate

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.github.ariefannur.boilerplate.R
import com.github.ariefannur.boilerplate.core.di.OkhttpProvider
import com.github.ariefannur.boilerplate.feature.MainActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.json.JSONArray
import org.junit.*
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create("okhttp", OkhttpProvider.createClient())
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getGetAsset() {
        val json = FileReader.readStringFromFile("success.json")
        val arr = JSONArray(json)
        val obj = arr.optJSONObject(0)
        Timber.d("AF name ${obj.optString("login")}")
        Assert.assertTrue(json.isNotEmpty())
    }

    @Test
    fun success_get_list() {
        mockWebServer.dispatcher = object : Dispatcher(){
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success.json"))
            }
        }

        activityRule.launchActivity(null)
        onView(ViewMatchers.withId(R.id.progress))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)
                )
        )

    }

}