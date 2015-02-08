package mobi.uta.campussynergy.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

import mobi.uta.campussynergy.Dialog.ListViewDialog;
import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/7/15.
 */
public class LoginInfoFragment extends Fragment implements View.OnClickListener {

    private EditText etUniversity, etMajor;
    private ImageButton bSearch;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_info, container, false);
        context = view.getContext();

        Button bNext = (Button) view.findViewById(R.id.button_next);
        Button bBack = (Button) view.findViewById(R.id.button_back);

        bSearch = (ImageButton) view.findViewById(R.id.button_search);
        etUniversity = (EditText) view.findViewById(R.id.et_university);
        etMajor = (EditText) view.findViewById(R.id.et_major);


        bNext.setOnClickListener(this);
        bBack.setOnClickListener(this);
        bSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_next:
                if(isInfoValidated()) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new LoginLikesFragment())
                        .commit();
                }
                break;
            case R.id.button_back:
                getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new FacebookLoginFragment())
                    .commit();
                break;

            case R.id.button_search:
                String url = "http://misc.cameronmoreau.org/hackathon/college.php?school=";
                String search = etUniversity.getText().toString();
                search = search.replace(' ', '+');

                new RequestTask().execute(url + search);

                //Toast.makeText(context, search, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean isInfoValidated() {
        //fix dis shit
        return true;
    }

    class RequestTask extends AsyncTask<String, String, String> {

        String result = null;

        @Override
        protected String doInBackground(String... uri) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            result = responseString;
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<String>>(){}.getType();
            List<String> list = (List<String>) gson.fromJson(result, listType);
            Log.e("Test", list.toString());


            /*ListViewDialog d = new ListViewDialog();
            d.setData(list);
            d.show(getFragmentManager(), "dialog");*/
        }
    }
}
