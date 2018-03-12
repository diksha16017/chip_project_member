package com.example.root.chips_projects;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.tokenautocomplete.TokenCompleteTextView;
import java.util.ArrayList;
import java.util.List;

//code
public class AddProjectMainActivity extends AppCompatActivity implements TokenCompleteTextView.TokenListener<ProjectMembers>{

    private ArrayList<ProjectMembers> contacts;
    private AddProjectFilterAdapter filterAdapter;
    private AddProjectCompletionView autoCompleteTextView;

    private EditText mProjectNameEditText ;
    private EditText mDescriptionEditText ;
    private EditText mSkillsEditText ;
    private Button mDoneButton ;
    private Spinner mCreditsSpinner;
    private EditText mVacancyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project_activity_main);

        setSampleContact();

        autoCompleteTextView = (AddProjectCompletionView) findViewById(R.id.autocomplete_textview_project_add_member);

        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        //Initializing and attaching adapter for AutocompleteTextView
        filterAdapter = new AddProjectFilterAdapter(this, R.layout.project_item_member_add, contacts);
        autoCompleteTextView.setAdapter(filterAdapter);


        autoCompleteTextView.setTokenListener(this);


        autoCompleteTextView.setTokenClickStyle(TokenCompleteTextView.TokenClickStyle.Select);

        mProjectNameEditText = (EditText) findViewById(  R.id.projectNameEditText) ;
        mDescriptionEditText = (EditText) findViewById(R.id.descriptionEditText ) ;
        mSkillsEditText = (EditText) findViewById(R.id.skillsEditText ) ;
        mVacancyEditText = (EditText) findViewById(R.id.vacancyEditText ) ;
        mDoneButton = (Button ) findViewById( R.id.doneButtonProject ) ;
        mCreditsSpinner = (Spinner) findViewById(R.id.spinner_credits_project);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ProjectMembers> tokens = autoCompleteTextView.getObjects(); //get list of selected subjects
                StringBuilder content = new StringBuilder();
                for (int i = 0; i < tokens.size(); i++) {
                    content.append(tokens.get(i)).append("; ");
                }

                String credits = String.valueOf(mCreditsSpinner.getSelectedItem());
                String name = mProjectNameEditText.getText().toString().toUpperCase();
                String roll = mDescriptionEditText.getText().toString();
                String vacancy = mVacancyEditText.getText().toString();
                Toast.makeText( AddProjectMainActivity.this , name+" ("+roll+" "+ vacancy +" "+credits+" ) choose: " + content.toString() , Toast.LENGTH_LONG).show();
            }
        });


    }
    private void setSampleContact() {
        contacts = new ArrayList<>();
        contacts.add(new ProjectMembers(  "MT16017", "Diksha"));
        contacts.add(new ProjectMembers( "MT16059", "Divya"));
        contacts.add(new ProjectMembers( "MT16004", "Anupama" ));
        contacts.add(new ProjectMembers(  "MT16054", "Raveena"));
        contacts.add(new ProjectMembers(  "MT16032", "Medha"));
        contacts.add(new ProjectMembers( "MT16033", "Megha"));
        contacts.add(new ProjectMembers(  "MT16051", "Sarika"));
        contacts.add(new ProjectMembers( "MT16037", "Neha"));
        contacts.add(new ProjectMembers( "MT16038", "Nitish"));
    }

    @Override
    public void onTokenAdded(ProjectMembers token) {
        Log.d("Main", "A Token added");
    }

    @Override
    public void onTokenRemoved(ProjectMembers token) {
        Log.d("Main", "A Token removed");
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
