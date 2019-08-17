package com.thadocizn.brav.utils

//import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.DrawerBuilder
import android.R
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import android.app.Activity
//import android.support.v7.widget.Toolbar
import androidx.appcompat.widget.Toolbar
import com.thadocizn.brav.views.MediatorActivity


/**
 * Created by charles on 01,August,2019
 */
object DrawerUtil {
    fun getDrawer(activity: Activity, toolbar: Toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        val drawerEmptyItem = PrimaryDrawerItem().withIdentifier(0).withName("")
        drawerEmptyItem.withEnabled(false)

        val drawerItemManageUser = PrimaryDrawerItem().withIdentifier(1)
            .withName("Charles").withIcon(R.drawable.sym_def_app_icon
            )
        val drawerItemManageUserEmail = PrimaryDrawerItem()
            .withIdentifier(2).withName("charles.tha.doc@gmail.com").withIcon(R.drawable.alert_dark_frame)


        val drawerItemSettings = SecondaryDrawerItem().withIdentifier(3)
            .withName("Cases").withIcon(R.drawable.arrow_down_float)
        val drawerItemAbout = SecondaryDrawerItem().withIdentifier(4)
            .withName("Mediator").withIcon(R.drawable.btn_dropdown)


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
                drawerItemSettings
                //drawerItemHelp,
               // drawerItemDonate
            )
            .withOnDrawerItemClickListener { view, position, drawerItem ->
                if (drawerItem.identifier == 4L ) {
                    // load tournament screen
                    val intent = Intent(activity, MediatorActivity::class.java)
                    view.getContext().startActivity(intent)
                }
                true
            }
            .build()
    }
}