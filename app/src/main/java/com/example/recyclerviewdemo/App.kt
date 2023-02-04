package com.example.recyclerviewdemo

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        FlutterBoost.instance().setup(Utils.getApp(), object :
            FlutterBoostDelegate {
            override fun pushNativeRoute(options: FlutterBoostRouteOptions) {
                LogUtils.d("pushNativeRoute")
            }


            override fun pushFlutterRoute(options: FlutterBoostRouteOptions) {
                LogUtils.d("pushFlutterRoute")
                val intent = FlutterBoostActivity.CachedEngineIntentBuilder(
                    FlutterBoostActivity::class.java
                )
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                    .destroyEngineWithActivity(false)
                    .uniqueId(options.uniqueId())
                    .url(options.pageName())
                    .urlParams(options.arguments())
                    .build(FlutterBoost.instance().currentActivity())
                FlutterBoost.instance().currentActivity().startActivity(intent)
            }
        }) { engine: FlutterEngine? -> LogUtils.d(engine) }
    }
}