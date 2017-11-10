package com.example.spark.app_with_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String url="jdbc:mysql://192.168.0.106:3306/login";
    private  static final String user="mokbul";
    private static final String pass="mokbul123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testdb();
    }
    public void testdb(){
        TextView tv=(TextView)this.findViewById(R.id.tv);

        try{
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,user,pass);
            String result="Database connection success\n";

         Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from logintable");
            ResultSetMetaData rsmd=rs.getMetaData();

            while (rs.next()){
                result +=rsmd.getColumnName(1)+":"+rs.getInt(1)+ "\n";
                result +=rsmd.getColumnName(2)+":"+rs.getString(2)+ "\n";
                result +=rsmd.getColumnName(3)+":"+rs.getString(3)+ "\n";
            }
            tv.setText(result);

        }
        catch (Exception e){
            tv.setText("" +e);
        }
    }
}
