package Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.raghav.instaclone.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import adapters.ChatListAdapter;
import models.ChatModel;

/**
 * Created by User on 5/28/2017.
 */

public class MessagesFragment extends Fragment {
    private static final String TAG = "MessagesFragment";
    ListView usersList;
    TextView noUsersText;
    ArrayList<ChatModel> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_users, container, false);
        usersList = (ListView) root.findViewById(R.id.usersList);
        noUsersText = (TextView) root.findViewById(R.id.noUsersText);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://instagramclone-e8110.firebaseio.com/following/"+ FirebaseAuth.getInstance().getUid() + ".json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                al.clear();
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(getActivity());
        rQueue.add(request);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position).getId();
                startActivity(new Intent(getActivity(), Chat.class));
            }
        });

        return root;
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                JSONObject nameObj = (JSONObject) obj.get(key);

                if(!key.equals(UserDetails.username)) {
//                    al.add(key);
//                    al.add(nameObj.get("user_id").toString());
                    al.add(new ChatModel(key, nameObj.get("user_id").toString()/*,nameObj.get("state").toString()*/));
                }
                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ChatListAdapter(al, getContext()));
        }

        pd.dismiss();
    }
}