package com.hb.base.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity

interface Routable<Route : AppCompatActivity, DATA : Parcelable> {

  companion object {
    const val ROUTE_DATA = "RouteData"
    const val DEEP_LINK_DATA = "DeepLinkData"
  }

  val route: Class<Route>

  val deepLinkCode: Array<Int>?

  fun startDeepLink(context: Context?, data: Uri?) {
    buildIntent(context).putExtra(DEEP_LINK_DATA, data).also {
      context?.startActivity(it)
    }
  }

  fun startActivity(context: Context?, data: DATA? = null) {
    buildIntent(context).putExtra(ROUTE_DATA, data).also {
      context?.startActivity(it)
    }
  }

  fun buildIntent(context: Context?) = Intent(context, route)

  fun <Data : Parcelable> getRouterData(intent: Intent?) = intent?.extras?.getParcelable<Data>(
    ROUTE_DATA
  )

  fun getDeepLinkData(intent: Intent?) = intent?.extras?.getParcelable<Uri>(DEEP_LINK_DATA)
}
