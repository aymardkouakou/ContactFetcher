


package com.rocky.contacter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.rocky.contactfetcher.Contact;
import com.rocky.contactfetcher.Helper;

import java.util.ArrayList;

public class ContactDetailActivity extends AppCompatActivity {

    public static final String CONTACT = "Contact";
    private ContactDetailAdapter adapter;

    public static Intent getIntent(Context context, Bundle bundle) {
        return new Intent(context, ContactDetailActivity.class).putExtras(bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_details);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactDetailAdapter(new ArrayList<String>());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((position, v) -> {
            Helper.copyToClipboard(adapter.getItem(position), ContactDetailActivity.this);
            Toast.makeText(ContactDetailActivity.this, "Copied ...!!!", Toast.LENGTH_SHORT).show();
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CONTACT)) {
            Contact contact = intent.getParcelableExtra(CONTACT);
            adapter.setContact(contact);
        }
    }
}
