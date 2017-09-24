package net.fyfconsult.fullsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.fyfconsult.fullsample.adapters.ContactAdapter;
import net.fyfconsult.fullsample.entities.Contact;
import net.fyfconsult.fullsample.services.ContactService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String OPERATION_KEY = "What";
    private static final String CONTACT_KEY = "Contact";
    private static final String CREATE_VALUE = "Add";
    private static final String DELETE_VALUE = "Del";
    private static final String UPDATE_VALUE = "Upd";
    private ContactService svcContact;
    private ListView lstPeople;
    private TextView lblName, lblPhone, lblId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cleanFields();
        fillContactList();
    }

    public void btnCall_Click(View v){
        makeCall();
    }

    public void btnCreate_Click(View v){
        createContact();
    }

    public void btnDelete_Click(View v){
//notifyDataSetChanged del adapter
        deleteContact();
    }

    public void btnUpdate_Click(View v){
        updateContact();
    }

    private void initializeComponents(){
        lblId = findViewById(R.id.lblId);
        lblName = findViewById(R.id.lblName);
        lblPhone = findViewById(R.id.lblPhone);
        lstPeople = findViewById(R.id.lstPeople);
        svcContact = new ContactService(this);
        lstPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                setFieldsWithSelectedContact(position);
            }
        });
        fillContactList();
    }

    private void makeCall(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            if(!lblName.getText().toString().isEmpty()) {
                String phoneNumber = lblPhone.getText().toString();
                if (!phoneNumber.isEmpty()) {
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(call);
                } else
                    Toast.makeText(this, "Select a phone number", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(this, "Select a contact", Toast.LENGTH_SHORT).show();
        }
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
    }

    private void cleanFields(){
        lblId.setText("");
        lblName.setText("");
        lblPhone.setText("");
    }

    private void createContact(){
        Intent frmInput = new Intent(this, AdminActivity.class);
        frmInput.putExtra(OPERATION_KEY, CREATE_VALUE);
        startActivity(frmInput);
    }

    private void updateContact(){
        if(lblName.getText().toString().length() > 0) {
            Intent frmInput = new Intent(this, AdminActivity.class);
            String[] name = lblName.getText().toString().split(", ");
            Contact objContact = new Contact();
            objContact.setId(Integer.parseInt(lblId.getText().toString()));
            objContact.setFirstName(name[1]);
            objContact.setLastName(name[0]);
            objContact.setPhone(lblPhone.getText().toString());
            frmInput.putExtra(OPERATION_KEY, UPDATE_VALUE);
            frmInput.putExtra(CONTACT_KEY, objContact);
            startActivity(frmInput);
        }else{
            Toast.makeText(this, "Select a contact", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteContact(){
        if(lblName.getText().toString().length() > 0) {
            svcContact.delete(Integer.parseInt(lblId.getText().toString()));
            cleanFields();
            fillContactList();
            Toast.makeText(this, "Contact deleted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Select a contact", Toast.LENGTH_LONG).show();
        }
    }

    private void fillContactList(){
        List<Contact> people = svcContact.getAll();
        ContactAdapter caAdapter = new ContactAdapter(this, android.R.layout.simple_list_item_1, people);
        lstPeople.setAdapter(caAdapter);
    }

    private void setFieldsWithSelectedContact(int index){
        Contact person = (Contact)lstPeople.getItemAtPosition(index);
        lblId.setText(person.getId() + "");
        lblName.setText(person.getLastName() + ", " + person.getFirstName());
        lblPhone.setText(person.getPhone());
    }
}