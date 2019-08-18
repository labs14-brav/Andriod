package com.thadocizn.brav.utils

//import android.support.v4.content.ContextCompat.startActivity
//import android.support.v7.widget.Toolbar
import android.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.thadocizn.brav.views.ActiveCasesActivity
import com.thadocizn.brav.views.CompletedCasesActivity
import com.thadocizn.brav.views.PendingCasesActivity
import com.thadocizn.brav.views.CaseActivity


/**
 * Created by charles on 01,August,2019
 */
object DrawerUtil {
    fun getDrawer(activity: Activity, toolbar: Toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        val drawerEmptyItem = PrimaryDrawerItem().withIdentifier(0).withName("")
        drawerEmptyItem.withEnabled(false)
        val email = SharedPreference(activity.baseContext).getUserEmail("userEmail").toString()
        val id = SharedPreference(activity.baseContext).getUserId("userId").toString()

        val drawerItemManageUser = PrimaryDrawerItem().withIdentifier(1)
            .withName(id).withIcon(
                R.drawable.sym_def_app_icon
            )
        val drawerItemManageUserEmail = PrimaryDrawerItem()
            .withIdentifier(2).withName(email).withIcon(R.drawable.alert_dark_frame)


        val drawerItemSettings = SecondaryDrawerItem().withIdentifier(3)
            .withName("Cases").withIcon(R.drawable.arrow_down_float)
        val drawerItemHelp = SecondaryDrawerItem().withIdentifier(4)
            .withName("Pending").withIcon(R.drawable.btn_dropdown)
        val drawerItemAbout = SecondaryDrawerItem().withIdentifier(5)
            .withName("Active").withIcon(R.drawable.btn_dropdown)
        val drawerItemDonate = SecondaryDrawerItem().withIdentifier(6)
            .withName("Completed").withIcon(R.drawable.btn_dropdown)


        //create the drawer and remember the `Drawer` result object
        val result = DrawerBuilder()
            .withActivity(activity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withActionBarDrawerToggleAnimated(true)
            .withCloseOnClick(true)
            .withSelectedItem(-1)
            .addDrawerItems(
                drawerEmptyItem, drawerEmptyItem, drawerEmptyItem,
                drawerItemManageUser,
                drawerItemManageUserEmail,
                DividerDrawerItem(),
                drawerItemAbout,
                drawerItemSettings,
                drawerItemHelp,
                drawerItemDonate
            )
            .withOnDrawerItemClickListener { view, position, drawerItem ->
                if (drawerItem.identifier == 3L) {
                    // load tournament screen
                    val intent = Intent(activity, CaseActivity::class.java)
                    view.getContext().startActivity(intent)
                }
                if (drawerItem.identifier == 4L) {
                    // load tournament screen
                    val intent = Intent(activity, PendingCasesActivity::class.java)
                    view.getContext().startActivity(intent)
                }
                if (drawerItem.identifier == 5L) {
                    // load tournament screen
                    val intent = Intent(activity, ActiveCasesActivity::class.java)
                    view.getContext().startActivity(intent)
                }
                if (drawerItem.identifier == 6L) {
                    // load tournament screen
                    val intent = Intent(activity, CompletedCasesActivity::class.java)
                    view.getContext().startActivity(intent)
                }
                true
            }
            .build()
    }
}