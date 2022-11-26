package com.lmc.permissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.lmc.mylibrary.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.makeCallBtn).setOnClickListener {
            PermissionX.request(this,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS) {
                allGranted,deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this,"你取消授权：$deniedList",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun call(){
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}