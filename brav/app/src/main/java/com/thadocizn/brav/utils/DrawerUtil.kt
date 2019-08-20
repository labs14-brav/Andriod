package com.thadocizn.brav.utils

//import android.support.v4.content.ContextCompat.startActivity
//import android.support.v7.widget.Toolbar
import android.R
import android.app.Activity
import androidx.appcompat.widget.Toolbar
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.thadocizn.brav.views.CaseActivity
import com.thadocizn.brav.views.UserAccountActivity
import org.jetbrains.anko.startActivity


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
            .withName("User ID: $id").withIcon(
                R.drawable.btn_star
            )
        val drawerItemManageUserEmail = PrimaryDrawerItem()
            .withIdentifier(2).withName(email).withIcon(R.drawable.ic_dialog_email)


        val drawerItemCase = SecondaryDrawerItem().withIdentifier(3)
            .withName("Cases").withIcon(R.drawable.arrow_down_float)

        val drawerItemSettings = SecondaryDrawerItem().withIdentifier(4)
            .withName("Settings").withIcon(R.drawable.dark_header)

        //create the drawer and remember the `Drawer` result object
        DrawerBuilder()
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
                drawerItemCase,
                drawerItemSettings

            )
            .withOnDrawerItemClickListener { view, _, drawerItem ->

                when(drawerItem.identifier){

                    3L -> {view.context.startActivity<CaseActivity>()}
                    4L -> {view.context.startActivity<UserAccountActivity>()}
                }
                true
            }
            .build()
    }
}