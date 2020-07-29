package com.aescttgt.appsqlserverudv.Connection;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.aescttgt.appsqlserverudv.R;
import com.aescttgt.appsqlserverudv.SharedPrefencies.SharedPrefManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String TAG = "DatabaseConnection";
    private static DatabaseConnection instance;
    private static Context mContext;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    //1,3

    public static DatabaseConnection getInstance(Context context) throws SQLException {
        mContext = context;
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() throws SQLException {
        try {
            String url = "jdbc:jtds:sqlserver://" + SharedPrefManager.getPrefVal(mContext, mContext.getString(R.string.ip)) + ";databaseName=" + SharedPrefManager.getPrefVal(mContext, mContext.getString(R.string.database_name));
            String username = SharedPrefManager.getPrefVal(mContext, mContext.getString(R.string.user_name));
            String password = SharedPrefManager.getPrefVal(mContext, mContext.getString(R.string.password));
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Log.e(TAG, "DatabaseConnection: Connection Creation Failed: " + ex.getMessage());
        }
    }
}
