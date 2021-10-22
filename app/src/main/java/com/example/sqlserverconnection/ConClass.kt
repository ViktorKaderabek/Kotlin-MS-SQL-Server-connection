package com.example.sqlserverconnection

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager

class ConClass {



    fun conFun(): Connection {

        var con: Connection? = null
        val ip: String = "192.168.0.242"
        val port: String = "1433"
        val db: String = "testDatabase"
        val username: String = "test"
        val pass: String = "1234"


        val a: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(a)
        val connectURL: String? = null

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            val connectURL: String =
                "jdbc:jtds:sqlserver://$ip:$port;databasename=$db;user=$username;password=$pass;"

           con = DriverManager.getConnection(connectURL)
            return con
        } catch (e: Exception) {
            Log.e("Error :", e.message.toString())
        }
        return con!!

    }
}


