package com.anago.applist

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.ApplicationInfo.FLAG_SYSTEM
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(context: Context) : RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    private var appList: ArrayList<app> = ArrayList()

    init {
        loadAppList(context)
    }

    private fun clearAppList() {
        appList.clear()
    }

    private fun loadAppList(context: Context) {
        clearAppList()
        val packageManager: PackageManager = context.packageManager
        val applicationInfoList: List<ApplicationInfo> = packageManager.getInstalledApplications(0)
        for (applicationInfo in applicationInfoList) {
            if (applicationInfo.flags and FLAG_SYSTEM == 0) {
                appList.add(
                    app(
                        icon = applicationInfo.loadIcon(packageManager),
                        name = applicationInfo.loadLabel(packageManager),
                        packageName = applicationInfo.packageName
                    )
                )
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView
        val name: TextView
        val packageName: TextView

        init {
            icon = itemView.findViewById(R.id.icon)
            name = itemView.findViewById(R.id.name)
            packageName = itemView.findViewById(R.id.packageName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app: app = appList[position]

        holder.icon.setImageDrawable(app.icon)
        holder.name.text = app.name
        holder.packageName.text = app.packageName
    }

    override fun getItemCount(): Int {
        return appList.size
    }
}