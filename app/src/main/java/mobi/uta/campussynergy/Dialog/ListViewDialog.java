package mobi.uta.campussynergy.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import mobi.uta.campussynergy.R;

/**
 * Created by Cameron on 2/8/15.
 */
public class ListViewDialog extends DialogFragment {

    ListView lv;
    Button cancel;

    List<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_listview, container, false);

        lv = (ListView) view.findViewById(R.id.lv_dialog);
        cancel = (Button) view.findViewById(R.id.button_cancel);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

}
