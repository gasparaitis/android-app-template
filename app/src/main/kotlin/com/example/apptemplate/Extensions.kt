package com.example.apptemplate

import android.content.Context
import android.content.Intent
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

fun Context.startOssLicensesActivity(title: String = "Open source licenses") {
    startActivity(
        Intent(
            /* packageContext = */ this,
            /* cls = */ OssLicensesMenuActivity::class.java,
        ),
    )
}
