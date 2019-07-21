package utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class StatusUtil {

    public static void updateuserstatus(String state){

        String savecurrenttime, savecurrentdate;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currenttime = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        savecurrenttime = currenttime.format(calendar.getTime());

        SimpleDateFormat currentdate = new SimpleDateFormat("dd MMM YYYY", Locale.getDefault());
        savecurrentdate = currentdate.format(calendar.getTime());

        HashMap<String,Object> onlinestate = new HashMap<>();
        onlinestate.put("time", savecurrenttime);
        onlinestate.put("date", savecurrentdate);
        onlinestate.put("state", state);


        String currentid  = FirebaseAuth.getInstance().getUid();
        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(currentid)
                .updateChildren(onlinestate);
    }

}
