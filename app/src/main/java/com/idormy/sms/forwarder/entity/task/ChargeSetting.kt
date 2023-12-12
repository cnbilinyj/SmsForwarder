package com.idormy.sms.forwarder.entity.task

import android.os.BatteryManager
import com.idormy.sms.forwarder.R
import com.xuexiang.xui.utils.ResUtils
import com.xuexiang.xutil.resource.ResUtils.getString
import java.io.Serializable

@Suppress("DEPRECATION", "unused")
data class ChargeSetting(
    var description: String = "", //描述
    var status: Int = BatteryManager.BATTERY_STATUS_UNKNOWN, //状态
    var plugged: Int = BatteryManager.BATTERY_PLUGGED_AC, //充电方式
) : Serializable {

    constructor(statusCheckId: Int, pluggedCheckId: Int) : this() {
        status = when (statusCheckId) {
            R.id.rb_battery_charging -> BatteryManager.BATTERY_STATUS_CHARGING
            R.id.rb_battery_discharging -> BatteryManager.BATTERY_STATUS_DISCHARGING
            R.id.rb_battery_not_charging -> BatteryManager.BATTERY_STATUS_NOT_CHARGING
            R.id.rb_battery_full -> BatteryManager.BATTERY_STATUS_FULL
            R.id.rb_battery_unknown -> BatteryManager.BATTERY_STATUS_UNKNOWN
            else -> BatteryManager.BATTERY_STATUS_UNKNOWN
        }
        plugged = when (pluggedCheckId) {
            R.id.rb_plugged_ac -> BatteryManager.BATTERY_PLUGGED_AC
            R.id.rb_plugged_usb -> BatteryManager.BATTERY_PLUGGED_USB
            R.id.rb_plugged_wireless -> BatteryManager.BATTERY_PLUGGED_WIRELESS
            else -> BatteryManager.BATTERY_PLUGGED_AC
        }
        description = String.format(getString(R.string.battery_status), getStatusStr(status))
        description += ", " + String.format(getString(R.string.battery_plugged), getPluggedStr(plugged))
    }

    private fun getStatusStr(status: Int): String {
        return when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> ResUtils.getString(R.string.battery_charging)
            BatteryManager.BATTERY_STATUS_DISCHARGING -> ResUtils.getString(R.string.battery_discharging)
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> ResUtils.getString(R.string.battery_not_charging)
            BatteryManager.BATTERY_STATUS_FULL -> ResUtils.getString(R.string.battery_full)
            BatteryManager.BATTERY_STATUS_UNKNOWN -> ResUtils.getString(R.string.battery_unknown)
            else -> ResUtils.getString(R.string.battery_unknown)
        }
    }

    fun getStatusCheckId(): Int {
        return when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> R.id.rb_battery_charging
            BatteryManager.BATTERY_STATUS_DISCHARGING -> R.id.rb_battery_discharging
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> R.id.rb_battery_not_charging
            BatteryManager.BATTERY_STATUS_FULL -> R.id.rb_battery_full
            BatteryManager.BATTERY_STATUS_UNKNOWN -> R.id.rb_battery_unknown
            else -> R.id.rb_battery_charging
        }
    }

    private fun getPluggedStr(plugged: Int): String {
        return when (plugged) {
            BatteryManager.BATTERY_PLUGGED_AC -> ResUtils.getString(R.string.battery_ac)
            BatteryManager.BATTERY_PLUGGED_USB -> ResUtils.getString(R.string.battery_usb)
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> ResUtils.getString(R.string.battery_wireless)
            else -> ResUtils.getString(R.string.battery_unknown)
        }
    }

    fun getPluggedCheckId(): Int {
        return when (plugged) {
            BatteryManager.BATTERY_PLUGGED_AC -> R.id.rb_plugged_ac
            BatteryManager.BATTERY_PLUGGED_USB -> R.id.rb_plugged_usb
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> R.id.rb_plugged_wireless
            else -> R.id.rb_plugged_unknown
        }
    }
}