package com.example.sqlserverconnection


import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sqlserverconnection.databinding.ActivityMain2Binding
import com.example.sqlserverconnection.databinding.ActivityMainBinding
import java.sql.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var mainActivity2: ActivityMain2Binding

    private val ip = "192.168.0.242"
    private val port = "1300"
    private val ss = "net.sourceforge.jtds.jdbc.Driver"
    private val database = "testDatabase"
    private val username = "test"
    private val password = "1234"
    private val url = "jdbc:jtds:sqlserver://$ip:$port/$database"
    private var finalResult : String? = null


    private var connection: Connection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val intent =
            Intent(this, MainActivity2::class.java)

        //ActivityCompat.requestPermissions(this,nString[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);


        // CoroutineScope(Dispatchers.IO).launch {
        val policy: StrictMode.ThreadPolicy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connection = DriverManager.getConnection(url,username,password)
            mainActivityBinding.textView.text = "SUCCESS"
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()

            mainActivityBinding.textView.text = "ERROR"
            Log.e("Error :", exception.message.toString())
        } catch (exception: SQLException) {
            exception.printStackTrace()
            mainActivityBinding.textView.text = "FAILURE"
            Log.e("Error :", exception.message.toString())
        }
        //  }


        mainActivityBinding.button.setOnClickListener {
            if (connection != null) {
                var statement: Statement? = null
                try {
                    statement = connection!!.createStatement()
                    val resultSet: ResultSet = statement.executeQuery("Select * from TEST_TABLE;")
                    finalResult = resultSet.getString(1)
                    while (resultSet.next()) {
                        mainActivity2.textv.text = resultSet.getString(1)


                    }
                } catch (e: SQLException) {
                    e.printStackTrace()
                }
            } else {
                mainActivityBinding.textView.text = "Connection is null"
            }

            startActivity(intent)
            //intent.putExtra("text",finalResult)
            finish()
        }

    }
}