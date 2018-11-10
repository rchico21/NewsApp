package com.example.rkjc.news_app_2;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String base_url = "https://newsapi.org/v1/articles";
    final static String source = "the-next-web";
    final static String sort = "latest";
    final static String key = "542f7c010a084e60a01067552445619a";

    final static String query_parameter = "source";
    final static String sortBy_parameter = "sortBy";
    final static String key_parameter = "apiKey";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(base_url).buildUpon().appendQueryParameter(query_parameter, source).appendQueryParameter(sortBy_parameter, sort).appendQueryParameter(key_parameter, key).build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
            ;
        }
    }

    class NewsQueryTask extends AsyncTask<URL, String, String> {

        String jsonString;

        @Override
        protected String doInBackground(URL... params) {
            HttpURLConnection connection = null;
            URL url = NetworkUtils.buildUrl();

            try {
                jsonString = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonString;


        }
    }
}
