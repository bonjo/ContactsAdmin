package net.fyfconsult.fullsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import net.fyfconsult.fullsample.entities.Contact;
import net.fyfconsult.fullsample.services.ContactService;

public class AdminActivity extends AppCompatActivity {
    private static final String OPERATION_KEY = "What";
    private static final String CONTACT_KEY = "Contact";
    private static final String CREATE_VALUE = "Add";
    private static final String DELETE_VALUE = "Del";
    private static final String UPDATE_VALUE = "Upd";
    private ContactService svcContact;
    private TextInputEditText txtFirstName;
    private TextInputEditText txtLastName;
    private TextInputEditText txtPhone;
    private Intent frmMain;
    private String operation;
    private Contact objContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initializeComponents();
    }

    private void initializeComponents(){
        svcContact = new ContactService(this);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtPhone = findViewById(R.id.txtPhone);
        svcContact = new ContactService(this);
        frmMain = this.getIntent();
        operation = frmMain.getStringExtra(OPERATION_KEY);
        objContact = new Contact();
        if(operation.equals(UPDATE_VALUE)){
            objContact = (Contact) frmMain.getSerializableExtra(CONTACT_KEY);
            txtFirstName.setText(objContact.getFirstName());
            txtLastName.setText(objContact.getLastName());
            txtPhone.setText(objContact.getPhone());
        }
    }

    public void btnOk_Click(View v){
        sendToDb();
    }

    public void btnCancel_Click(View v){
        finish();
    }

    private void sendToDb(){
        objContact.setFirstName(txtFirstName.getText().toString());
        objContact.setLastName(txtLastName.getText().toString());
        objContact.setPhone(txtPhone.getText().toString());
        switch (operation){
            case CREATE_VALUE:
                svcContact.create(objContact);
                Toast.makeText(this, "Contact Created", Toast.LENGTH_LONG).show();
                break;
            case DELETE_VALUE:
                svcContact.delete(1);
                Toast.makeText(this,"Contact Deleted", Toast.LENGTH_LONG).show();
                break;
            case UPDATE_VALUE:
                svcContact.update(objContact);
                Toast.makeText(this, "Contact Updated", Toast.LENGTH_LONG).show();
                break;
        }
        finish();
    }
}