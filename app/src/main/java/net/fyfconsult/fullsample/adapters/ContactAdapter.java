package net.fyfconsult.fullsample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.fyfconsult.fullsample.R;
import net.fyfconsult.fullsample.entities.Contact;

import java.util.List;

/**
 * Created by Bonjo on 21/09/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater liContact;
    private List<Contact> people;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        liContact = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        people = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemContact item;
        if(convertView == null){
            convertView = liContact.inflate(R.layout.item_contact, null);
            item = new ItemContact();
            item.contactId = convertView.findViewById(R.id.lblContactId);
            item.contactName = convertView.findViewById(R.id.lblContactName);
            item.contactPhone = convertView.findViewById(R.id.lblContactPhone);
            convertView.setTag(item);
        }
        else
            item = (ItemContact)convertView.getTag();
        Contact objContact = people.get(position);
        item.contactId.setText(objContact.getId() + "");
        item.contactName.setText(objContact.getLastName() + ", " + objContact.getFirstName());
        item.contactPhone.setText(objContact.getPhone());

        return convertView;
    }

    private static class ItemContact{
        public TextView contactId, contactName, contactPhone;
    }
}