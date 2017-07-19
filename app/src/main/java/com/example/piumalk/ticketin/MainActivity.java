package com.example.piumalk.ticketin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.couchbase.lite.replicator.Replication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a manager
        Manager manager = null;
        try {
            manager = new Manager(new AndroidContext(getApplicationContext()), Manager.DEFAULT_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Create or open the database named app
        Database database = null;

        try {
            database = manager.getDatabase("ticketinapp");
        } catch (CouchbaseLiteException couchLiteException) {
            couchLiteException.printStackTrace();
        }

        //The properties that will be saved on the document
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("title", "CouchBase Mobile");
        properties.put("sdk", "Android");

        //Create a new document
        Document document = database.createDocument();
        //Save the document to the DB
        try {
            document.putProperties(properties);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        Log.d("app", String.format("Dcocument ID :: %s", document.getId()));
        Log.d("app", String.format("Learning %s with %s", (String) document.getProperty("title"), (String) document.getProperty("sdk")));

        //Create replicators to push & pull changes to & form sync gateway
        URL url = null;

        try{
            url = new URL("http://10.0.2.2:5984/hello");
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        Replication push = database.createPushReplication(url);
        Replication pull = database.createPullReplication(url);
        push.setContinuous(true);
        pull.setContinuous(true);

        //start replicators
        push.start();
        pull.start();
    }


}
