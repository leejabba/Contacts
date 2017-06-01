package kr.heythisway.contacts;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kr.heythisway.contacts.domain.Data;

/**
 * Created by SMARTHINK_MBL13 on 2017. 6. 1..
 */

class ContactAdapta extends RecyclerView.Adapter<ContactAdapta.Holder> {
    Activity activity;
    List<Data> useDataBase;

    public ContactAdapta(Activity activity, List<Data> useDataBase) {
        this.activity = activity;
        this.useDataBase = useDataBase;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_view, parent, false);
        return new Holder(inflater);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Data data = useDataBase.get(position);
        holder.setName(data.getName());
        holder.setEmail(data.getTel());
    }

    @Override
    public int getItemCount() {
        return useDataBase.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView tel;

        public Holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.itemName);
            tel = (TextView) itemView.findViewById(R.id.itemTel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "gkgk", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setEmail(String tel) {
            this.tel.setText(tel);
        }


    }

}
