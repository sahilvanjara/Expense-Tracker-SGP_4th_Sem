package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.expensetracker.databinding.ActivityAddTransactionBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class AddTransactionActivity extends AppCompatActivity {
    ActivityAddTransactionBinding binding;
    FirebaseFirestore fStore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddTransactionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fStore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        binding.expenseCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Expense";
                binding.expenseCheckBox.setChecked(true);
                binding.incomeCheckBox.setChecked(false);
            }
        });
        binding.incomeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type="Income";
                binding.expenseCheckBox.setChecked(false);
                binding.incomeCheckBox.setChecked(true);
            }
        });
        binding.btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount=binding.userAmountAdd.getText().toString().trim();
                String description=binding.userDescriptionAdd.getText().toString().trim();
                if(amount.length()<=0){
                    return;
                }
                if(type.length()<=0){
                    Toast.makeText(AddTransactionActivity.this, "Select transaction type", Toast.LENGTH_SHORT).show();
                }
                SimpleDateFormat sdf=new SimpleDateFormat("dd MM yyyy_HH:mm", Locale.getDefault());
                String currentDateandTime= sdf.format(new Date());

                String id= UUID.randomUUID().toString();
                Map<String,Object> transaction=new HashMap<>();
                transaction.put("id",id);
                transaction.put("amount",amount);
                transaction.put("description",description);
                transaction.put("type",type);
                transaction.put("date",currentDateandTime);

                fStore.collection("Expenses").document(firebaseAuth.getUid()).collection("Description").document(id)
                        .set(transaction)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddTransactionActivity.this, "Added", Toast.LENGTH_SHORT).show();
                                binding.userDescriptionAdd.setText("");
                                binding.userAmountAdd.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddTransactionActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}