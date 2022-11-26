package com.lmc.mylibrary

import androidx.fragment.app.FragmentActivity
//单例类是为了咱这个类中的方法更容易被调用
object PermissionX {
    private const val TAG = "InvisibleFragment"
    //注解：FragmentActivity 是 AppCompatActivity 的父类（不信 Ctrl+H）
    fun request(activity: FragmentActivity, vararg permissions: String, callback:
     PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager//获取FragmentManager的实例
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            //把invisibleFragment加进Activity
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()//这里不能换成commit（否则下面这个作为返回值返回的时候是没有那个i——Fragment的 // ）
            invisibleFragment
        }
        fragment.requestNow(callback, *permissions)//*：表示将一个数组转换成可变长度参数传递过去
    }
}